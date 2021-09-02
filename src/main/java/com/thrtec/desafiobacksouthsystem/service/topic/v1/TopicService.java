package com.thrtec.desafiobacksouthsystem.service.topic.v1;

import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;

import java.util.List;

public interface TopicService {

    CreateTopicResponseDto createTopic(CreateTopicRequestDto requestDto);

    List<FindTopicResponseDto> listTopic(PaginationDto paginationDto);

}
