package com.github.sorend.bitbucketserver.webhook;

import com.github.sorend.bitbucketserver.webhook.service.WebhookDispatcher;
import com.github.sorend.bitbucketserver.webhook.service.WebhookService;
import com.github.sorend.bitbucketserver.webhook.eventpayload.EventPayloads;
import com.github.sorend.bitbucketserver.webhook.eventpayload.helper.GsonHelper;
import io.helidon.health.HealthSupport;
import io.helidon.health.checks.HealthChecks;
import io.helidon.metrics.MetricsSupport;
import io.helidon.webserver.Routing;
import io.helidon.webserver.WebServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static WebServer start(ApplicationConfiguration configuration, WebhookHandler webhookHandler) {

        Routing routing = createRouting(configuration, webhookHandler);

        WebServer server = WebServer.create(configuration.getServerConfiguration(), routing);

        // start server
        server.start()
                .thenAccept(ws -> {
                    logger.info("WEB server is up! http://localhost:{}/", ws.port());
                    ws.whenShutdown().thenRun(() -> logger.info("WEB server is DOWN. Good bye!"));
                })
                .exceptionally(t -> {
                    logger.info("Startup failed: {}", t.getMessage(), t);
                    return null;
                });

        return server;
    }

    private static Routing createRouting(ApplicationConfiguration configuration, WebhookHandler webhookHandler) {

        MetricsSupport metrics = MetricsSupport.create();

        HealthSupport health = HealthSupport.builder()
                .addLiveness(HealthChecks.healthChecks())   // Adds a convenient set of checks
                .build();

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        EventPayloads eventPayloads = new EventPayloads(GsonHelper.configure());

        WebhookDispatcher webhookDispatcher = new WebhookDispatcher(configuration.getBitbucketApi(), eventPayloads, executorService, webhookHandler);

        WebhookService webhookService = new WebhookService(webhookDispatcher);

        return Routing.builder()
                .register(health)                   // Health at "/health"
                .register(metrics)                  // Metrics at "/metrics"
                .register(configuration.getServerPath(), webhookService)
                .build();
    }
}
