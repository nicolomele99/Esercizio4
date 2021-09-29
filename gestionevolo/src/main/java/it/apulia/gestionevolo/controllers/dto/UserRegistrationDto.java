package it.apulia.gestionevolo.controllers.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import it.apulia.gestionevolo.constraint.FieldMatch;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "Le password inserite devono coincidere!"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "Le email inserite devono coincidere!") })
public class UserRegistrationDto {

    final String emptyMessage = "Campo richiesto!";

    @NotEmpty(message = emptyMessage)
    private String firstName;

    @NotEmpty(message = emptyMessage)
    private String lastName;

    @NotEmpty(message = emptyMessage)
    private String password;

    @NotEmpty(message = emptyMessage)
    private String confirmPassword;

    @Email(message = "Formato email non corretto!")
    @NotEmpty(message = emptyMessage)
    private String email;

    @Email(message = "Formato email non corretto!")
    @NotEmpty(message = emptyMessage)
    private String confirmEmail;

    public String getFirstName() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

}
