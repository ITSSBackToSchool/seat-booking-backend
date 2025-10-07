package org.itss.backtoschool.deskops.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth0")
public class Auth0ManagementProperties {

    private Management management = new Management();
    private Roles roles = new Roles();

    @Data
    public static class Management {
        private String domain;
        private String clientId;
        private String clientSecret;
    }

    @Data
    public static class Roles {
        private String employeeId;
        private String hrId;
        private String adminId;
    }
}
