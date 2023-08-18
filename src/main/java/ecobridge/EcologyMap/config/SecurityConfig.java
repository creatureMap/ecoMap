package ecobridge.EcologyMap.config;

import ecobridge.EcologyMap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                //.requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

    //특정 http 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .requestMatchers("/login", "/user").permitAll()
                .anyRequest().authenticated().and() //특정 경로 엑세스
                .formLogin(formLogin -> formLogin.
                        loginPage("/login").defaultSuccessUrl("/")) //로그인 관련 설정
                .logout(logout -> {
                    logout.logoutSuccessUrl("/login") //로그아웃 설정
                            .invalidateHttpSession(true);
                }).csrf(AbstractHttpConfigurer::disable).build();
    }

    //인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailService)
        throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)//사용자 정보를 가져올 서비스 클래스 설정
                .passwordEncoder(bCryptPasswordEncoder).and().build();
    }

    //패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}