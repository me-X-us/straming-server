package com.mexus.homeleisure.server.api.media.controller;

import com.mexus.homeleisure.server.api.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 영상 업로드/다운로드 컨트롤러
 *
 * @author always0ne
 * @version 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/media", produces = MediaTypes.HAL_JSON_VALUE)
public class MediaController {

    private final MediaService mediaService;
}
