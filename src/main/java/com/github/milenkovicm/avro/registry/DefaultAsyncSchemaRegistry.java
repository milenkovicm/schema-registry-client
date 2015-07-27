package com.github.milenkovicm.avro.registry;

import com.github.milenkovicm.avro.registry.local.CachedSchemaRegistry;
import com.github.milenkovicm.avro.registry.remote.RestAsyncSchemaRegistry;
import rx.Observable;
import rx.schedulers.Schedulers;

public class DefaultAsyncSchemaRegistry implements AsyncSchemaRegistry {

    final AsyncSchemaRegistry localAsyncSchemaRegistry;
    final AsyncSchemaRegistry remoteAsyncSchemaRegistry;

    public DefaultAsyncSchemaRegistry(String address) {
        this.localAsyncSchemaRegistry = new CachedSchemaRegistry();
        this.remoteAsyncSchemaRegistry = new RestAsyncSchemaRegistry(address);
    }

    @Override
    public Observable<RegistryItem> lookup(String name) {
        return localAsyncSchemaRegistry.lookup(name)
                .switchIfEmpty(
                        remoteAsyncSchemaRegistry
                                .lookup(name).subscribeOn(Schedulers.io())
                                .flatMap(s -> localAsyncSchemaRegistry.put(s))
                );
    }

    @Override
    public Observable<RegistryItem> lookup(long id) {
        return localAsyncSchemaRegistry.lookup(id)
                .switchIfEmpty(
                        remoteAsyncSchemaRegistry
                                .lookup(id).subscribeOn(Schedulers.io())
                                .flatMap(s -> localAsyncSchemaRegistry.put(s))
                );
    }

    @Override
    public Observable<RegistryItem> put(RegistryItem registryItem) {
        throw new UnsupportedOperationException("put not implemented");
    }
}
