package org.opensearch.data.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {


    @Value("${app.version}")
    private String version;

    @Bean
    public OpenAPI metadata() {
        return new OpenAPI()
                .servers(Collections.emptyList())
                .info(this.apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Opensearch полнотекстовый поиск")
                .version(version)
                .description("Spring Data OpenSearch example with Testcontainers")
                .contact(getContactDetails());
    }

    private Contact getContactDetails() {
        return new Contact().name("Ляшенко Евгений")
                .email("viges@mail.ru")
                .url("https://github.com/viges2201");
    }
}