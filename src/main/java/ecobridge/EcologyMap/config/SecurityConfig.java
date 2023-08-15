package ecobridge.EcologyMap.config;

import ecobridge.EcologyMap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

//인증 처리를 하는 설정 파일
@Configuration
@EnableWebSecurity //Security의 세부 설정을 조작
@RequiredArgsConstructor //생성자 처리를 간단하게
public class SecurityConfig {

    private final UserService userService;

    //스프링 시큐리티 기능 정적 리소스에 비활성화
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    //특정 http 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()//인증 설정
                .requestMatchers("/join").permitAll()
                .anyRequest().authenticated().and().build();
    }
}