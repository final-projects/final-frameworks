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

package org.ifinalframework.json.jackson.serializer;

import com.fasterxml.jackson.core.JsonGenerator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assumptions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * LocalDateSerializerTest.
 *
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class LocalDateSerializerTest {

    private final LocalDateSerializer serializer = new LocalDateSerializer();

    @Mock
    private JsonGenerator jsonGenerator;

    @Test
    void handleType() {
        Assertions.assertEquals(LocalDate.class, serializer.handledType());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "2021-10-01")
    void serialize(LocalDate localDate) throws IOException {

        serializer.serialize(localDate, jsonGenerator, null);

        assumingThat(Objects.isNull(localDate), () -> verify(jsonGenerator, times(1)).writeNull());
        assumingThat(Objects.nonNull(localDate), () -> verify(jsonGenerator, times(1)).writeString(anyString()));

    }

}
