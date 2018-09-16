package com.undecode.goduettocompanion.bakar.utils.date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.joda.time.DateTime;

/**
 * Created by Amr on 3/18/2017.
 */
public class JsonSerializerDeserializer {
    private static Gson handler = null;

    private JsonSerializerDeserializer() {

    }

    public static void initialize(Boolean useDotNetFormat) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(DateTime.class, new DateTimeSerializer(useDotNetFormat));
        builder.registerTypeAdapter(DateTime.class, new DateTimeDeserializer(useDotNetFormat));
        handler = builder.create();
    }

    public static <T> String serialize(T instance, Boolean useDotNetFormat) {
        initialize(useDotNetFormat);
        if (useDotNetFormat) {
            return (handler.toJson(instance, instance.getClass()))
                    .replace("\"", "")
                    .replace("\\/\"", "/")
                    ;
        } else {
            return handler.toJson(instance, instance.getClass());
        }
    }

    public static <T> DateTime deserialize(String json, Class<DateTime> resultType) throws JsonSyntaxException {
        initialize(json.contains("Date("));
        return handler.fromJson(json, resultType);
    }
}
