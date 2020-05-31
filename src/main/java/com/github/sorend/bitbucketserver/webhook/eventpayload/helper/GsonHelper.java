package com.github.sorend.bitbucketserver.webhook.eventpayload.helper;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GsonHelper {

    public static Gson configure() {
        return new GsonBuilder().registerTypeAdapter(ZonedDateTime.class, GsonHelper.ZDT_DESERIALIZER).create();
    }

    public static final JsonDeserializer<ZonedDateTime> ZDT_DESERIALIZER = new JsonDeserializer<ZonedDateTime>() {
        @Override
        public ZonedDateTime deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
            JsonPrimitive jsonPrimitive = json.getAsJsonPrimitive();
            try {
                // if provided as String - '2011-12-03T10:15:30+01:00[Europe/Paris]'
                if (jsonPrimitive.isString()) {
                    String jsonString = jsonPrimitive.getAsString();
                    Matcher m = Pattern.compile("\\+(\\d{2})(\\d{2})").matcher(jsonString);
                    String value = m.find() ? m.replaceFirst("+$1:$2") : jsonString;
                    return ZonedDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME);
                }
                // if provided as Long
                if (jsonPrimitive.isNumber()) {
                    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(jsonPrimitive.getAsLong()), ZoneId.systemDefault());
                }

            } catch (RuntimeException e) {
                throw new JsonParseException("Unable to parse ZonedDateTime (1)", e);
            }
            throw new JsonParseException("Unable to parse ZonedDateTime (2) from " + jsonPrimitive);
        }
    };
}
