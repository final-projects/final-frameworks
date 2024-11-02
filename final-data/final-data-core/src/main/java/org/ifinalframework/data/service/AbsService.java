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

package org.ifinalframework.data.service;

import org.springframework.lang.NonNull;

import org.ifinalframework.core.IEntity;
import org.ifinalframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused"})
public interface AbsService<I extends Serializable, T extends IEntity<I>>
        extends Repository<I, T> {

    @Override
    default int save(@NonNull Map<String, Object> params) {
        return getRepository().save(params);
    }

    @Override
    default int insert(@NonNull Map<String, Object> params) {
        return getRepository().insert(params);
    }

    @Override
    default int replace(@NonNull Map<String, Object> params) {
        return getRepository().replace(params);
    }

    @Override
    default int update(@NonNull Map<String, Object> params) {
        return getRepository().update(params);
    }

    @Override
    default int delete(@NonNull Map<String, Object> params) {
        return getRepository().delete(params);
    }

    @Override
    default List<T> select(@NonNull Map<String, Object> params) {
        return getRepository().select(params);
    }

    @Override
    default T selectOne(@NonNull Map<String, Object> params) {
        return getRepository().selectOne(params);
    }

    @Override
    default List<I> selectIds(@NonNull Map<String, Object> params) {
        return getRepository().selectIds(params);
    }

    @Override
    default long selectCount(@NonNull Map<String, Object> params) {
        return getRepository().selectCount(params);
    }

    @Override
    default void truncate(@NonNull Map<String, Object> params) {
        getRepository().truncate(params);
    }

    @NonNull
    Repository<I, T> getRepository();

}
