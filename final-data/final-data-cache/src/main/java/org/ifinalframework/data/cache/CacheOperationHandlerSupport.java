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

package org.ifinalframework.data.cache;

import org.springframework.expression.EvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import org.ifinalframework.aop.OperationHandlerSupport;
import org.ifinalframework.context.expression.MethodMetadata;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CacheOperationHandlerSupport extends OperationHandlerSupport {

    @Nullable
    Object generateKey(@NonNull String[] keys, @NonNull String delimiter, @NonNull MethodMetadata metadata,
                       @NonNull EvaluationContext evaluationContext);

    @Nullable
    Object generateField(@NonNull String[] fields, @NonNull String delimiter, @NonNull MethodMetadata metadata,
                         @NonNull EvaluationContext evaluationContext);

    @Nullable
    Object generateValue(@NonNull String value, @NonNull MethodMetadata metadata, EvaluationContext evaluationContext);

    @Nullable
    <T> T generateValue(@NonNull String value, @NonNull MethodMetadata metadata, EvaluationContext evaluationContext,
                        Class<T> clazz);

    boolean isConditionPassing(@NonNull String condition, @NonNull MethodMetadata metadata,
                               EvaluationContext evaluationContext);

    @Nullable
    Object generateExpire(@NonNull String expire, @NonNull MethodMetadata metadata,
                          EvaluationContext evaluationContext);

}
