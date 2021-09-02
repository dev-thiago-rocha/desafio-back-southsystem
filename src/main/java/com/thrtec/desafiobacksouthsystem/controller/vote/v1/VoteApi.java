package com.thrtec.desafiobacksouthsystem.controller.vote.v1;

import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Api(value = "Vote", tags = "Vote")
public interface VoteApi {

    @ApiOperation(value = "Create a new vote", tags = "Vote")
    ResponseEntity<CreateVoteResponseDto> createVote(@Valid @RequestBody CreateVoteRequestDto requestDto);

}
