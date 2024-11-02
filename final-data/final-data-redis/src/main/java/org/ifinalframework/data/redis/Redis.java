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

package org.ifinalframework.data.redis;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.lang.NonNull;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public interface Redis {

    static RedisTemplate template() {
        return RedisRegistry.getInstance().template();
    }

    @NonNull
    static RedisOperations key() {
        return RedisRegistry.getInstance().key();
    }

    static ValueOperations value() {
        return RedisRegistry.getInstance().value();
    }

    static HashOperations hash() {
        return RedisRegistry.getInstance().hash();
    }

    static ListOperations list() {
        return RedisRegistry.getInstance().list();
    }

    static SetOperations set() {
        return RedisRegistry.getInstance().set();
    }

    static ZSetOperations zset() {
        return RedisRegistry.getInstance().zset();
    }

    static boolean lock(final Object key, final Object value, final Long timeout, final TimeUnit unit) {

        if (timeout != null && unit != null) {
            return Boolean.TRUE.equals(value().setIfAbsent(key, value, timeout, unit));
        } else {
            return Boolean.TRUE.equals(value().setIfAbsent(key, value));

        }

    }

    static boolean unlock(final Object key, final Object value) {
        final String script = "if redis.call('get', KEYS[1]) == ARGV[1] "
                + "then return redis.call('del', KEYS[1]) else return 0 end";
        return Boolean.TRUE.equals(
                key().execute(new DefaultRedisScript<>(script, Boolean.class), Collections.singletonList(key), value));
    }

}
