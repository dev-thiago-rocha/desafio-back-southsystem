package com.thrtec.desafiobacksouthsystem.service.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import com.thrtec.desafiobacksouthsystem.mapper.votingsession.v1.VotingSessionMapper;
import com.thrtec.desafiobacksouthsystem.repository.VotingSessionRepository;
import com.thrtec.desafiobacksouthsystem.validator.votingsession.v1.VotingSessionValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VotingSessionServiceImpl implements VotingSessionService {

    private final VotingSessionMapper votingSessionMapper;
    private final VotingSessionValidator votingSessionValidator;
    private final VotingSessionRepository votingSessionRepository;

    @Override
    @Transactional
    public CreateVotingSessionResponseDto createVotingSession(final CreateVotingSessionRequestDto requestDto) {
        var votingSession = votingSessionMapper.toVotingSession(requestDto);
        votingSessionValidator.validateCreateVotingSession(votingSession);

        votingSession = votingSessionRepository.save(votingSession);

        log.info("VOTING SESSION WITH ID {} CREATED SUCCESSFULLY", votingSession.getId());
        return votingSessionMapper.toCreateVotingSessionResponseDto(votingSession);
    }

}
