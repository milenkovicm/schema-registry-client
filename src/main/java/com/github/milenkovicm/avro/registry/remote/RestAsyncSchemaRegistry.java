package com.github.milenkovicm.avro.registry.remote;

import rx.Observable;
import rx.functions.Func0;

import com.github.milenkovicm.avro.registry.AsyncSchemaRegistry;
import com.github.milenkovicm.avro.registry.RegistryItem;
import com.github.milenkovicm.avro.registry.SchemaUtil;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.slf4j.Slf4jLogger;

public class RestAsyncSchemaRegistry implements AsyncSchemaRegistry {

    final String address;
    final SchemaRegistryClient client;

    public RestAsyncSchemaRegistry(String address) {
        this.address = address;
        this.client = getInstance(this.address);
    }

    static SchemaRegistryClient getInstance(String address){
        return Feign.builder()
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .target(SchemaRegistryClient.class, address);
    }

    @Override
    public Observable<RegistryItem> lookup(String name) {
        return Observable.defer(new Func0<Observable<RegistryItem>>() {
            @Override
            public Observable<RegistryItem> call() {
                final SchemaRegistryResponse restResponse = client.lookup(name);
                final RegistryItem registryItem = new RegistryItem(restResponse.getSubject(),restResponse.getId(),restResponse.getVersion(), SchemaUtil.parse(restResponse.getSchema()));
                return Observable.just(registryItem);
            }
        });
    }

    @Override
    public Observable<RegistryItem> lookup(long id) {

        return Observable.defer(new Func0<Observable<RegistryItem>>() {
            @Override
            public Observable<RegistryItem> call() {
                final SchemaRegistryResponse restResponse = client.lookup(id);
                final RegistryItem registryItem = new RegistryItem(restResponse.getSubject(),id,restResponse.getVersion(), SchemaUtil.parse(restResponse.getSchema()));
                return Observable.just(registryItem);
            }
        });

    }

    @Override
    public Observable<RegistryItem> put(RegistryItem registryItem) {
        throw new UnsupportedOperationException("put");
    }
}
