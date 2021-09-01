package com.thrtec.desafiobacksouthsystem.controller.topic.v1;

import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.service.topic.v1.TopicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopicControllerTest {

    @InjectMocks
    private TopicController topicController;

    @Mock
    private TopicService topicService;

    @Test
    public void createTopic_withSuccess() {
        // Arrange
        final var requestDto = CreateTopicRequestDto.builder()
                .title("Test title")
                .build();
        final var responseDto = CreateTopicResponseDto.builder()
                .id(1L)
                .createdAt(LocalDateTime.now())
                .build();
        final var expected = ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        when(topicService.createTopic(requestDto)).thenReturn(responseDto);

        // Act
        final var actual = topicController.createTopic(requestDto);

        // Assert
        verify(topicService).createTopic(requestDto);
        assertEquals(expected, actual);
    }

    @Test
    public void listTopic_withSuccess() {
        // Arrange
        final var requestDto = PaginationDto.builder()
                .limit(10)
                .page(0)
                .build();
        final var responseDto = singletonList(
                FindTopicResponseDto.builder()
                        .id(1L)
                        .title("Test title")
                        .build()
        );
        final var expected = ResponseEntity.ok(responseDto);

        when(topicService.listTopic(requestDto)).thenReturn(responseDto);

        // Act
        final var actual = topicController.listTopic(requestDto);

        // Assert
        verify(topicService).listTopic(requestDto);
        assertEquals(expected, actual);
    }

}
