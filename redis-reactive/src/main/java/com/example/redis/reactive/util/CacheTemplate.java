package com.example.redis.reactive.util;

import reactor.core.publisher.Mono;

public abstract class CacheTemplate<KEY, ENTITY> {

    public Mono<ENTITY> postData(ENTITY entity) {
        return postDataToSource(entity).flatMap(e -> postDataToChach(e).thenReturn(e));
    }

    public Mono<ENTITY> getData(KEY key) {
        return getDataFromCache(key)
                .switchIfEmpty(getDataFromSource(key).flatMap(e -> updateDataFromCache(key, e)));

    }

    public Mono<ENTITY> updateData(KEY key, ENTITY entity) {
        return updateDataFromSource(key, entity).flatMap(e -> deleteDataFromCache(key).thenReturn(e));
    }

    public Mono<Void> deleteData(KEY key) {
        return deleteDataFromSource(key).then(deleteDataFromCache(key));
    }

    abstract protected Mono<ENTITY> getDataFromSource(KEY key);

    abstract protected Mono<ENTITY> getDataFromCache(KEY key);

    abstract protected Mono<ENTITY> postDataToSource(ENTITY entity);

    abstract protected Mono<ENTITY> postDataToChach(ENTITY entity);

    abstract protected Mono<ENTITY> updateDataFromSource(KEY key, ENTITY entity);

    abstract protected Mono<ENTITY> updateDataFromCache(KEY key, ENTITY entity);

    abstract protected Mono<Void> deleteDataFromSource(KEY key);

    abstract protected Mono<Void> deleteDataFromCache(KEY key);

}