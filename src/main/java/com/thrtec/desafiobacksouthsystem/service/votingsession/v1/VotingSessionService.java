package com.thrtec.desafiobacksouthsystem.service.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;

public interface VotingSessionService {

    CreateVotingSessionResponseDto createVotingSession(CreateVotingSessionRequestDto requestDto);

}
