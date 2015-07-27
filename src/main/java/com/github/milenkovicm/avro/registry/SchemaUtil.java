package com.github.milenkovicm.avro.registry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.avro.Schema;

public class SchemaUtil {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
            .disableHtmlEscaping().create();

    public static Schema parse(String schema) {
        return new Schema.Parser().parse(schema);
    }

    public static String toString(Object o) {
        return GSON.toJson(o);
    }

}
