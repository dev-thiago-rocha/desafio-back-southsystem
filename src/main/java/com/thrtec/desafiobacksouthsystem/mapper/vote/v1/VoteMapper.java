package com.thrtec.desafiobacksouthsystem.mapper.vote.v1;

import com.thrtec.desafiobacksouthsystem.domain.Vote;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.thrtec.desafiobacksouthsystem.util.MaskUtil.removeMaskFromCpf;

@Component
public class VoteMapper {

    public Vote toVote(final CreateVoteRequestDto requestDto) {
        return Vote.builder()
                .cpf(removeMaskFromCpf(requestDto.getCpf()))
                .votingSessionId(requestDto.getVotingSessionId())
                .decision(requestDto.getDecision())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public CreateVoteResponseDto toCreateVoteResponseDto(final Vote vote) {
        return CreateVoteResponseDto.builder()
                .id(vote.getId())
                .build();
    }

}
