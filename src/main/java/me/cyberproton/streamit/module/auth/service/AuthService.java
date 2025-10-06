package me.cyberproton.streamit.module.auth.service;

import lombok.AllArgsConstructor;
import me.cyberproton.streamit.module.auth.dto.SignInRequestDto;
import me.cyberproton.streamit.module.auth.dto.SignUpRequestDto;
import me.cyberproton.streamit.module.jwt.service.JwtService;
import me.cyberproton.streamit.module.user.entity.UserEntity;
import me.cyberproton.streamit.module.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public String signIn(@RequestBody SignInRequestDto user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtService.generateToken(userDetails.getUsername());
    }

    public String signUp(@RequestBody SignUpRequestDto user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "Error: Username is already taken!";
        }

        // Create new user's account
        UserEntity newUser = new UserEntity();
        newUser.setEmail(user.getEmail());

        String hashedPassword = encoder.encode(user.getPassword());
        newUser.setPassword(hashedPassword);
        userRepository.save(newUser);

        return "User registered successfully!";
    }

}
