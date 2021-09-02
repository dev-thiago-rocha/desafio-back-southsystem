package com.thrtec.desafiobacksouthsystem.dto.votingsession.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVotingSessionRequestDto {

    @NotNull
    private Long topicId;

    @Builder.Default
    @NotNull
    @Min(1)
    private Long activePeriod = 1L;

}
