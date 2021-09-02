package com.thrtec.desafiobacksouthsystem.mapper.topic.v1;

import com.thrtec.desafiobacksouthsystem.domain.Topic;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TopicMapper {

    public Topic toTopic(final CreateTopicRequestDto requestDto) {
        return Topic.builder()
                .createdAt(LocalDateTime.now())
                .title(requestDto.getTitle())
                .build();
    }

    public CreateTopicResponseDto toCreateTopicResponseDto(final Topic topic) {
        return CreateTopicResponseDto.builder()
                .id(topic.getId())
                .createdAt(topic.getCreatedAt())
                .build();
    }

    public List<FindTopicResponseDto> toListFindTopicResponseDto(final List<Topic> topics) {
        return topics.stream()
                .map(this::toFindTopicResponseDto)
                .collect(Collectors.toList());
    }

    private FindTopicResponseDto toFindTopicResponseDto(final Topic topic) {
        return FindTopicResponseDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .build();
    }

}
