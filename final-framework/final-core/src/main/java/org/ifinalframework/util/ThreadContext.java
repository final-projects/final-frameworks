/*
 * Copyright 2020-2022 the original author or authors.
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

package org.ifinalframework.util;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.NonNull;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
final class ThreadContext {

    private static final ThreadLocal<Map<String, Object>> cache = ThreadLocal.withInitial(LinkedHashMap::new);

    private ThreadContext() {
    }

    static void set(final @NonNull String key, final Object value) {
        cache.get().put(key, value);
    }

    static Object get(final @NonNull String key) {
        return cache.get().get(key);
    }

    static boolean containsKey(final @NonNull String key) {
        return cache.get().containsKey(key);
    }

    public static boolean containsValue(final @NonNull Object value) {
        return cache.get().containsValue(value);
    }

    static Object remove(final @NonNull String key) {
        return cache.get().remove(key);
    }

    static void remove() {
        cache.remove();
    }

}
