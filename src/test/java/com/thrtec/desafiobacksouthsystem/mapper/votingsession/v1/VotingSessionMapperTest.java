package com.thrtec.desafiobacksouthsystem.mapper.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VotingSessionMapperTest {

    @InjectMocks
    private VotingSessionMapper votingSessionMapper;

    @Test
    public void toVotingSession_withSuccess() {
        // Arrange
        final var requestDto = CreateVotingSessionRequestDto.builder()
                .topicId(1L)
                .activePeriod(1L)
                .build();

        final var now = LocalDateTime.now();
        final var finishDate = now.plusMinutes(requestDto.getActivePeriod());

        final var expected = VotingSession.builder()
                .createdAt(now)
                .finishDate(finishDate)
                .status(VotingSessionStatusType.ACTIVE)
                .topicId(requestDto.getTopicId())
                .build();

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        // Act
        final var actual = votingSessionMapper.toVotingSession(requestDto);

        // Assert
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

    @Test
    public void toCreateVotingSessionResponseDto_withSuccess() {
        // Arrange
        final var now = LocalDateTime.now();
        final var votingSession = VotingSession.builder()
                .id(1L)
                .finishDate(now)
                .build();
        final var expected = CreateVotingSessionResponseDto.builder()
                .id(votingSession.getId())
                .finishDate(votingSession.getFinishDate())
                .build();

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        // Act
        final var actual = votingSessionMapper.toCreateVotingSessionResponseDto(votingSession);

        // Assert
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

}
