/*
 * Copyright 2020-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ifinalframework.data.cache;

import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import org.ifinalframework.cache.annotation.Cache;
import org.ifinalframework.data.redis.Redis;
import org.ifinalframework.json.Json;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
@Primary
@Component
public class RedisCache implements Cache {

    private static final Long ONE = 1L;

    @Override
    public boolean lock(final @NonNull Object key, final @NonNull Object value, final @Nullable Long ttl,
                        final @Nullable TimeUnit timeUnit) {

        return Redis.lock(key, value, ttl, timeUnit);
    }

    @Override
    public boolean unlock(final @NonNull Object key, final @NonNull Object value) {

        return Redis.unlock(key, value);
    }

    @Override
    public boolean isExists(final @NonNull Object key, final @Nullable Object field) {

        return Boolean.TRUE.equals(field == null ? Redis.key().hasKey(key) : Redis.hash().hasKey(key, field));
    }

    @Override
    public boolean expire(final @NonNull Object key, final long ttl, final @NonNull TimeUnit timeUnit) {

        return Boolean.TRUE.equals(Redis.key().expire(key, ttl, timeUnit));
    }

    @Override
    public void set(final @NonNull Object key, final @Nullable Object field, final @Nullable Object value,
                    final @Nullable Long ttl,
                    final @Nullable TimeUnit timeUnit, final @Nullable Class<?> view) {

        final Object cacheValue = view == null ? Json.toJson(value) : Json.toJson(value, view);
        if (field == null) {
            if (ttl != null && ttl > 0 && timeUnit != null) {
                Redis.value().set(key, cacheValue, ttl, timeUnit);
            } else {
                Redis.value().set(key, cacheValue);
            }
        } else {
            Redis.hash().put(key, field, cacheValue);
            if (ttl != null && ttl > 0 && timeUnit != null) {
                Redis.key().expire(key, ttl, timeUnit);
            }
        }
    }

    @Override
    public <T> T get(final @NonNull Object key, final @Nullable Object field, final @NonNull Type type,
                     final @Nullable Class<?> view) {

        final Object cacheValue = field == null ? Redis.value().get(key) : Redis.hash().get(key, field);
        if (cacheValue == null) {
            return null;
        }
        final String json = cacheValue.toString();
        return view == null ? Json.toObject(json, type) : Json.toObject(json, type, view);
    }

    @Override
    public Long increment(final @NonNull Object key, final @Nullable Object field, final @NonNull Long value) {

        return field == null ? Redis.value().increment(key, value) : Redis.hash().increment(key, field, value);
    }

    @Override
    public Double increment(final @NonNull Object key, final @Nullable Object field, final @NonNull Double value) {

        return field == null ? Redis.value().increment(key, value) : Redis.hash().increment(key, field, value);
    }

    @Override
    public Boolean del(final @NonNull Object key, final @Nullable Object field) {

        return field == null ? Boolean.TRUE.equals(Redis.key().delete(key))
                : ONE.equals(Redis.hash().delete(key, field));
    }

}
