package com.github.milenkovicm.avro.registry;


import rx.Observable;

public interface AsyncSchemaRegistry {

    Observable<RegistryItem> lookup(String name);

    Observable<RegistryItem> lookup(long id);

    Observable<RegistryItem> put(RegistryItem registryItem);

}
