package com.thrtec.desafiobacksouthsystem.controller.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import com.thrtec.desafiobacksouthsystem.service.votingsession.v1.VotingSessionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VotingSessionControllerTest {

    @InjectMocks
    private VotingSessionController votingSessionController;

    @Mock
    private VotingSessionService votingSessionService;

    @Test
    public void createVotingSession_withSuccess() {
        // Arrange
        final var now = LocalDateTime.now();
        final var requestDto = CreateVotingSessionRequestDto.builder()
                .topicId(1L)
                .activePeriod(1L)
                .build();
        final var responseDto = CreateVotingSessionResponseDto.builder()
                .id(1L)
                .finishDate(now.plusMinutes(requestDto.getActivePeriod()))
                .build();
        final var expected = ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        when(votingSessionService.createVotingSession(requestDto)).thenReturn(responseDto);

        // Act
        final var actual = votingSessionController.createVotingSession(requestDto);

        // Assert
        verify(votingSessionService).createVotingSession(requestDto);
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

}
