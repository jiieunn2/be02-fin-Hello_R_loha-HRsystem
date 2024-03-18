package com.HelloRolha.HR.config;


import com.HelloRolha.HR.config.utils.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig{

    @Value("${jwt.secret-key}")
    private String secretKey;


//    private final CustomAccessDeniedHandler customAccessDeniedHandler;
//    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            http.csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()  // CORS 해결하기 위한 OPTION 메서드 허용
                    .anyRequest().permitAll()
                    .and()
                    .exceptionHandling()
//                    .accessDeniedHandler(customAccessDeniedHandler) // 인가에 대한 예외 처리
//                    .authenticationEntryPoint(customAuthenticationEntryPoint) // 인증에 대한 예외 처리
                    .and()
                    .formLogin().disable()
                    .addFilterBefore(new JwtFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

                    // OAuth 2.0 로그인 처리

            return  http.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
