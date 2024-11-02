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

package org.ifinalframework.spiriter.jdbc.service;

import org.springframework.lang.Nullable;

import javax.sql.DataSource;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
public interface DataSourceService {

    @Nullable
    Map<String, DataSource> getDataSources();

    @Nullable
    default DataSource getDataSource(String name) {
        return Optional.ofNullable(getDataSources()).orElse(Collections.emptyMap()).get(name);
    }


}
