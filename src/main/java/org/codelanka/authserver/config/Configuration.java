package org.codelanka.authserver.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties()
@Data
public class Configuration {
    private String username;
    private String password;
}