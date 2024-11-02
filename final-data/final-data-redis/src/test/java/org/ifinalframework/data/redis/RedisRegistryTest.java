/*
 * Copyright 2020-2023 the original author or authors.
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

import org.springframework.data.redis.core.StringRedisTemplate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * RedisRegistryTest.
 *
 * @author iimik
 * @version 1.5.0
 * @since 1.5.0
 */
class RedisRegistryTest {

    @Test
    void test() {
        final StringRedisTemplate redisTemplate = new StringRedisTemplate();
        RedisRegistry.getInstance().setRedisTemplate(redisTemplate);

        Assertions.assertEquals(redisTemplate, RedisRegistry.getInstance().template());
        Assertions.assertEquals(redisTemplate, RedisRegistry.getInstance().key());
        Assertions.assertEquals(redisTemplate.opsForValue(), RedisRegistry.getInstance().value());
        Assertions.assertEquals(redisTemplate.opsForHash().getClass(), RedisRegistry.getInstance().hash().getClass());
        Assertions.assertEquals(redisTemplate.opsForList(), RedisRegistry.getInstance().list());
        Assertions.assertEquals(redisTemplate.opsForSet(), RedisRegistry.getInstance().set());
        Assertions.assertEquals(redisTemplate.opsForZSet(), RedisRegistry.getInstance().zset());

    }

}