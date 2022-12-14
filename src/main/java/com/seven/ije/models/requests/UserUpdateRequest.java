package com.seven.ije.models.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seven.ije.models.enums.UserRole;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserUpdateRequest implements AppRequest {
    @Pattern(regexp = "^[A-Za-z'\\-]{2,30}",message = "Name must be at least 2 characters long")
    String firstName;
    @Pattern(regexp = "^[A-Za-z'\\-]{2,30}",message = "Name must be at least 2 characters long")
    String lastName ;
    @Pattern(regexp = "^[+-][0-9]{11,15}$",message = "Name must be at least 2 characters long")
    String phoneNo ;
    @Email(regexp = "[A-Za-z0-9\\-]{2,}@[A-Za-z'\\-]{2,}\\.[A-Za-z'\\-]{2,}", message = "Invalid email format")
    String email;
    @Pattern(regexp = "^[A-Za-z\\d'.!@#$%^&*_\\-]{8,}",message = "Password must be at least 8 characters long")
    String password;
    @Past(message = "Future and current dates not allowed")
    LocalDate dateBirth;
    UserRole role;
    Boolean isAccountNonExpired;
    Boolean isAccountNonLocked;
    Boolean isCredentialsNonExpired;
    Boolean isEnabled;
}
