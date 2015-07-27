package com.github.milenkovicm.avro.registry.remote;


import com.github.milenkovicm.avro.registry.SchemaUtil;

public class SchemaRegistryId {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return SchemaUtil.toString(this);
    }
}
