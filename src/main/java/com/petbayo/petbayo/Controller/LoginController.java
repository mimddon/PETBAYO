package com.petbayo.petbayo.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        if (isValidLogin(loginRequest.getEmail(), loginRequest.getPassword())) {
            session.setAttribute("loggedInUser", loginRequest.getEmail());
            response.put("success", true);
            response.put("message", "Login successful.");
        } else {
            response.put("success", false);
            response.put("message", "Invalid email or password.");
        }
        return response;
    }

    private boolean isValidLogin(String email, String password) {
        // TODO: Validate email and password against database or other data source
        return true;
    }

    // 로그인 요청 JSON 페이로드를 처리하기 위한 내부 클래스
    private static class LoginRequest {
        private String email;
        private String password;
        private boolean rememberMe;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public boolean isRememberMe() {
            return rememberMe;
        }

        public void setRememberMe(boolean rememberMe) {
            this.rememberMe = rememberMe;
        }
    }
}