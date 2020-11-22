package com.mexus.homeleisure.streaming.api.media.controller;

import com.mexus.homeleisure.streaming.api.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * 트레이닝 데이터 스트리밍 컨트롤러
 *
 * @author always0ne
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/training", produces = MediaTypes.HAL_JSON_VALUE)
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/{trainingId}/video")
    public ResponseEntity<byte[]> streamVideo(
        @RequestHeader(value = "Range", required = false) String httpRangeList,
        @PathVariable Long trainingId) throws IOException {
        return mediaService.streamingVideo(trainingId, httpRangeList);
    }

    @GetMapping("/{trainingId}/shape")
    public ResponseEntity<byte[]> streamShape(
        @RequestHeader(value = "Range", required = false) String httpRangeList,
        @PathVariable Long trainingId) throws IOException {
        return mediaService.streamingShape(trainingId, httpRangeList);
    }
}
