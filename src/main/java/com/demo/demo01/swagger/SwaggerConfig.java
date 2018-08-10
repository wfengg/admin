package com.demo.demo01.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final String SWAGGER2_API_BASEPACKAGE = "com.demo.demo01.controller";
    private final String SWAGGER2_API_TITLE = "user-API";
    private final String SWAGGER2_API_DESCRIPTION = "com.demo.demo01.controller";
    private final String SWAGGER2_API_VERSION = "1.0";


    @Bean
    public Docket restfulApi(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()                                                           //选择哪些路径和API会生成document
            .apis(RequestHandlerSelectors.basePackage(SWAGGER2_API_BASEPACKAGE))//对哪些api进行监控
            .paths(PathSelectors.any())                                         //对所有路径进行监控
            .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(SWAGGER2_API_TITLE)
                .description(SWAGGER2_API_DESCRIPTION)
                .version(SWAGGER2_API_VERSION)
//                .contact()
                .build();

    }



}
