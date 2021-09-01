package com.thrtec.desafiobacksouthsystem.dto.pagination.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto {

    @Builder.Default
    @Min(0)
    private Integer page = 0;

    @Builder.Default
    @Min(1)
    @Max(100)
    private Integer limit = 5;

}
