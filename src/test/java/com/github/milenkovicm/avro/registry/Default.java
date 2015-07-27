package com.github.milenkovicm.avro.registry;


import org.apache.avro.Schema;

public class Default {

    public static final String DEFAULT_NAME_RESPONSE = "{\"subject\":\"test-schema\",\"version\":1,\"id\":81,\"schema\":\"{\\\"type\\\":\\\"record\\\",\\\"name\\\":\\\"E_2\\\",\\\"namespace\\\":\\\"com.github.milenkovicm.avro\\\",\\\"fields\\\":[{\\\"name\\\":\\\"P1\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"P2\\\",\\\"type\\\":\\\"string\\\"},{\\\"name\\\":\\\"P3\\\",\\\"type\\\":\\\"string\\\"}]}\"}";
    public static final String DEFAULT_ID_RESPONSE = "{\"schema\":\"{   \\\"type\\\": \\\"record\\\",   \\\"name\\\": \\\"E_2\\\",   \\\"namespace\\\": \\\"com.github.milenkovicm.avro\\\",   \\\"fields\\\": [     {       \\\"name\\\": \\\"P1\\\",       \\\"type\\\": \\\"string\\\"     },     {       \\\"name\\\": \\\"P2\\\",       \\\"type\\\": \\\"string\\\"     },     {       \\\"name\\\": \\\"P3\\\",       \\\"type\\\": \\\"string\\\"     }   ] }\"}";
    public static final String DEFAULT_NAME_SCHEMA = "{\"type\":\"record\",\"name\":\"E_2\",\"namespace\":\"com.github.milenkovicm.avro\",\"fields\":[{\"name\":\"P1\",\"type\":\"string\"},{\"name\":\"P2\",\"type\":\"string\"},{\"name\":\"P3\",\"type\":\"string\"}]}";
    public static final String DEFAULT_ID_SCHEMA = "{   \"type\": \"record\",   \"name\": \"E_2\",   \"namespace\": \"com.github.milenkovicm.avro\",   \"fields\": [     {       \"name\": \"P1\",       \"type\": \"string\"     },     {       \"name\": \"P2\",       \"type\": \"string\"     },     {       \"name\": \"P3\",       \"type\": \"string\"     }   ] }";
    public static final String DEFAULT_ID_POST_REPONSE = "{\"id\": 102}";

    public static final Schema DEFAULT_SCHEMA = new Schema.Parser().parse(DEFAULT_ID_SCHEMA);

    private Default(){};
}
