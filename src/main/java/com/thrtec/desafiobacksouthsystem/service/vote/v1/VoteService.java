package com.thrtec.desafiobacksouthsystem.service.vote.v1;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionResultType;

public interface VoteService {

    CreateVoteResponseDto createVote(CreateVoteRequestDto requestDto);

    VotingSessionResultType countVotesAndDefineResult(VotingSession votingSession);

}
