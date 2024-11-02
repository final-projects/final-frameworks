/*
 * Copyright 2020-2021 the original author or authors.
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

package org.ifinalframework.json;

import org.ifinalframework.json.jackson.JacksonJsonService;

import java.util.Objects;

/**
 * Json service register.
 *
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
public final class JsonRegistry {

    private static final JsonRegistry instance = new JsonRegistry();

    private JsonService defaultJsonService = new JacksonJsonService();

    private JsonService jsonService;

    private JsonRegistry() {
    }

    public static JsonRegistry getInstance() {
        return instance;
    }

    public JsonService getJsonService() {

        if (Objects.nonNull(jsonService)) {
            return jsonService;
        }

        return defaultJsonService;
    }

    public void setDefaultJsonService(final JsonService jsonService) {
        this.defaultJsonService = Objects.requireNonNull(jsonService);
    }

    public synchronized void register(final JsonService jsonService) {
        this.jsonService = Objects.requireNonNull(jsonService, "json service can not be null");
    }

}
