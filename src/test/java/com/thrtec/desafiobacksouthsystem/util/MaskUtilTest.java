package com.thrtec.desafiobacksouthsystem.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MaskUtilTest {

    @Test
    public void removeMaskFromCpf_withSuccess() {
        // Arrange
        final var cpf = "000.000.000-00";
        final var expected = "00000000000";

        // Act
        final var actual = MaskUtil.removeMaskFromCpf(cpf);

        // Assert
        assertEquals(expected, actual);
    }

}
