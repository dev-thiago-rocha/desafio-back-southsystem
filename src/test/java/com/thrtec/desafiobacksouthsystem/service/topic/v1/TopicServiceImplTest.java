package com.thrtec.desafiobacksouthsystem.service.topic.v1;

import com.thrtec.desafiobacksouthsystem.domain.Topic;
import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.mapper.topic.v1.TopicMapper;
import com.thrtec.desafiobacksouthsystem.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Stream;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

    @InjectMocks
    private TopicServiceImpl topicService;

    @Mock
    private TopicRepository topicRepository;

    @Mock
    private TopicMapper topicMapper;

    @Test
    public void createTopic_withSuccess() {
        // Arrange
        final var now = LocalDateTime.now();
        final var requestDto = CreateTopicRequestDto.builder()
                .title("Test title")
                .build();
        final var topic = Topic.builder()
                .id(1L)
                .title(requestDto.getTitle())
                .createdAt(now)
                .build();
        final var expected = CreateTopicResponseDto.builder()
                .id(topic.getId())
                .createdAt(now)
                .build();

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        when(topicMapper.toTopic(requestDto)).thenReturn(topic);
        when(topicRepository.save(topic)).thenReturn(topic);
        when(topicMapper.toCreateTopicResponseDto(topic)).thenReturn(expected);

        // Act
        final var actual = topicService.createTopic(requestDto);

        // Assert
        verify(topicMapper).toTopic(requestDto);
        verify(topicRepository).save(topic);
        verify(topicMapper).toCreateTopicResponseDto(topic);
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

    @Test
    public void listTopic_withSuccess() {
        // Arrange
        final var requestDto = PaginationDto.builder()
                .page(0)
                .limit(10)
                .build();
        final var pageRequest = PageRequest.of(requestDto.getPage(), requestDto.getLimit());
        final var topic = Topic.builder()
                .id(1L)
                .title("Test title")
                .build();
        final var responseDto = FindTopicResponseDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .build();
        final var topicList = singletonList(topic);
        final var topicPage = new PageImpl<>(topicList);
        final var expected = singletonList(responseDto);

        when(topicRepository.findAll(pageRequest)).thenReturn(topicPage);
        when(topicMapper.toListFindTopicResponseDto(topicList)).thenReturn(expected);

        // Act
        final var actual = topicService.listTopic(requestDto);

        // Assert
        verify(topicRepository).findAll(pageRequest);
        verify(topicMapper).toListFindTopicResponseDto(topicList);
        assertEquals(expected,actual);
    }

}
