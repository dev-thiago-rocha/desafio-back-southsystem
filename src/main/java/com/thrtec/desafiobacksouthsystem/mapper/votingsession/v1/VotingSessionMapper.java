package com.thrtec.desafiobacksouthsystem.mapper.votingsession.v1;

import com.thrtec.desafiobacksouthsystem.domain.VotingSession;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.votingsession.v1.CreateVotingSessionResponseDto;
import com.thrtec.desafiobacksouthsystem.enumeration.VotingSessionStatusType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class VotingSessionMapper {

    public VotingSession toVotingSession(final CreateVotingSessionRequestDto requestDto) {
        final var now = LocalDateTime.now();
        final var finishDate = now.plusMinutes(requestDto.getActivePeriod());

        return VotingSession.builder()
                .createdAt(now)
                .finishDate(finishDate)
                .status(VotingSessionStatusType.ACTIVE)
                .topicId(requestDto.getTopicId())
                .build();
    }

    public CreateVotingSessionResponseDto toCreateVotingSessionResponseDto(final VotingSession votingSession) {
        return CreateVotingSessionResponseDto.builder()
                .id(votingSession.getId())
                .finishDate(votingSession.getFinishDate())
                .build();
    }

}
