package org.ifinalframework.poi.databind.ser;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import org.ifinalframework.poi.databind.ExcelSerializer;

import org.apache.poi.ss.usermodel.Cell;

import java.util.Optional;

/**
 * @author iimik
 * @version 1.2.4
 **/
public class FloatExcelSerializer implements ExcelSerializer<Float> {
    @Override
    public void serialize(@NonNull Cell cell, @Nullable Float value) {
        Optional.ofNullable(value).ifPresent(cell::setCellValue);
    }
}
