package com.github.milenkovicm.avro.registry;

import org.apache.avro.Schema;

public class RegistryItem {

    private final String subject;
    private final Long id;
    private final Integer version;
    private final Schema schema;

    public RegistryItem(String subject, Long id, Integer version, Schema schema) {
        this.subject = subject;
        this.id = id;
        this.version = version;
        this.schema = schema;
    }

    public String getSubject() {
        return subject;
    }

    public Long getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public Schema getSchema() {
        return schema;
    }
}
