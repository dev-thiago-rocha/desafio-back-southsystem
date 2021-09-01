package com.thrtec.desafiobacksouthsystem.dto.topic.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTopicRequestDto {

    @Size(max = 255)
    @NotBlank
    private String title;

}
