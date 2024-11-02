/*
 * Copyright 2020-2021 the original author or authors.
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

package org.ifinalframework.data.mybatis.interceptor;

import org.springframework.core.annotation.Order;

import org.ifinalframework.core.IEntity;
import org.ifinalframework.data.mybatis.mapper.AbsMapper;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Order(0)
//@Component
@Intercepts({
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class, CacheKey.class,
                BoundSql.class}),
})
@Deprecated
public class InlineSelectInterceptor implements Interceptor {

    /**
     * @see MapperBuilderAssistant#getStatementResultMaps(String, Class, String)
     * @see AbsMapper#select(String, Class, Collection, Query)
     * @see AbsMapper#selectOne(String, Class, Serializable, Query)
     */
    private static final Pattern PATTERN = Pattern.compile("(\\.select|\\.selectOne|-Inline)$");

    @Override
    public Object intercept(final Invocation invocation) throws Throwable {

        Executor executor = (Executor) invocation.getTarget();
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object param = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler<?> resultHandler = (ResultHandler<?>) args[3];
        final String id = ms.getId();
        final List<ResultMap> resultMaps = ms.getResultMaps();

        if (resultMaps.size() == 1 && PATTERN.matcher(id).find()
                && IEntity.class.isAssignableFrom(resultMaps.get(0).getType())) {
            final MappedStatement mappedStatement = newFinalMappedStatement(ms, id + "-final");
            return executor.query(mappedStatement, param, rowBounds, resultHandler);
        }

        return invocation.proceed();

    }

    public MappedStatement newFinalMappedStatement(final MappedStatement ms, final String newMsId) {

        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), newMsId, ms.getSqlSource(),
                ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        //count查询返回值int
        List<ResultMap> resultMaps = new ArrayList<>();
        Class<?> type = ms.getResultMaps().get(0).getType();
        final ResultMap resultMap = ms.getConfiguration().getResultMap(type.getName());
        resultMaps.add(resultMap);
        builder.resultMaps(resultMaps);
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

}
