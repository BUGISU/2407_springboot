package com.example.ex8.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /* SecurityFilterChain Bean 역할 :
  세션 인증 기반 방식으로 대부분의 Spring Security에 대한 설정으로 다룰 수 있다. */
  @Bean
  protected SecurityFilterChain config(HttpSecurity httpSecurity)
      throws Exception {
    // api 서버에서 csrf 사용 안함.
    httpSecurity.csrf(httpSecurityCsrfConfigurer -> {
      httpSecurityCsrfConfigurer.disable();
    });
    httpSecurity.authorizeHttpRequests(
        auth -> auth
            .requestMatchers(new AntPathRequestMatcher("/notes/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/error/**")).permitAll()
            .anyRequest().denyAll());

//    httpSecurity.oauth2Login(new Customizer<OAuth2LoginConfigurer<HttpSecurity>>() {
//      @Override
//      public void customize(OAuth2LoginConfigurer<HttpSecurity> httpSecurityOAuth2LoginConfigurer) {
//        httpSecurityOAuth2LoginConfigurer.successHandler(
//            getAuthenticationSuccessHandler()
//        );
//      }
//    });

    return httpSecurity.build();
  }

  // InMemory 방식으로 UserDetailsService(인증 관리 객체) 사용
  /*@Bean
  public UserDetailsService userDetailsService() {
    UserDetails user1 = User.builder()
        .username("user1")
        .password("$2a$10$XGw3jOo9mQSoij4/so.6H.BtSRWpgPze6ZWMuc7ntyFFWqVNbcmBe")
        .roles("USER")
        .build();
    UserDetails manager = User.builder()
        .username("manager")
        .password("$2a$10$AEHcuzENZx7OLeA.s8e.t.CvhE/a/GZf.ZKTPEBIKLv8g03zChnD2")
        .roles("MANAGER")
        .build();
    UserDetails admin = User.builder()
        .username("admin")
        .password(passwordEncoder().encode("1"))
        .roles("ADMIN", "MANAGER")
        .build();
    List<UserDetails> list = new ArrayList<>();
    list.add(user1);
    list.add(manager);
    list.add(admin);
    return new InMemoryUserDetailsManager(list);

  }*/

}