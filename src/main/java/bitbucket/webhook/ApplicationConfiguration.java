package bitbucket.webhook;

import com.cdancy.bitbucket.rest.BitbucketApi;
import io.helidon.webserver.ServerConfiguration;

public class ApplicationConfiguration {

    private ServerConfiguration serverConfiguration;
    private String serverPath;

    private BitbucketApi bitbucketApi;

    public ApplicationConfiguration(ServerConfiguration serverConfiguration, String serverPath, BitbucketApi bitbucketApi) {
        this.serverConfiguration = serverConfiguration;
	this.serverPath = serverPath;
	this.bitbucketApi = bitbucketApi;
    }

    public ServerConfiguration getServerConfiguration() {
        return serverConfiguration;
    }

    public String getServerPath() {
        return serverPath;
    }

    public BitbucketApi getBitbucketApi() {
        return bitbucketApi;
    }
}
