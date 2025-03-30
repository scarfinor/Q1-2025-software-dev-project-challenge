package com.challenge.LaunchCode.payloads.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @Size(min = 3, max = 50, message = "Username must be between three and fifty characters.")
    @NotBlank(message = "Username must not be blank.")
    private String username;
    @Size(min = 6, max = 20, message = "Password must be between six and twenty characters.")
    @NotBlank(message = "Password must not be blank.")
    private String password;

    public @Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") @NotBlank(message = "Username must not be blank.") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") @NotBlank(message = "Username must not be blank.") String username) {
        this.username = username;
    }

    public @Size(min = 6, max = 20, message = "Password must be between six and twenty characters.") @NotBlank(message = "Password must not be blank.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20, message = "Password must be between six and twenty characters.") @NotBlank(message = "Password must not be blank.") String password) {
        this.password = password;
    }
}
