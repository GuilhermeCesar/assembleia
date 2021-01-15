package br.medeiros.guilherme.testesouth.helper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;

import javax.annotation.PostConstruct;
import java.util.Locale;

@RequiredArgsConstructor
public class MessageHelper {

    private final MessageSource messageSource;
    private MessageSourceAccessor accessor;

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
    }

    public String get(String code, Object... args) {
        return accessor.getMessage(code, args);
    }

    public String get(ErrorCode code, Object... args) {
        return accessor.getMessage(code.getMessageKey(), args);
    }

    @AllArgsConstructor
    @Getter
    public  enum ErrorCode{

        SESSAO_ERROR("sessao.erro"),
        ERROR_AUTHENTICATION("error.authentication"),
        SWAGGER_DESCRIPTION("swagger.description"),
        SWAGGER_VERSION("swagger.version"),
        SWAGGER_ORGANIZATION_NAME("swagger.organization.name"),
        SWAGGER_ORGANIZATION_URL("swagger.organization.url"),
        SWAGGER_EMAIL("swagger.email"),
        SWAGGER_NAME("swagger.name"),
        SWAGGER_REFINANCING("swagger.refinancing"),
        SWAGGER_PLAN("swagger.plan"),
        SESSAO_NAO_ENCONTRADA("sessao.nao.encontrada"),
        ERROR_VALID_DATA("error.valid.data");

        private final String messageKey;
    }
}