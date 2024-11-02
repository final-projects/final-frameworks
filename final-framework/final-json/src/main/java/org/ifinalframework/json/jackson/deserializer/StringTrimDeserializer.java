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

package org.ifinalframework.json.jackson.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import org.ifinalframework.auto.service.annotation.AutoService;

import java.io.IOException;
import java.util.Objects;

/**
 * {@link String}类型反序列化器。
 * <p>
 * 该反序列化器在执行时，会将字符串两端的空格去掉
 *
 * @author iimik
 * @version 1.0.0
 * @see StringDeserializer
 * @since 1.0.0
 */
@AutoService(JsonDeserializer.class)
public class StringTrimDeserializer extends StringDeserializer {

    @Override
    public String deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException {
        String value = super.deserialize(p, ctxt);

        if (Objects.nonNull(value)) {
            return value.trim();
        }

        return null;
    }

}
