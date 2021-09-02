package com.thrtec.desafiobacksouthsystem.service.topic.v1;

import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.mapper.topic.v1.TopicMapper;
import com.thrtec.desafiobacksouthsystem.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.thrtec.desafiobacksouthsystem.util.PaginationUtil.toPageable;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicMapper topicMapper;
    private final TopicRepository topicRepository;

    @Override
    @Transactional
    public CreateTopicResponseDto createTopic(final CreateTopicRequestDto requestDto) {
        var topic = topicMapper.toTopic(requestDto);
        topic = topicRepository.save(topic);

        log.info("TOPIC WITH ID {} CREATED SUCCESSFULLY", topic.getId());
        return topicMapper.toCreateTopicResponseDto(topic);
    }

    @Override
    public List<FindTopicResponseDto> listTopic(final PaginationDto paginationDto) {
        final var pageable = toPageable(paginationDto);

        final var topics = topicRepository.findAll(pageable);

        return topicMapper.toListFindTopicResponseDto(topics.toList());
    }

}
