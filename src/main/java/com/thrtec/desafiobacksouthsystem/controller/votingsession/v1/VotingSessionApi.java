package com.thrtec.desafiobacksouthsystem.controller.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(value = "VotingSession", tags = "VotingSession")
public interface VotingSessionApi {

    @ApiOperation(value = "Create a new voting session", tags = "VotingSession")
    ResponseEntity<CreateVotingSessionResponseDto> createVotingSession(@Valid @RequestBody CreateVotingSessionRequestDto requestDto);

}
