package com.thrtec.desafiobacksouthsystem.util;

import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaginationUtil {

    public static Pageable toPageable(PaginationDto paginationDto) {
        return PageRequest.of(paginationDto.getPage(), paginationDto.getLimit());
    }

}
