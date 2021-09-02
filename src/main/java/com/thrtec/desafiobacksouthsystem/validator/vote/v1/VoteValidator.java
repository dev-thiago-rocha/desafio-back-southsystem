package com.thrtec.desafiobacksouthsystem.validator.vote.v1;

import com.thrtec.desafiobacksouthsystem.domain.Vote;
import com.thrtec.desafiobacksouthsystem.dto.userinfo.v1.ValidateCpfResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import com.thrtec.desafiobacksouthsystem.exception.ValidationException;
import com.thrtec.desafiobacksouthsystem.feign.UserInfoClient;
import com.thrtec.desafiobacksouthsystem.repository.VoteRepository;
import com.thrtec.desafiobacksouthsystem.repository.VotingSessionRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.thrtec.desafiobacksouthsystem.enumeration.CpfVoteValidationType.ABLE_TO_VOTE;

@Component
@RequiredArgsConstructor
public class VoteValidator {

    private final UserInfoClient userInfoClient;
    private final VoteRepository voteRepository;
    private final VotingSessionRepository votingSessionRepository;

    public void validateCreateVote(final Vote vote) {

        if (validateCpfAlreadyVoted(vote)) {
            throw new ValidationException("Cpf already been used in this voting session");
        }

        if (!validateVotingSessionIsValid(vote.getVotingSessionId())) {
            throw new ValidationException("Invalid voting session");
        }

        if (!validateCpfCanVote(vote.getCpf())) {
            throw new ValidationException("Cpf is not allowed to vote");
        }

    }

    private boolean validateCpfAlreadyVoted(final Vote vote) {
        return voteRepository.existsByCpfAndVotingSessionId(vote.getCpf(), vote.getVotingSessionId());
    }

    private boolean validateCpfCanVote(final String cpf) {
        ValidateCpfResponseDto validationResponse;

        try {
            validationResponse = userInfoClient.validateCpf(cpf);
        } catch (FeignException exception) {
            if (exception.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ValidationException("Cpf is invalid");
            } else {
                throw exception;
            }
        }

        return ABLE_TO_VOTE.equals(validationResponse.getStatus());
    }

    private boolean validateVotingSessionIsValid(Long votingSessionId) {
        return votingSessionRepository.existsByIdAndStatus(votingSessionId, VotingSessionStatusType.ACTIVE);
    }
}
