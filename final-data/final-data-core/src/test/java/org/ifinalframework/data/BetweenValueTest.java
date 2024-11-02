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

package org.ifinalframework.data;

import javassist.ClassPool;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.lang.Nullable;

import org.ifinalframework.data.query.BetweenValue;
import org.ifinalframework.javassist.AbstractNestablePropertyAccessorJavaAssistProcessor;
import org.ifinalframework.util.format.LocalDateTimeFormatters;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * BetweenValueTest.
 *
 * @author iimik
 * @version 1.5.0
 * @since 1.5.0
 */
@Slf4j
class BetweenValueTest {

    @Setter
    @Getter
    static class Bean {
        BetweenValue<LocalDateTime> betweenValue;
        BetweenValue<Long> longBetweenValue;
    }


    @Test
    @SneakyThrows
    void test() {

        final ClassPool classPool = ClassPool.getDefault();
        new AbstractNestablePropertyAccessorJavaAssistProcessor().process(classPool);

        final Bean bean = new Bean();
        final BeanWrapperImpl beanWrapper = new BeanWrapperImpl(bean);
        final DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        conversionService.addConverter(new Converter<String, LocalDateTime>() {
            @Nullable
            @Override
            public LocalDateTime convert(String source) {
                return LocalDateTimeFormatters.DEFAULT.parse(source);
            }
        });
        beanWrapper.setConversionService(conversionService);
        beanWrapper.setAutoGrowNestedPaths(true);
        beanWrapper.setPropertyValue("betweenValue.min", "2023-04-12 00:00:00");
        beanWrapper.setPropertyValue("longBetweenValue.min", "1");
        logger.info("{}", bean.betweenValue.getMin());
    }

}