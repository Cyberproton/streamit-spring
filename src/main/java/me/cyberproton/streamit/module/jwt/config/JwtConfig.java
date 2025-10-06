package me.cyberproton.streamit.module.jwt.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Data
@Component
@ConfigurationProperties("jwt")
public class JwtConfig {
    private String secret;
    private String expiration;
}
