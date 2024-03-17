package com.challeng.foro.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Question and answer forum.", summary = "API about a question and answer forum.",
                description = "In the API you can create topics on a topic in a course, and also delete, update and search for a certain topic. In addition to adding, deleting, and updating responses to these topics.",
                contact = @Contact(name = "Saúl Ramírez.", url = "https://www.linkedin.com/in/sayul-ramirez/"),
                version = "Version 1.0")
)
public class SwaggerConfiguration {
}
