package br.medeiros.guilherme.testesouth.config;

import br.medeiros.guilherme.testesouth.resource.handle.AssembleiaEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig
        extends WebSecurityConfigurerAdapter
{

    private final AssembleiaEntryPoint assembleiaEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(this.assembleiaEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/api/assembleia/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest()
                .permitAll();
    }
}
