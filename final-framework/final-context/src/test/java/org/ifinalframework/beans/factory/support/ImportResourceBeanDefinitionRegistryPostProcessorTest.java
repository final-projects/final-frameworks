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

package org.ifinalframework.beans.factory.support;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author iimik
 * @version 1.2.4
 **/
@ExtendWith(MockitoExtension.class)
class ImportResourceBeanDefinitionRegistryPostProcessorTest {

    @Test
    void processBeanDefinitionRegistry() {
        System.setProperty("spring.xml.ignore", "false");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ImportResourceBeanDefinitionRegistryPostProcessor.class);
        context.refresh();
    }
}