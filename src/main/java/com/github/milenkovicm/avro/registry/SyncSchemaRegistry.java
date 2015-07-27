package com.github.milenkovicm.avro.registry;

public interface SyncSchemaRegistry {

    RegistryItem lookup(String name);

    RegistryItem lookup(long id);

    RegistryItem put(RegistryItem registryItem);
}
