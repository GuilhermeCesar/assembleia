package br.medeiros.guilherme.testesouth.config;

import br.medeiros.guilherme.testesouth.helper.MessageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalTime;
import java.util.function.Predicate;

import static br.medeiros.guilherme.testesouth.helper.MessageHelper.ErrorCode.*;
import static springfox.documentation.builders.RequestHandlerSelectors.any;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@RequiredArgsConstructor
@Configuration
@EnableSwagger2
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
@PropertySource(value = "classpath:/i18n/swagger.properties", encoding = "UTF-8")
public class SwaggerConfig {

    private final MessageHelper messageHelper;

    @Bean
    Docket api() {
        return new Docket(SWAGGER_2)
                .groupName("V1")
                    .directModelSubstitute(LocalTime.class, String.class)
                .select()
                .apis(any())
                .paths(paths())
                .build()
                .tags(new Tag(SwaggerTags.SESSAO, "Api de sessão"))
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title(this.messageHelper.get(MessageHelper.ErrorCode.SWAGGER_NAME))
                .description(this.messageHelper.get(SWAGGER_DESCRIPTION))
                .version(this.messageHelper.get(SWAGGER_VERSION))
                .contact(
                        new Contact(
                                this.messageHelper.get(SWAGGER_ORGANIZATION_NAME),
                                this.messageHelper.get(SWAGGER_ORGANIZATION_URL),
                                this.messageHelper.get(MessageHelper.ErrorCode.SWAGGER_EMAIL)
                        )
                )
                .build();
    }

    private Predicate<String> paths() {
        return PathSelectors.regex("/api/assembleia/v.*");
    }

    public static class SwaggerTags {

        public static final String SESSAO = "Sessao";

        private SwaggerTags() {
            super();
        }
    }
}
