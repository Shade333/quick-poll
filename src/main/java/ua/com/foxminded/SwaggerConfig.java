package ua.com.foxminded;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

    @Bean
    public Docket v1api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("v1")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/v1.*"))
                .build();
    }

    @Bean
    public Docket v2api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("v2")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/v2.*"))
                .build();
    }
}