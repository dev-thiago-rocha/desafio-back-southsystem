package com.thrtec.desafiobacksouthsystem.service.vote.v1;

import com.thrtec.desafiobacksouthsystem.domain.Vote;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VoteDecisionType;
import com.thrtec.desafiobacksouthsystem.mapper.vote.v1.VoteMapper;
import com.thrtec.desafiobacksouthsystem.repository.VoteRepository;
import com.thrtec.desafiobacksouthsystem.validator.vote.v1.VoteValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.thrtec.desafiobacksouthsystem.util.MaskUtil.removeMaskFromCpf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceImplTest {

    @InjectMocks
    private VoteServiceImpl voteService;

    @Mock
    private VoteMapper voteMapper;

    @Mock
    private VoteValidator voteValidator;

    @Mock
    private VoteRepository voteRepository;

    @Test
    public void createVote_withSuccess() {
        // Arrange
        final var now = LocalDateTime.now();
        final var requestDto = CreateVoteRequestDto.builder()
                .cpf("000.000.000-00")
                .decision(VoteDecisionType.SIM)
                .votingSessionId(1L)
                .build();
        final var vote = Vote.builder()
                .id(1L)
                .cpf(removeMaskFromCpf(requestDto.getCpf()))
                .votingSessionId(requestDto.getVotingSessionId())
                .decision(requestDto.getDecision())
                .createdAt(now)
                .build();
        final var expected = CreateVoteResponseDto.builder()
                .id(vote.getId())
                .build();

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        when(voteMapper.toVote(requestDto)).thenReturn(vote);
        doNothing().when(voteValidator).validateCreateVote(vote);
        when(voteRepository.save(vote)).thenReturn(vote);
        when(voteMapper.toCreateVoteResponseDto(vote)).thenReturn(expected);

        // Act
        final var actual = voteService.createVote(requestDto);

        // Assert
        verify(voteMapper).toVote(requestDto);
        verify(voteValidator).validateCreateVote(vote);
        verify(voteRepository).save(vote);
        verify(voteMapper).toCreateVoteResponseDto(vote);
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

}
