package com.github.milenkovicm.avro.registry.remote;

import com.github.milenkovicm.avro.registry.SchemaUtil;

class SchemaRegistryResponse {

    private String subject;
    private Integer version;
    private Long id;
    private String schema;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        return SchemaUtil.toString(this);
    }
}
