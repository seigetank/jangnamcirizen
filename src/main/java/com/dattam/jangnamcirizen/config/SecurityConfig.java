package com.dattam.jangnamcirizen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // 인메모리 유저 생성 (테스트용)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
                .password("{noop}1234") // {noop} → 비밀번호 암호화 안 함
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // URL별 접근 권한 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index.html", "/css/**", "/js/**", "/images/**").permitAll() // 정적 자원 허용
                        .requestMatchers("/api/**").authenticated() // API 인증 필요
                        .anyRequest().permitAll() // 나머지는 허용
                )
                .formLogin(Customizer.withDefaults()) // 기본 로그인폼 활성화
                .logout(Customizer.withDefaults());  // 기본 로그아웃 활성화

        return http.build();
    }
}
