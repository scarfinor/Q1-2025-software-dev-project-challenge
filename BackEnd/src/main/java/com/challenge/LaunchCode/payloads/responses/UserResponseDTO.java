package com.challenge.LaunchCode.payloads.responses;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserResponseDTO {
    @Size(min = 3, max = 50, message = "Username must be between three and fifty characters.")
    private String username;
    @Size(min = 3, max = 20, message = "First name must be between three and twenty characters.")
    private String firstName;
    @Size(min = 3, max = 20, message = "Last name must be between three and twenty characters.")
    private String lastName;
    @Email(message = "Email address must be well formated.")
    private String email;
    @Size(min = 6, max = 20, message = "Password must be between six and twenty characters.")
    private String password;
    private String bio;

    public @Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") String getUsername(@Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") String username) {
        return this.username;
    }

    public void setUsername(@Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") String username) {
        this.username = username;
    }

    public @Size(min = 3, max = 20, message = "First name must be between three and twenty characters.") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 3, max = 20, message = "First name must be between three and twenty characters.") String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 3, max = 20, message = "Last name must be between three and twenty characters.") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 3, max = 20, message = "Last name must be between three and twenty characters.") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "Email address must be well formated.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email address must be well formated.") String email) {
        this.email = email;
    }

    public @Size(min = 6, max = 20, message = "Password must be between six and twenty characters.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20, message = "Password must be between six and twenty characters.") String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
