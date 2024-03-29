package com.backreview.backend.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.backreview.backend.controller"))
      .paths(PathSelectors.ant("/api/**"))  // 그중 /api/** 인 URL들만 필터링
      .paths(Predicates.not(PathSelectors.regex("/error")))
      .build()
      .useDefaultResponseMessages(false)
      .globalResponseMessage(RequestMethod.GET,
                            newArrayList(
                              new ResponseMessageBuilder()
                                  .code(500)
                                  .message("Internal Server Error")
                                  //.responseModel(new ModelRef("Error"))
                                  .build(),
                              new ResponseMessageBuilder()
                                    .code(400)
                                    .message("Bad Request")
                                    .build(),
                              new ResponseMessageBuilder()
                                .code(404)
                                .message("Not Found")
                                .build()));
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("test Swagger2")
            .description("Swagger2 Test Case")
            .build();
  }
}
