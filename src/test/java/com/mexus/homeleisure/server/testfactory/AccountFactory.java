package com.mexus.homeleisure.server.testfactory;

import com.mexus.homeleisure.server.security.response.SignInResponse;
import com.mexus.homeleisure.server.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountFactory {

    @Autowired
    private AuthService authService;

    @Transactional
    public SignInResponse generateUser(int i) {
         return authService.signUp(
                 "TestUser" + i,
                "password",
                "테스트 유저 " + i
        );
    }
}
