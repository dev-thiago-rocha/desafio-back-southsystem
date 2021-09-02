package com.thrtec.desafiobacksouthsystem.repository;

import com.thrtec.desafiobacksouthsystem.domain.Vote;
import com.thrtec.desafiobacksouthsystem.enumeration.VoteDecisionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByCpfAndVotingSessionId(String cpf, Long votingSessionId);

    Long countAllByVotingSessionIdAndDecision(Long votingSessionId, VoteDecisionType decision);
}
