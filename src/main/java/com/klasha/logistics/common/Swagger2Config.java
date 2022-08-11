package com.klasha.logistics.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    public static final String TAG_1 = "User";
    public static final String TAG_2 = "Location";
    public static final String TAG_3 = "Routes";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.klasha"))
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointsInfo())
                .tags(new Tag(TAG_1, "Administer all actions performed on a user"))
                .tags(new Tag(TAG_2, "Administer location services"))
                .tags(new Tag(TAG_3, "Methods available to terminals"));
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Klasha Logistics")
                .description("A backend service to enable logistics services")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}

