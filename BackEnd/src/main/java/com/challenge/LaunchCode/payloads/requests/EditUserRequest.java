package com.challenge.LaunchCode.payloads.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EditUserRequest {
    @Size(min = 3, max = 50, message = "Username must be between three and fifty characters.")
    @NotBlank(message = "Username must not be blank.")
    private String username;
    @Size(min = 3, max = 20, message = "First name must be between three and twenty characters.")
    @NotBlank(message = "First name must not be blank.")
    private String firstName;
    @Size(min = 3, max = 20, message = "Last name must be between three and twenty characters.")
    @NotBlank(message = "Last name must not be blank.")
    private String lastName;
    @Email(message = "Email address must be well formated.")
    @NotBlank(message = "Email must not be blank.")
    private String email;
    @Size(min = 6, max = 20, message = "Password must be between six and twenty characters.")
    @NotBlank(message = "Password must not be blank.")
    private String password;
    private String bio;

    public @Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") @NotBlank(message = "Username must not be blank.") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") @NotBlank(message = "Username must not be blank.") String username) {
        this.username = username;
    }

    public @Size(min = 3, max = 20, message = "First name must be between three and twenty characters.") @NotBlank(message = "First name must not be blank.") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 3, max = 20, message = "First name must be between three and twenty characters.") @NotBlank(message = "First name must not be blank.") String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 3, max = 20, message = "Last name must be between three and twenty characters.") @NotBlank(message = "Last name must not be blank.") String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 3, max = 20, message = "Last name must be between three and twenty characters.") @NotBlank(message = "Last name must not be blank.") String lastName) {
        this.lastName = lastName;
    }

    public @Email(message = "Email address must be well formated.") @NotBlank(message = "Email must not be blank.") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Email address must be well formated.") @NotBlank(message = "Email must not be blank.") String email) {
        this.email = email;
    }

    public @Size(min = 6, max = 20, message = "Password must be between six and twenty characters.") @NotBlank(message = "Password must not be blank.") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, max = 20, message = "Password must be between six and twenty characters.") @NotBlank(message = "Password must not be blank.") String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
