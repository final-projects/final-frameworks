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

package org.ifinalframework.data.mybatis.javassist;

import javassist.ClassPool;

import org.ifinalframework.data.annotation.AbsRecord;
import org.ifinalframework.data.mybatis.mapper.AbsMapper;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.session.Configuration;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;


/**
 * MapperAnnotationBuilderJavaAssistProcessorTest.
 *
 * @author iimik
 * @version 1.5.1
 * @since 1.5.1
 */
class MapperAnnotationBuilderJavaAssistProcessorTest {

    private interface AbsEntityMapper extends AbsMapper<Long, AbsRecord> {
    }

    @SneakyThrows
    @Test
    void process() {
        final MapperAnnotationBuilderJavaAssistProcessor processor = new MapperAnnotationBuilderJavaAssistProcessor();
        processor.process(ClassPool.getDefault());
        final MapperAnnotationBuilder builder = new MapperAnnotationBuilder(new Configuration(), AbsEntityMapper.class);
        builder.parse();
    }
}