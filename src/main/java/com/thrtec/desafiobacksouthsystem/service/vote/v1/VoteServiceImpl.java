package com.thrtec.desafiobacksouthsystem.service.vote.v1;

import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.vote.v1.CreateVoteResponseDto;
import com.thrtec.desafiobacksouthsystem.mapper.vote.v1.VoteMapper;
import com.thrtec.desafiobacksouthsystem.repository.VoteRepository;
import com.thrtec.desafiobacksouthsystem.validator.vote.v1.VoteValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteMapper voteMapper;
    private final VoteValidator voteValidator;
    private final VoteRepository voteRepository;

    @Override
    public CreateVoteResponseDto createVote(final CreateVoteRequestDto requestDto) {
        var vote = voteMapper.toVote(requestDto);
        voteValidator.validateCreateVote(vote);

        vote = voteRepository.save(vote);

        log.info("VOTE WITH ID {} CREATED SUCCESSFULLY", vote.getId());
        return voteMapper.toCreateVoteResponseDto(vote);
    }

}
