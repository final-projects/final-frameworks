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

import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.SpringProperties;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;

/**
 * Create {@link BeanDefinitionReader} instance from bean definition resource, such as {@code xml} or {@code groovy}.
 *
 * @author iimik
 * @version 1.2.4
 * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
 **/
@RequiredArgsConstructor
class BeanDefinitionReaderFactory {

    private final Map<Class<? extends BeanDefinitionReader>, BeanDefinitionReader> instances = new LinkedHashMap<>();

    private final boolean shouldIgnoreXml = SpringProperties.getFlag("spring.xml.ignore");

    private final Environment environment;
    private final ResourceLoader resourceLoader;
    private final BeanDefinitionRegistry beanDefinitionRegistry;

    @NonNull
    public BeanDefinitionReader create(@NonNull String resource) {
        return instance(deduceBeanDefinitionReaderFromResource(resource));
    }

    /**
     * Deduce the {@link BeanDefinitionReader} type from resource.
     * <ul>
     *     <li>return class of {@link GroovyBeanDefinitionReader} when resource endsWith {@code .groovy}.</li>
     *     <li>return default class of {@link XmlBeanDefinitionReader}.</li>
     * </ul>
     *
     * @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsFromImportedResources
     */
    private Class<? extends BeanDefinitionReader> deduceBeanDefinitionReaderFromResource(String resource) {
        if (StringUtils.endsWithIgnoreCase(resource, ".groovy")) {
            return GroovyBeanDefinitionReader.class;
        }

        if (shouldIgnoreXml) {
            throw new UnsupportedOperationException("XML support disabled");
        }

        return XmlBeanDefinitionReader.class;

    }

    private BeanDefinitionReader instance(Class<? extends BeanDefinitionReader> clazz) {

        return instances.computeIfAbsent(clazz, readerClass -> {
            try {
                // Instantiate the specified BeanDefinitionReader
                BeanDefinitionReader reader = readerClass.getConstructor(BeanDefinitionRegistry.class).newInstance(beanDefinitionRegistry);
                // Delegate the current ResourceLoader to it if possible
                if (reader instanceof AbstractBeanDefinitionReader) {
                    AbstractBeanDefinitionReader abdr = ((AbstractBeanDefinitionReader) reader);
                    abdr.setResourceLoader(resourceLoader);
                    abdr.setEnvironment(environment);
                }
                return reader;
            } catch (Throwable ex) {
                throw new IllegalStateException(
                        "Could not instantiate BeanDefinitionReader class [" + readerClass.getName() + "]");
            }
        });

    }
}
