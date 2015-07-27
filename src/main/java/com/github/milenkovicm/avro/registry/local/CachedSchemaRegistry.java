package com.github.milenkovicm.avro.registry.local;

import com.github.milenkovicm.avro.registry.AsyncSchemaRegistry;
import com.github.milenkovicm.avro.registry.RegistryItem;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import rx.Observable;

public class CachedSchemaRegistry implements AsyncSchemaRegistry {

    final Cache<String,RegistryItem> cache;
    final Cache<Long,RegistryItem> reverseCache;

    public CachedSchemaRegistry(){
        cache = CacheBuilder.newBuilder().maximumSize(100).build();
        reverseCache = CacheBuilder.newBuilder().maximumSize(100).build();
    }

    public Observable<RegistryItem> lookup(String name){
        return lookup(cache.getIfPresent(name));
    }

    public Observable<RegistryItem> lookup(long id){
        return lookup(reverseCache.getIfPresent(id));
    }

    @Override
    public Observable<RegistryItem> put(RegistryItem registryItem) {
        // TODO: needs cleanup
        if (registryItem.getSubject() != null) {
            cache.put(registryItem.getSubject(), registryItem);
        }
        if (registryItem.getId() != null) {
            reverseCache.put(registryItem.getId(), registryItem);
        }
        return Observable.just(reverseCache.getIfPresent(registryItem.getId()));
    }

    Observable<RegistryItem> lookup(RegistryItem registryItem) {
        if (registryItem == null) {
            return Observable.empty();
        } else {
            return Observable.just(registryItem);
        }
    }
}
