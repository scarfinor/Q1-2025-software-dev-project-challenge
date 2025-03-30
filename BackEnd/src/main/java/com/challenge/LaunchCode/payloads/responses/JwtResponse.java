package com.challenge.LaunchCode.payloads.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    @JsonIgnore
    private long id;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<String> roles;
    private String bio;

    public JwtResponse() {}

    public JwtResponse(String accessToken, Long id, String username, String firstName, String lastName, String email, String password, List<String> roles, String bio) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.bio = bio;
    }
    public JwtResponse(String accessToken, int id, String username, String firstName, String lastName, String password, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }

    public JwtResponse(String accessToken, int id, String username, String firstName, String lastName, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public @Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") @NotBlank(message = "Username must not be blank.") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") @NotBlank(message = "Username must not be blank.") String username) {
        this.username = username;
    }

    public @Size(min = 3, max = 20, message = "First name must be between three and twenty characters.") @NotBlank(message = "First name must not be blank.") String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 3, max = 20, message = "First name must be between three and twenty characters.") @NotBlank(message = "First name must not be blank.") String fristName) {
        this.firstName = fristName;
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
