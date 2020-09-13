package com.mexus.homeleisure.streaming.api.media.controller;

import com.mexus.homeleisure.streaming.api.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 트레이닝 데이터 스트리밍 컨트롤러
 *
 * @author always0ne
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/media", produces = MediaTypes.HAL_JSON_VALUE)
public class MediaController {

    private final MediaService mediaService;

    @GetMapping("/stream/{fileType}/{fileName}")
    public ResponseEntity<byte[]> streamVideo(
            HttpServletResponse response,
            @RequestHeader(value = "Range", required = false) String httpRangeList,
            @PathVariable("fileType") String fileType,
            @PathVariable("fileName") String fileName
    ) throws IOException {
        return mediaService.prepareContent(fileName, fileType, httpRangeList);
    }
}
