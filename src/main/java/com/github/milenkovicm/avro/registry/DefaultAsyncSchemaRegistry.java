package com.github.milenkovicm.avro.registry;

import com.github.milenkovicm.avro.registry.local.CachedSchemaRegistry;
import com.github.milenkovicm.avro.registry.remote.RestAsyncSchemaRegistry;
import rx.Observable;
import rx.functions.Func1;
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
                                .flatMap(new Func1<RegistryItem, Observable<? extends RegistryItem>>() {
                                    @Override
                                    public Observable<? extends RegistryItem> call(RegistryItem registryItem) {
                                        return localAsyncSchemaRegistry.put(registryItem);
                                    }
                                })
                );
    }

    @Override
    public Observable<RegistryItem> lookup(long id) {
        return localAsyncSchemaRegistry.lookup(id)
                .switchIfEmpty(
                        remoteAsyncSchemaRegistry
                                .lookup(id).subscribeOn(Schedulers.io())
                                .flatMap(new Func1<RegistryItem, Observable<? extends RegistryItem>>() {
                                    @Override
                                    public Observable<? extends RegistryItem> call(RegistryItem registryItem) {
                                        return localAsyncSchemaRegistry.put(registryItem);
                                    }
                                })
                );
    }

    @Override
    public Observable<RegistryItem> put(RegistryItem registryItem) {
        return remoteAsyncSchemaRegistry.put(registryItem).subscribeOn(Schedulers.io());
    }
}
