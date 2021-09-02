package com.thrtec.desafiobacksouthsystem.mapper.topic.v1;

import com.thrtec.desafiobacksouthsystem.domain.Topic;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class TopicMapperTest {

    @InjectMocks
    private TopicMapper topicMapper;

    @Test
    public void toTopic_withSuccess() {
        // Arrange
        final var now = LocalDateTime.now();
        final var requestDto = CreateTopicRequestDto.builder()
                .title("Test title")
                .build();
        final var expected = Topic.builder()
                .createdAt(now)
                .title(requestDto.getTitle())
                .build();

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        // Act
        final var actual = topicMapper.toTopic(requestDto);

        // Assert
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

    @Test
    public void toCreateTopicResponseDto_withSuccess() {
        // Arrange
        final var now = LocalDateTime.now();
        final var topic = Topic.builder()
                .id(1L)
                .title("Test title")
                .createdAt(now)
                .build();
        final var expected = CreateTopicResponseDto.builder()
                .createdAt(now)
                .id(topic.getId())
                .build();

        final var localDateTimeMockedStatic = Mockito.mockStatic(LocalDateTime.class);
        localDateTimeMockedStatic.when(LocalDateTime::now).thenReturn(now);

        // Act
        final var actual = topicMapper.toCreateTopicResponseDto(topic);

        // Assert
        assertEquals(expected, actual);
        localDateTimeMockedStatic.close();
    }

    @Test
    public void toListFindTopicResponseDto_withSuccess() {
        // Arrange
        final var topic = Topic.builder()
                .id(1L)
                .title("Test title")
                .build();
        final var expected = FindTopicResponseDto.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .build();

        // Act
        final var actual = topicMapper.toListFindTopicResponseDto(singletonList(topic));

        // Assert
        assertEquals(singletonList(expected), actual);
    }

}
