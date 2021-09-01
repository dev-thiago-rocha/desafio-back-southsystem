package com.thrtec.desafiobacksouthsystem.dto.topic.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindTopicResponseDto {

    private Long id;
    private String title;

}
