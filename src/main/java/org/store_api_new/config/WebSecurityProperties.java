package org.store_api_new.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security")
@Setter
@Getter
public class WebSecurityProperties {
    private String adminEmail;
}
