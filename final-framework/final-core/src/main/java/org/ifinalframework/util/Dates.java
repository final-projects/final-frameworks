/*
 * Copyright 2020-2022 the original author or authors.
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

package org.ifinalframework.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * @author iimik
 * @version 1.0.0
 * @see LocaleContextHolder
 * @since 1.0.0
 */
public interface Dates {

    @Nullable
    static Date to(final @Nullable LocalDateTime localDateTime) {

        return Optional.ofNullable(localDateTime)
                .map(it -> Date.from(it.atZone(LocaleContextHolder.getTimeZone().toZoneId()).toInstant()))
                .orElse(null);
    }

    @Nullable
    static LocalDateTime from(final @Nullable Date date) {

        return Optional.ofNullable(date)
                .map(it -> it.toInstant().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toLocalDateTime())
                .orElse(null);
    }

}
