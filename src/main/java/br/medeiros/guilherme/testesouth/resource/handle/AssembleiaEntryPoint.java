package br.medeiros.guilherme.testesouth.resource.handle;

import br.medeiros.guilherme.testesouth.dto.ErrorMessage;
import br.medeiros.guilherme.testesouth.helper.MessageHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

import static br.medeiros.guilherme.testesouth.helper.MessageHelper.ErrorCode.ERROR_AUTHENTICATION;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j
@NoArgsConstructor
@Component
public class AssembleiaEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Autowired
    private transient MessageHelper messageHelper;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        try {

            final var errorMessage = ErrorMessage
                    .builder()
                    .message(this.messageHelper.get(ERROR_AUTHENTICATION))
                    .build();

            response.setStatus(SC_UNAUTHORIZED);
            response.setContentType(APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
            response.getWriter().write(this.objectMapper.writeValueAsString(errorMessage));
        } catch (Exception ex) {
            log.error(this.messageHelper.get(ERROR_AUTHENTICATION), ex);
        }
    }
}
