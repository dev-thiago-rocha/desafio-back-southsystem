package com.thrtec.desafiobacksouthsystem.dto.exception.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponseDto {

    private String message;

}
