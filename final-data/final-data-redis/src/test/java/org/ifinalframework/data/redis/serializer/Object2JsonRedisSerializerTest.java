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

package org.ifinalframework.data.redis.serializer;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Object2JsonRedisSerializerTest.
 *
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
class Object2JsonRedisSerializerTest {

    @Test
    void serialize() {
        assertNull(Object2JsonRedisSerializer.UTF_8.serialize(null));
        assertArrayEquals("2".getBytes(StandardCharsets.UTF_8), Object2StringRedisSerializer.UTF_8.serialize(2));
    }

    @Test
    void deserialize() {
        assertNull(Object2JsonRedisSerializer.UTF_8.deserialize(null));
        assertEquals("2", Object2StringRedisSerializer.UTF_8.deserialize("2".getBytes(StandardCharsets.UTF_8)));
    }

}
