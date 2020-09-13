package com.mexus.homeleisure.streaming.api.media;

import com.mexus.homeleisure.streaming.common.BaseControllerTest;
import com.mexus.homeleisure.streaming.security.JwtTokenProvider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("트레이닝 데이터 스트리밍 테스트")
class VideoUploadTest extends BaseControllerTest {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Test
    @DisplayName("트레이닝 데이터 스트리밍 성공")
    void VideoUploadSuccess(){
    }
}