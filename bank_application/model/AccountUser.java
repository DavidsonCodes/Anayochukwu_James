package org.example.bank_application.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "account_user", uniqueConstraints = @UniqueConstraint(columnNames = {"username, phoneNumber"}))
public class AccountUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    @NotBlank(message = "First name must not be blank")
    @NotNull()
    @Length(min = 4, max = 20)
    private String firstName;
    @Column(name = "last_name")
    @NotNull @NotBlank
    @Length(min = 4, max = 20)
    private String lastName;
    @Value("${myMiddleName}")
    private String middleName;
    @Column(name = "username", unique = true)
    @Email
    @Length(min = 5, max = 50)
    private String username;
    @NotBlank @NotNull
    @Length(min = 5, max = 50, message = "Your password must be more than 5 characters and less than 50")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,20}$",
            message = "Password must be 8-20 characters long and include at least one lowercase letter, one uppercase letter, one digit, and one special character.")
    private String password;

    @NotBlank @NotNull
     @Pattern(regexp = "[0-9]{11}", message = "Phone number must be 11 digits")
     private String phoneNumber;


    public AccountUser(String firstName, String lastName, String middleName, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
    public AccountUser(){}

    public Long getId() {
        return id;
    }public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "AccountUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

