package com.thrtec.desafiobacksouthsystem.controller.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import com.thrtec.desafiobacksouthsystem.service.votingsession.v1.VotingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/voting-session")
public class VotingSessionController implements VotingSessionApi {

    private final VotingSessionService votingSessionService;

    @Override
    @PostMapping
    public ResponseEntity<CreateVotingSessionResponseDto> createVotingSession(final CreateVotingSessionRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(votingSessionService.createVotingSession(requestDto));
    }

}
