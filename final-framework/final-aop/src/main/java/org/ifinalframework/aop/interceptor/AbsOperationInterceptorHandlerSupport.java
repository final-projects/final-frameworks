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

package org.ifinalframework.aop.interceptor;

import org.springframework.expression.EvaluationContext;
import org.springframework.lang.NonNull;

import org.ifinalframework.aop.ExpressionEvaluator;
import org.ifinalframework.aop.InvocationContext;
import org.ifinalframework.aop.OperationHandlerSupport;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
public class AbsOperationInterceptorHandlerSupport implements OperationHandlerSupport {

    private final ExpressionEvaluator evaluator;

    public AbsOperationInterceptorHandlerSupport(final ExpressionEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    @Override
    @NonNull
    public EvaluationContext createEvaluationContext(final @NonNull InvocationContext context, final Object result,
                                                     final Throwable e) {

        return evaluator.createEvaluationContext(context.metadata().getMethod(), context.args(),
                context.target(), context.metadata().getTargetClass(), context.metadata().getTargetMethod(), result, e);

    }

}
