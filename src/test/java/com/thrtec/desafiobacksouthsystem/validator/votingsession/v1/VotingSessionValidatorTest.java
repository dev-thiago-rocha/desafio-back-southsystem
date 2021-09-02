package com.thrtec.desafiobacksouthsystem.validator.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import com.thrtec.desafiobacksouthsystem.exception.ValidationException;
import com.thrtec.desafiobacksouthsystem.repository.TopicRepository;
import com.thrtec.desafiobacksouthsystem.repository.VotingSessionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VotingSessionValidatorTest {

    @InjectMocks
    private VotingSessionValidator votingSessionValidator;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private TopicRepository topicRepository;

    @Test
    public void validateCreateVotingSession_withSuccess() {
        // Arrange
        final var votingSession = VotingSession.builder()
                .topicId(1L)
                .build();

        when(topicRepository.existsById(votingSession.getTopicId())).thenReturn(true);
        when(votingSessionRepository.existsByTopicIdAndStatus(votingSession.getTopicId(), VotingSessionStatusType.ACTIVE)).thenReturn(false);

        // Act
        votingSessionValidator.validateCreateVotingSession(votingSession);

        // Assert
        verify(topicRepository).existsById(votingSession.getTopicId());
        verify(votingSessionRepository).existsByTopicIdAndStatus(votingSession.getTopicId(), VotingSessionStatusType.ACTIVE);
    }

    @Test
    public void validateCreateVotingSession_topicDoesNotExist_withValidationException() {
        // Arrange
        final var votingSession = VotingSession.builder()
                .topicId(1L)
                .build();

        when(topicRepository.existsById(votingSession.getTopicId())).thenReturn(false);

        // Act
        final var actual = assertThrows(ValidationException.class, () -> votingSessionValidator.validateCreateVotingSession(votingSession));

        // Assert
        verify(topicRepository).existsById(votingSession.getTopicId());
        verify(votingSessionRepository, never()).existsByTopicIdAndStatus(votingSession.getTopicId(), VotingSessionStatusType.ACTIVE);
        assertEquals("Topic does not exist", actual.getMessage());
    }

    @Test
    public void validateCreateVotingSession_topicAlreadyHasVotingSession_withValidationException() {
        // Arrange
        final var votingSession = VotingSession.builder()
                .topicId(1L)
                .build();

        when(topicRepository.existsById(votingSession.getTopicId())).thenReturn(true);
        when(votingSessionRepository.existsByTopicIdAndStatus(votingSession.getTopicId(), VotingSessionStatusType.ACTIVE)).thenReturn(true);

        // Act
        final var actual = assertThrows(ValidationException.class, () -> votingSessionValidator.validateCreateVotingSession(votingSession));

        // Assert
        verify(topicRepository).existsById(votingSession.getTopicId());
        verify(votingSessionRepository).existsByTopicIdAndStatus(votingSession.getTopicId(), VotingSessionStatusType.ACTIVE);
        assertEquals("Topic already has active voting session", actual.getMessage());
    }

}
