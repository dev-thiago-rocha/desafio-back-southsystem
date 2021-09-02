package com.thrtec.desafiobacksouthsystem.controller.vote.v1;

import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import com.thrtec.desafiobacksouthsystem.service.vote.v1.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/vote")
public class VoteController implements VoteApi {

    private final VoteService voteService;

    @Override
    @PostMapping
    public ResponseEntity<CreateVoteResponseDto> createVote(final CreateVoteRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.createVote(requestDto));
    }

}
