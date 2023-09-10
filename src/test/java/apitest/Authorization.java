package apitest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authorization {
    public String username;
    public String password;

    public Authorization(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Authorization() {
    }

    public static class AuthResponse {
        @JsonProperty("access_token")
        public String accessToken;
    }
}
