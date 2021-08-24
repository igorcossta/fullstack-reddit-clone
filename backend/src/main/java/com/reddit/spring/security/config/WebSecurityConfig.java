package com.reddit.spring.security.config;

import com.reddit.spring.filter.CustomAccessDeniedHandler;
import com.reddit.spring.filter.CustomAuthenticationFilter;
import com.reddit.spring.filter.CustomAuthorizationFilter;
import com.reddit.spring.filter.CustomForbiddenEntryPoint;
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

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
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
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/signin");

        http.cors();
        http.csrf().disable().formLogin().disable().rememberMe().disable().logout().disable().httpBasic().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        // open endpoints
        http.authorizeRequests()
                .antMatchers(POST, "/api/signin/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers(GET, "/api/subreddit/**").permitAll()
                .antMatchers(GET, "/api/post/**").permitAll()
                .antMatchers(GET, "/api/comment/**").permitAll();

        // user endpoints
        http.authorizeRequests()
                .antMatchers(POST, "/api/subreddit/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(POST, "/api/post/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(POST, "/api/comment/**").hasAnyAuthority("USER", "ADMIN");


        // admin endpoints
        http.authorizeRequests()
                .antMatchers(GET, "/api/admin/**").hasAnyAuthority("ADMIN");

        http.authorizeRequests().anyRequest().authenticated();

        // filters
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        // exception handler
        http.exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(new CustomForbiddenEntryPoint());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/swagger-ui/**").antMatchers("/swagger-resources/**").antMatchers("/v2/api-docs/**");
    }
}
