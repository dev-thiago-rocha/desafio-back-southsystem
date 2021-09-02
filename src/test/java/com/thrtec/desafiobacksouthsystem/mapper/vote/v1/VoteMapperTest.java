package com.thrtec.desafiobacksouthsystem.mapper.vote.v1;

import com.thrtec.desafiobacksouthsystem.domain.Vote;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VoteDecisionType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.thrtec.desafiobacksouthsystem.util.MaskUtil.removeMaskFromCpf;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VoteMapperTest {

    @InjectMocks
    private VoteMapper voteMapper;

    @Test
    public void toVote_withSuccess() {
        // Arrange
        final var now = LocalDateTime.now();
        final var requestDto = CreateVoteRequestDto.builder()
                .cpf("000.000.000-00")
                .decision(VoteDecisionType.SIM)
                .votingSessionId(1L)
                .build();
        final var expected = Vote.builder()
                .cpf(removeMaskFromCpf(requestDto.getCpf()))
                .votingSessionId(requestDto.getVotingSessionId())
                .decision(requestDto.getDecision())
                .createdAt(now)
                .build();

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        // Act
        final var actual = voteMapper.toVote(requestDto);

        // Assert
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

    @Test
    public void toCreateVoteResponseDto_withSuccess() {
        // Arrange
        final var vote = Vote.builder()
                .id(1L)
                .build();
        final var expected = CreateVoteResponseDto.builder()
                .id(vote.getId())
                .build();

        // Act
        final var actual = voteMapper.toCreateVoteResponseDto(vote);

        // Assert
        assertEquals(expected, actual);
    }

}
