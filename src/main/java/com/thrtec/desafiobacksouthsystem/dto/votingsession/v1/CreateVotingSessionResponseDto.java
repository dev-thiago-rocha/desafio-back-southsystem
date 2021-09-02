package com.thrtec.desafiobacksouthsystem.dto.votingsession.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVotingSessionResponseDto {

    private Long id;
    private LocalDateTime finishDate;

}
