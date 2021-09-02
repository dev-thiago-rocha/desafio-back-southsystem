package com.thrtec.desafiobacksouthsystem.controller.vote.v1;

import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VoteDecisionType;
import com.thrtec.desafiobacksouthsystem.service.vote.v1.VoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VoteControllerTest {

    @InjectMocks
    private VoteController voteController;

    @Mock
    private VoteService voteService;

    @Test
    public void createVote_withSuccess() {
        // Arrange
        final var requestDto = CreateVoteRequestDto.builder()
                .cpf("000.000.000-00")
                .decision(VoteDecisionType.SIM)
                .votingSessionId(1L)
                .build();
        final var responseDto = CreateVoteResponseDto.builder()
                .id(1L)
                .build();
        final var expected = ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        when(voteService.createVote(requestDto)).thenReturn(responseDto);

        // Act
        final var actual = voteController.createVote(requestDto);

        // Assert
        verify(voteService).createVote(requestDto);
        assertEquals(expected, actual);
    }

}
