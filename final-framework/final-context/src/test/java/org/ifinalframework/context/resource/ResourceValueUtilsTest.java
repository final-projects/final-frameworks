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

package org.ifinalframework.context.resource;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * ResourceValueUtilsTest.
 *
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
class ResourceValueUtilsTest {

    @Test
    void resourceValues() {
        ResourceValueEntity entity = new ResourceValueEntity();
        Collection<ResourceValueHolder> holders = ResourceValueUtils
                .findAllResourceValueHolders(entity, ResourceValueEntity.class);

        assertEquals(2, holders.size());

    }

}
