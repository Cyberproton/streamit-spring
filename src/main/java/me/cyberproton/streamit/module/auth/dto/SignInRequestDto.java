package me.cyberproton.streamit.module.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInRequestDto {
    @Email
    private String email;

    @Pattern(regexp = "^[A-Za-z\\d@$!%*?&_-]{8,64}$")
    private String password;
}
