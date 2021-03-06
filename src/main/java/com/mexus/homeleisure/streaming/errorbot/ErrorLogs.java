package com.mexus.homeleisure.streaming.errorbot;

import ch.qos.logback.classic.spi.ILoggingEvent;
import com.mexus.homeleisure.streaming.errorbot.embedded.ErrorInfo;
import com.mexus.homeleisure.streaming.errorbot.embedded.RequestInfo;
import com.mexus.homeleisure.streaming.errorbot.embedded.SystemInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 에러로그 엔터티
 *
 * @author always0ne
 * @version 1.0
 */
@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class ErrorLogs {
    /**
     * pk
     */
    @Id
    @Column(name = "ID", precision = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 접속자 ID(비로그인 사용자는 anonymousUser)
     */
    @Column(name = "USER_INFO", columnDefinition = "TEXT")
    private String userInfo;

    /**
     * 서버 시스템 정보
     */
    @Embedded
    private SystemInfo systemInfo;

    /**
     * 에러 정보
     */
    @Embedded
    private ErrorInfo errorInfo;

    /**
     * 사용자 요청 정보
     */
    @Embedded
    private RequestInfo requestInfo;

    /**
     * 알림이 전달되었는지 여부
     */
    @Column(name = "ALERT_YN", length = 1)
    private Boolean alert = false;

    /**
     * 에러 발생 시각
     */
    @Column(name = "ERROR_DATETIME")
    private final LocalDateTime errorDatetime = LocalDateTime.now();

    public ErrorLogs(ILoggingEvent eventObject, String serverName, String uri, String parameter, String header, String body, String agent) {
        this.userInfo = SecurityContextHolder.getContext().getAuthentication().getName();
        this.systemInfo = new SystemInfo(serverName);
        this.errorInfo = new ErrorInfo(eventObject);
        this.requestInfo = new RequestInfo(uri, parameter, header, body, agent);
    }

    public void markAsAlert() {
        this.alert = true;
    }
}
