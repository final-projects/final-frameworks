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

import org.ifinalframework.data.annotation.YN;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * EnumsTest.
 *
 * @author iimik
 * @version 1.0.0
 * @since 1.0.0
 */
class EnumsTest {

    @AllArgsConstructor
    @Getter
    public enum Yn {
        YES(1), NO(0);

        private final Integer value;

        public static Yn valueOf(final Integer code) {
            return Arrays.stream(Yn.values()).filter(it -> it.getValue().equals(code)).findFirst().orElse(null);
        }

    }

    @Test
    void findEnum() {
        Assertions.assertEquals(Yn.YES, Enums.findEnum(Yn.class, "valueOf", Integer.class, 1));
    }

    @Test
    void notFoundCreatorMethod() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Enums.findEnum(Yn.class, "noMethod", Integer.class, 1);
        });
    }

    @Test
    void findDesc() {
        Assertions.assertEquals(YN.YES.getDesc(), Enums.findDesc(YN.YES, "getDesc"));
    }

    @Test
    void getEnumCodeOfI18N() {

        Assertions.assertEquals(String.join(".", Yn.class.getCanonicalName(), Yn.YES.name().toLowerCase(Locale.ROOT)),
                Enums.getEnumCodeOfI18N(Yn.YES));

    }

}
