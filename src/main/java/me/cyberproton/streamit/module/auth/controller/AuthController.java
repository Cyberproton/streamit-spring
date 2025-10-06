package me.cyberproton.streamit.module.auth.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import me.cyberproton.streamit.module.auth.dto.SignInRequestDto;
import me.cyberproton.streamit.module.auth.dto.SignUpRequestDto;
import me.cyberproton.streamit.module.auth.service.AuthService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signin")
    public String signIn(@Valid @RequestBody SignInRequestDto dto) {
        return authService.signIn(dto);
    }

    @PostMapping("/signup")
    public String signUp(@Valid @RequestBody SignUpRequestDto dto) {
        return authService.signUp(dto);
    }

    @GetMapping("/test")
    public String test() {
        return "OK";
    }
}
