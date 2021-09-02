package com.thrtec.desafiobacksouthsystem.exception;

import com.thrtec.desafiobacksouthsystem.dto.exception.v1.ExceptionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(1)
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<ExceptionResponseDto> handleValidationException(ValidationException exception) {
        log.error(exception.getMessage(), exception);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ExceptionResponseDto.builder().message(exception.getMessage()).build());
    }

}
