package com.thrtec.desafiobacksouthsystem.controller.topic.v1;

import com.thrtec.desafiobacksouthsystem.dto.pagination.v1.PaginationDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicRequestDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.CreateTopicResponseDto;
import com.thrtec.desafiobacksouthsystem.dto.topic.v1.FindTopicResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Topic", tags = "Topic")
public interface TopicApi {

    @ApiOperation(value = "Create a new topic", tags = "Topic")
    ResponseEntity<CreateTopicResponseDto> createTopic(@Valid @RequestBody CreateTopicRequestDto requestDto);

    @ApiOperation(value = "List all topics with pagination", tags = "Topic")
    ResponseEntity<List<FindTopicResponseDto>> listTopic(@Valid PaginationDto paginationDto);

}
