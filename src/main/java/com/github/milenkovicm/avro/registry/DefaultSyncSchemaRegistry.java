package com.github.milenkovicm.avro.registry;


public class DefaultSyncSchemaRegistry implements  SyncSchemaRegistry{

    final DefaultAsyncSchemaRegistry schemaRegistry;

    public DefaultSyncSchemaRegistry(String address) {
        this.schemaRegistry = new DefaultAsyncSchemaRegistry(address);
    }

    public RegistryItem lookup(String name){
        return schemaRegistry.lookup(name).toBlocking().single();
    }

    public RegistryItem lookup(long id){
        return schemaRegistry.lookup(id).toBlocking().single();
    }

    public RegistryItem put(RegistryItem registryItem) {
        return schemaRegistry.put(registryItem).toBlocking().single();
    }
}
