package com.greedy.semi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.greedy.semi.member.service.AuthenticationService;

@EnableWebSecurity
public class SecurityConfig {
	
	private final AuthenticationService authenticationService;

    @Autowired
    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/images/**");
    }

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
        return http
        		.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/member/update", "/member/delete", "/member/payment","/trade/regist", "/trade/update", "/trade/delete","/free/make").hasAnyAuthority("ROLE_MEMBER", "ROLE_ADMIN")
                .mvcMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .mvcMatchers("/member/login").anonymous()
                .mvcMatchers("/**", "/member/**").permitAll()
                .and()
                    .formLogin()
                    .loginPage("/member/login")             
                    .defaultSuccessUrl("/")  
                    .failureForwardUrl("/member/loginfail")
                    .usernameParameter("memberId")			// 아이디 파라미터명 설정
                    .passwordParameter("memberPwd")			// 패스워드 파라미터명 설정
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
    			.and()
    				.exceptionHandling()
					.accessDeniedPage("/error/denied")
				.and()
    				.build();
    }

	/* 4. 사용자 인증을 위해서 사용할 Service 등록, 사용 할 비밀번호 인코딩 방식 설정 */
	@Bean
	public AuthenticationManager authManager(HttpSecurity http) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	      .userDetailsService(authenticationService)
	      .passwordEncoder(passwordEncoder())
	      .and()
	      .build();
	}

}
















