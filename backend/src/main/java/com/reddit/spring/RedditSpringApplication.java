package com.reddit.spring;

import com.reddit.spring.appuser.AppUser;
import com.reddit.spring.appuser.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.reddit.spring.model.Role.ADMIN;
import static com.reddit.spring.model.Role.USER;

@SpringBootApplication
@EnableAsync
@Slf4j
public class RedditSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        AppUser user = new AppUser(
                "ana",
                "smith",
                "pro@email.com",
                passwordEncoder.encode("0123"),
                ADMIN
        );
        user.setEnabled(true);
        appUserRepository.save(user);

        return args -> {};
    }

}
