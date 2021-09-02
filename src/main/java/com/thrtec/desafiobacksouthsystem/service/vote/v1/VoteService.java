package com.thrtec.desafiobacksouthsystem.service.vote.v1;

import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;

public interface VoteService {

    CreateVoteResponseDto createVote(CreateVoteRequestDto requestDto);

}
