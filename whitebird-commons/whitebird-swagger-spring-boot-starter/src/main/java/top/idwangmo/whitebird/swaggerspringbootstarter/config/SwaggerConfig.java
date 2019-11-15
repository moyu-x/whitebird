package top.idwangmo.whitebird.swaggerspringbootstarter.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
import top.idwangmo.whitebird.swaggerspringbootstarter.props.WhitebirdSwaggerProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Swagger2 配置.
 *
 * @author idwangmo
 */
@Configuration
@EnableSwagger2
@RequiredArgsConstructor
@EnableConfigurationProperties({WhitebirdSwaggerProperties.class})
public class SwaggerConfig {

    private static final String AUTH_KEY = "Authorization";

    private final @NonNull WhitebirdSwaggerProperties whitebirdSwaggerProperties;

    @Bean
    public Docket api() {

        List<ResponseMessage> responseMessages =
            Lists.newArrayList(
                new ResponseMessageBuilder().code(200).message("ok").build(),
                new ResponseMessageBuilder().code(204).message("NoContent").build(),
                new ResponseMessageBuilder().code(401).message("Unauthorized").build(),
                new ResponseMessageBuilder().code(400).message("前端参数错误").build(),
                new ResponseMessageBuilder().code(404).message("Not Found").build(),
                new ResponseMessageBuilder().code(500).message("服务端出现错误").build());

        if (whitebirdSwaggerProperties.getBasePath().isEmpty()) {
            whitebirdSwaggerProperties.getBasePath().add("/**");
        }

        List<Predicate<String>> basePath = whitebirdSwaggerProperties.getBasePath().parallelStream()
            .map(PathSelectors::ant).collect(Collectors.toList());

        List<Predicate<String>> excludePath = whitebirdSwaggerProperties.getExcludePath().parallelStream()
            .map(PathSelectors::ant).collect(Collectors.toList());

        return new Docket(DocumentationType.SWAGGER_2)
            .host(whitebirdSwaggerProperties.getHost())
            .enable(whitebirdSwaggerProperties.isEnabled())
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, responseMessages)
            .globalResponseMessage(RequestMethod.POST, responseMessages)
            .globalResponseMessage(RequestMethod.DELETE, responseMessages)
            .globalResponseMessage(RequestMethod.PUT, responseMessages)
            .select()
            .apis(RequestHandlerSelectors.basePackage(whitebirdSwaggerProperties.getBasePackage()))
            .paths(Predicates.and(Predicates.not(Predicates.or(excludePath)), Predicates.or(basePath)))
            .build()
            .securitySchemes(securitySchemes())
            .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(whitebirdSwaggerProperties.getTitle())
            .description(whitebirdSwaggerProperties.getDescription())
            .license(whitebirdSwaggerProperties.getLicense())
            .termsOfServiceUrl(whitebirdSwaggerProperties.getTermOfServiceUrl())
            .build();
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> contexts = new ArrayList<>(1);
        SecurityContext securityContext = SecurityContext.builder()
            .securityReferences(defaultAuth())
            .build();
        contexts.add(securityContext);
        return contexts;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> references = new ArrayList<>(1);
        references.add(new SecurityReference(AUTH_KEY, authorizationScopes));
        return references;
    }

    private List<ApiKey> securitySchemes() {
        return Lists.newArrayList(new ApiKey(AUTH_KEY, AUTH_KEY, "header"));
    }

}
