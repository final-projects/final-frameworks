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

package org.ifinalframework.monitor.trace;

import org.springframework.lang.NonNull;

import org.ifinalframework.aop.InterceptorHandler;
import org.ifinalframework.aop.InvocationContext;
import org.ifinalframework.context.exception.InternalServerException;
import org.ifinalframework.context.expression.MethodMetadata;
import org.ifinalframework.core.IException;
import org.ifinalframework.json.Json;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Parameter;
import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
public class TraceLoggerInterceptorHandler implements InterceptorHandler<Tracer, Boolean> {

    private static final String TRACE_START = "traceStart";

    private static final String TAB = "    ";

    ThreadLocal<AtomicInteger> methodDeepCounter = new ThreadLocal<>();

    @Override
    public Object before(final @NonNull Tracer executor, final @NonNull InvocationContext context,
                         final @NonNull Boolean annotation) {

        context.addAttribute(TRACE_START, System.currentTimeMillis());

        MethodMetadata metadata = context.metadata();
        final Logger logger = LoggerFactory.getLogger(metadata.getTargetClass() + "." + metadata.getMethod().getName());

        String tab = tab();

        logger.info("====START====================================================START====");
        logger.info("Class:{}", metadata.getTargetClass().getName());
        logger.info("{},Method:{}", tab, metadata.getMethod().getName());

        Object[] args = context.args();
        Parameter[] parameters = metadata.getMethod().getParameters();

        for (int i = 0; i < args.length; i++) {
            logger.info("{},Parameter: {}={}", tab, parameters[i].getName(), args[i]);
        }

        return null;
    }

    private AtomicInteger counter() {
        AtomicInteger atomicInteger = methodDeepCounter.get();
        if (Objects.isNull(atomicInteger)) {
            atomicInteger = new AtomicInteger(0);
            methodDeepCounter.set(atomicInteger);
        }
        return atomicInteger;
    }

    private String tab() {

        final AtomicInteger atomicInteger = counter();

        StringBuilder sb = new StringBuilder();

        int count = atomicInteger.get();
        for (int i = 0; i < count; i++) {
            sb.append(TAB);
        }

        return sb.toString();
    }

    @Override
    public void after(final @NonNull Tracer executor, final @NonNull InvocationContext context,
                      final @NonNull Boolean annotation, final Object result,
                      final Throwable throwable) {

        MethodMetadata metadata = context.metadata();
        final Logger logger = LoggerFactory.getLogger(metadata.getTargetClass() + "." + metadata.getMethod().getName());

        final AtomicInteger atomicInteger = counter();
        final String tab = tab();

        long start = context.getAttribute(TRACE_START);
        final long duration = System.currentTimeMillis() - start;
        if (logger.isInfoEnabled()) {
            logger.info("{},Duration:{}", tab, Duration.ofMinutes(duration));
        }

        if (Objects.nonNull(throwable)) {
            if (throwable instanceof IException && !(throwable instanceof InternalServerException)) {
                IException exception = (IException) throwable;
                logger.warn("exception: code={},message={}", exception.getCode(), exception.getMessage());
            } else {
                logger.error("exception: {}", throwable.getMessage(), throwable);
            }
        } else {
            if (logger.isInfoEnabled()) {
                logger.info("{},Result: {}", tab, Json.toJson(result));
            }
        }

        int i = atomicInteger.decrementAndGet();
        if (i == 0) {
            methodDeepCounter.remove();
        }

        logger.info("====END====================================================END====");

    }

}
