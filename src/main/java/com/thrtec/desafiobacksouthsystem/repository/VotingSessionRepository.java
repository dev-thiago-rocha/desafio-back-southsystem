package com.thrtec.desafiobacksouthsystem.repository;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotingSessionRepository extends JpaRepository<VotingSession, Long> {

    boolean existsByTopicIdAndStatus(Long topicId, VotingSessionStatusType status);

    List<VotingSession> findAllByStatus(VotingSessionStatusType status);

}
