package com.thrtec.desafiobacksouthsystem.util;

import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaginationUtilTest {

    @Test
    public void toPageable_withSuccess() {
        // Arrange
        final var paginationDto = PaginationDto.builder()
                .page(0)
                .limit(10)
                .build();
        final var expected = PageRequest.of(paginationDto.getPage(), paginationDto.getLimit());

        // Act
        final var actual = PaginationUtil.toPageable(paginationDto);

        // Assert
        assertEquals(expected, actual);
    }

}
