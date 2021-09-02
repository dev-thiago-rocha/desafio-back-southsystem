package com.thrtec.desafiobacksouthsystem.dto.vote.v1;

import com.thrtec.desafiobacksouthsystem.enumeration.VoteDecisionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateVoteRequestDto {

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpf;

    @NotNull
    private VoteDecisionType decision;

    @NotNull
    private Long votingSessionId;
}
