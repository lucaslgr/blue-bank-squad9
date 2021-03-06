package com.squad9.bluebank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.squad9.bluebank.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGet())
                .globalResponseMessage(RequestMethod.POST, responseMessageForPost())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageForDelete())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPut())
                .apiInfo(apiInfo());
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Blue Bank - Squad 9")
                .description("?? uma API do desafio final do PAN-Academy criada pelo SQUAD9")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .contact(new Contact("SQUAD9", "https://github.com/lucaslgr/blue-bank-squad9", ""))
                .build();
    }

    //Personalizando os codigos HTTTP dos m??todos de acordo com o verbo HTTP GET
    private List<ResponseMessage> responseMessageForGet() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno no servidor")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Algum campo obrigat??rio n??o foi informado ou ?? inv??lido.")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("N??o autorizado.")
                    .build()
            );
        }};
    }

    //Personalizando os codigos HTTTP dos m??todos de acordo com o verbo HTTP POST
    private List<ResponseMessage> responseMessageForPost() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno no servidor")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Algum campo obrigat??rio n??o foi informado ou ?? inv??lido.")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("N??o autorizado.")
                    .build()
            );
        }};
    }

    //Personalizando os codigos HTTTP dos m??todos de acordo com o verbo HTTP PUT
    private List<ResponseMessage> responseMessageForPut() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno no servidor")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Algum campo obrigat??rio n??o foi informado ou ?? inv??lido.")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("N??o autorizado.")
                    .build()
            );
        }};
    }

    //Personalizando os codigos HTTTP dos m??todos de acordo com o verbo HTTP DELETE
    private List<ResponseMessage> responseMessageForDelete() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno no servidor")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Algum campo obrigat??rio n??o foi informado ou ?? inv??lido.")
                    .build()
            );
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("N??o autorizado.")
                    .build()
            );
        }};
    }
}
