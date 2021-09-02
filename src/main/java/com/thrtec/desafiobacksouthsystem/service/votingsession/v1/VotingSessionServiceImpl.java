package com.thrtec.desafiobacksouthsystem.service.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import com.thrtec.desafiobacksouthsystem.mapper.votingsession.v1.VotingSessionMapper;
import com.thrtec.desafiobacksouthsystem.repository.VotingSessionRepository;
import com.thrtec.desafiobacksouthsystem.service.vote.v1.VoteService;
import com.thrtec.desafiobacksouthsystem.validator.votingsession.v1.VotingSessionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionMapper votingSessionMapper;
    private final VotingSessionValidator votingSessionValidator;
    private final VotingSessionRepository votingSessionRepository;
    private final VoteService voteService;

    @Override
    @Transactional
    @CacheEvict(value = "active-voting-sessions", allEntries = true)
    public CreateVotingSessionResponseDto createVotingSession(final CreateVotingSessionRequestDto requestDto) {
        var votingSession = votingSessionMapper.toVotingSession(requestDto);
        votingSessionValidator.validateCreateVotingSession(votingSession);

        votingSession = votingSessionRepository.save(votingSession);

        log.info("VOTING SESSION WITH ID {} CREATED SUCCESSFULLY", votingSession.getId());
        return votingSessionMapper.toCreateVotingSessionResponseDto(votingSession);
    }

    @Cacheable("active-voting-sessions")
    public List<VotingSession> findActiveVotingSessions() {
        log.info("SEARCHING FOR ACTIVE VOTING SESSIONS");
        return votingSessionRepository.findAllByStatus(VotingSessionStatusType.ACTIVE);
    }

    @Scheduled(fixedRate = 60000)
    protected void finishVotingSessions() {
        final var now = LocalDateTime.now();
        var sessions = findActiveVotingSessions();
        sessions = sessions.stream()
                .filter(votingSession -> votingSession.getFinishDate().isBefore(now))
                .peek(votingSession -> {
                    votingSession.setStatus(VotingSessionStatusType.FINISHED);
                    votingSession.setResult(voteService.countVotesAndDefineResult(votingSession));
                }).collect(Collectors.toList());

        if (!sessions.isEmpty()) {
            log.info("SAVING FINISHED VOTING SESSIONS");
            votingSessionRepository.saveAll(sessions);
        }
    }

}
