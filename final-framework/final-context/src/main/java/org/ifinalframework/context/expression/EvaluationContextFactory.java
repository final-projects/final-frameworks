/*
 * Copyright 2020-2024 the original author or authors.
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

package org.ifinalframework.context.expression;

import org.springframework.expression.EvaluationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * 把一个给定的对象{@code object}转换为{@link EvaluationContext}，这用对象可能本身就是{@link EvaluationContext}。
 *
 * @author iimik
 * @since 1.5.6
 **/
@FunctionalInterface
public interface EvaluationContextFactory {
    /**
     * 把一个给定的对象{@code object}转换为{@link EvaluationContext}。
     *
     * @param object 给定的对象
     * @return evaluation context。
     */
    @NonNull
    EvaluationContext create(@Nullable Object object);
}
