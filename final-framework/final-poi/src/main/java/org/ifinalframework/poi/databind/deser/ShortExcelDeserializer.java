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

package org.ifinalframework.poi.databind.deser;

import org.springframework.lang.NonNull;

import org.ifinalframework.poi.databind.ExcelDeserializer;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Deserialize a {@link Short} result from {@link Cell}.
 *
 * @author iimik
 * @version 1.2.4
 * @since 1.2.4
 */
public class ShortExcelDeserializer implements ExcelDeserializer<Short> {

    private static final short ONE = 1;
    private static final short ZERO = 0;

    @Override
    public Short deserialize(@NonNull Cell cell) {
        switch (cell.getCellType()) {
            case NUMERIC:
                return (short) cell.getNumericCellValue();
            case STRING:
                return Double.valueOf(cell.getStringCellValue()).shortValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? ONE : ZERO;
            case BLANK:
            case _NONE:
                return null;
            default:
                throw new IllegalArgumentException("Can not mapping Short from " + cell.getCellType());
        }
    }
}
