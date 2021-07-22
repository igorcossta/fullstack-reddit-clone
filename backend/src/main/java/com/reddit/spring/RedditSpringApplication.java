package com.reddit.spring;

import com.reddit.spring.model.User;
import com.reddit.spring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

import static com.reddit.spring.model.Role.ADMIN;
import static com.reddit.spring.model.Role.USER;

@SpringBootApplication
@EnableAsync
public class RedditSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        User user = new User(
                "ana",
                "smith",
                "pro@email.com",
                passwordEncoder.encode("0123"),
                ADMIN
        );
        User user2 = new User(
                "Pamela",
                "Costa",
                "trainee@email.com",
                passwordEncoder.encode("0123"),
                USER
        );
        user.setEnabled(true);
        user2.setEnabled(true);
        userRepository.saveAll(Arrays.asList(user, user2));

        return args -> {};
    }

}
