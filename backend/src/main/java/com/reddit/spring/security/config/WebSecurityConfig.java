package com.reddit.spring.security.config;

import com.reddit.spring.jwt.JwtTokenVerifier;
import com.reddit.spring.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final RequestMatcher PUBLIC_ENDPOINT = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/register/**"),
            new AntPathRequestMatcher("/api/signin"),
            new AntPathRequestMatcher("/api/subreddit/**", "GET"),
            // swagger
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/v2/api-docs")
    );
    private static final RequestMatcher PROTECT_ENDPOINT = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/subreddit/**", "POST")
    );
    private static final RequestMatcher ADMIN_ENDPOINT = new OrRequestMatcher(
            new AntPathRequestMatcher("/api/admin/**")
    );
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PUBLIC_ENDPOINT);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .formLogin().disable().rememberMe().disable().logout().disable().httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .addFilterBefore(new JwtTokenVerifier(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .requestMatchers(ADMIN_ENDPOINT).hasAuthority("ADMIN")
                .requestMatchers(PROTECT_ENDPOINT).hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
