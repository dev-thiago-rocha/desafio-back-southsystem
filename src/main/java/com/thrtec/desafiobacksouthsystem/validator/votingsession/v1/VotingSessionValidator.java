package com.thrtec.desafiobacksouthsystem.validator.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import com.thrtec.desafiobacksouthsystem.exception.ValidationException;
import com.thrtec.desafiobacksouthsystem.repository.TopicRepository;
import com.thrtec.desafiobacksouthsystem.repository.VotingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VotingSessionValidator {

    private final VotingSessionRepository votingSessionRepository;
    private final TopicRepository topicRepository;

    public void validateCreateVotingSession(final VotingSession votingSession) {

        if (!verifyTopicExists(votingSession.getTopicId())) {
            throw new ValidationException("Topic does not exist");
        }

        if (verifyAlreadyHasTopicVotingSessionActive(votingSession.getTopicId())) {
            throw new ValidationException("Topic already has active voting session");
        }

    }

    private boolean verifyAlreadyHasTopicVotingSessionActive(Long topicId) {
        return votingSessionRepository.existsByTopicIdAndStatus(topicId, VotingSessionStatusType.ACTIVE);
    }

    private boolean verifyTopicExists(Long topicId) {
        return topicRepository.existsById(topicId);
    }

}
