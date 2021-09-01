package com.thrtec.desafiobacksouthsystem.controller.topic.v1;

import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.service.topic.v1.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/topic")
public class TopicController implements TopicApi {

    private final TopicService topicService;

    @Override
    @PostMapping
    public ResponseEntity<CreateTopicResponseDto> createTopic(final CreateTopicRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(topicService.createTopic(requestDto));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<FindTopicResponseDto>> listTopic(final PaginationDto paginationDto) {
        return ResponseEntity.ok(topicService.listTopic(paginationDto));
    }

}
