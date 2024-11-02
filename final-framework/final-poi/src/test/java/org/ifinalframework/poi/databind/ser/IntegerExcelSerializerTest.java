package org.ifinalframework.poi.databind.ser;

import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author iimik
 * @version 1.2.4
 **/
@ExtendWith(MockitoExtension.class)
class IntegerExcelSerializerTest {

    private final IntegerExcelSerializer serializer = new IntegerExcelSerializer();

    @Mock
    private Cell cell;

    @Test
    void serialize() {
        serializer.serialize(cell, 1);
        ArgumentCaptor<Double> captor = ArgumentCaptor.forClass(Double.class);
        verify(cell, only()).setCellValue(captor.capture());
        assertEquals(1.0, captor.getValue());
    }
}