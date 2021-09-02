package com.thrtec.desafiobacksouthsystem.validator.vote.v1;

import com.thrtec.desafiobacksouthsystem.domain.Vote;
import com.thrtec.desafiobacksouthsystem.dto.userinfo.v1.ValidateCpfResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.CpfVoteValidationType;
import com.thrtec.desafiobacksouthsystem.exception.ValidationException;
import com.thrtec.desafiobacksouthsystem.feign.UserInfoClient;
import com.thrtec.desafiobacksouthsystem.repository.VoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteValidatorTest {

    @InjectMocks
    private VoteValidator voteValidator;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private UserInfoClient userInfoClient;

    @Test
    public void validateCreateVote_withSuccess() {
        // Arrange
        final var vote = Vote.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .build();
        final var validationResponse = ValidateCpfResponseDto.builder()
                .status(CpfVoteValidationType.ABLE_TO_VOTE)
                .build();

        when(voteRepository.existsByCpfAndVotingSessionId(vote.getCpf(), vote.getVotingSessionId())).thenReturn(false);
        when(userInfoClient.validateCpf(vote.getCpf())).thenReturn(validationResponse);

        // Act
        voteValidator.validateCreateVote(vote);

        // Assert
        verify(voteRepository).existsByCpfAndVotingSessionId(vote.getCpf(), vote.getVotingSessionId());
        verify(userInfoClient).validateCpf(vote.getCpf());
    }

    @Test
    public void validateCreateVote_cpfAlreadyUsed_withValidationException() {
        // Arrange
        final var vote = Vote.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .build();

        when(voteRepository.existsByCpfAndVotingSessionId(vote.getCpf(), vote.getVotingSessionId())).thenReturn(true);

        // Act
        assertThrows(ValidationException.class, () -> voteValidator.validateCreateVote(vote));

        // Assert
        verify(voteRepository).existsByCpfAndVotingSessionId(vote.getCpf(), vote.getVotingSessionId());
        verify(userInfoClient, never()).validateCpf(vote.getCpf());
    }

    @Test
    public void validateCreateVote_cpfUnable_withValidationException() {
        // Arrange
        final var vote = Vote.builder()
                .id(1L)
                .cpf("000.000.000-00")
                .build();
        final var validationResponse = ValidateCpfResponseDto.builder()
                .status(CpfVoteValidationType.UNABLE_TO_VOTE)
                .build();

        when(voteRepository.existsByCpfAndVotingSessionId(vote.getCpf(), vote.getVotingSessionId())).thenReturn(false);
        when(userInfoClient.validateCpf(vote.getCpf())).thenReturn(validationResponse);

        // Act
        assertThrows(ValidationException.class, () -> voteValidator.validateCreateVote(vote));

        // Assert
        verify(voteRepository).existsByCpfAndVotingSessionId(vote.getCpf(), vote.getVotingSessionId());
        verify(userInfoClient).validateCpf(vote.getCpf());
    }

}
