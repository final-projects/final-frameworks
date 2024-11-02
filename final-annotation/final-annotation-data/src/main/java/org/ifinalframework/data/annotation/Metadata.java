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
 *
 */

package org.ifinalframework.data.annotation;

import java.io.Serializable;

import org.ifinalframework.data.query.AndOr;

import lombok.Data;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class Metadata implements Serializable {

    /**
     * test value
     */
    private String test;
    /**
     * selective test value
     */
    private String selectiveTest;

    private AndOr andOr;

    /**
     * property name
     */
    private String property;

    /**
     * column name
     */
    private String column;

    /**
     * value
     */
    private String value;

    /**
     * value java type
     */
    private Class<?> javaType;

    /**
     * value type handler
     */
    private Class<?> typeHandler;

}

