package com.challenge.LaunchCode.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
    private Set<Role> roles = new HashSet<>();
    private String bio;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions = new HashSet<>();
    private double netWorth;

    public User() {}

    public User(String username, String firstName, String lastName, String email, String password, Set<Role> roles, String bio, Set<Transaction> transactions) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.bio = bio;
        this.transactions = transactions;
    }

    public User(@Size(min = 3, max = 50, message = "Username must be between three and fifty characters.") @NotBlank(message = "Username must not be blank.") String username, @Size(min = 3, max = 20, message = "First name must be between three and twenty characters.") @NotBlank(message = "First name must not be blank.") String firstName, @Size(min = 3, max = 20, message = "Last name must be between three and twenty characters.") @NotBlank(message = "Last name must not be blank.") String lastName, @Email(message = "Email address must be well formated.") @NotBlank(message = "Email must not be blank.") String email, String encode) {
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public void updateNetWorth() {
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME) {
                totalIncome += transaction.getAmount();
            } else if (transaction.getType() == TransactionType.EXPENSE) {
                totalExpense += transaction.getAmount();
            }
        }

        this.netWorth = totalIncome - totalExpense;
    }
}
