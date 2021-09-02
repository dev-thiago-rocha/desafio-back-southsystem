package com.thrtec.desafiobacksouthsystem.service.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import com.thrtec.desafiobacksouthsystem.mapper.votingsession.v1.VotingSessionMapper;
import com.thrtec.desafiobacksouthsystem.repository.VotingSessionRepository;
import com.thrtec.desafiobacksouthsystem.validator.votingsession.v1.VotingSessionValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotingSessionServiceImplTest {

    @InjectMocks
    private VotingSessionServiceImpl votingSessionService;

    @Mock
    private VotingSessionMapper votingSessionMapper;

    @Mock
    private VotingSessionValidator votingSessionValidator;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Test
    public void createVotingSession_withSuccess() {
        // Arrange
        final var requestDto = CreateVotingSessionRequestDto.builder()
                .topicId(1L)
                .activePeriod(1L)
                .build();

        final var now = LocalDateTime.now();
        final var finishDate = now.plusMinutes(requestDto.getActivePeriod());

        final var votingSession = VotingSession.builder()
                .id(1L)
                .createdAt(now)
                .finishDate(finishDate)
                .status(VotingSessionStatusType.ACTIVE)
                .topicId(requestDto.getTopicId())
                .build();
        final var expected = CreateVotingSessionResponseDto.builder()
                .id(votingSession.getId())
                .finishDate(votingSession.getFinishDate())
                .build();

        when(votingSessionMapper.toVotingSession(requestDto)).thenReturn(votingSession);
        doNothing().when(votingSessionValidator).validateCreateVotingSession(votingSession);
        when(votingSessionRepository.save(votingSession)).thenReturn(votingSession);
        when(votingSessionMapper.toCreateVotingSessionResponseDto(votingSession)).thenReturn(expected);

        // Act
        final var actual = votingSessionService.createVotingSession(requestDto);

        // Assert
        verify(votingSessionMapper).toVotingSession(requestDto);
        verify(votingSessionValidator).validateCreateVotingSession(votingSession);
        verify(votingSessionRepository).save(votingSession);
        verify(votingSessionMapper).toCreateVotingSessionResponseDto(votingSession);
        assertEquals(expected, actual);
    }

}
