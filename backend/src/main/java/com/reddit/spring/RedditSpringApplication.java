package com.reddit.spring;

import com.reddit.spring.model.Post;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.model.User;
import com.reddit.spring.repository.PostRepository;
import com.reddit.spring.repository.SubredditRepository;
import com.reddit.spring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
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
            PostRepository postRepository,
            SubredditRepository subredditRepository,
            PasswordEncoder passwordEncoder
    ) {
        User user = new User(
                "ana",
                "smith",
                "ana@edu.br",
                passwordEncoder.encode("Ana@1234"),
                ADMIN
        );
        User user2 = new User(
                "Pamela",
                "Costa",
                "pam@edu.br",
                passwordEncoder.encode("Pam@1234"),
                USER
        );
        user.setEnabled(true);
        user2.setEnabled(true);
        userRepository.saveAll(Arrays.asList(user, user2));

        Subreddit subreddit = new Subreddit();
        subreddit.setUser(user);
        subreddit.setDescription("Description");
        subreddit.setName("SubredditName");
        subreddit.setCreatedDate(Instant.now());
        subreddit.setPosts(null);
        subredditRepository.save(subreddit);

        Post post = new Post();
        post.setSubreddit(subreddit);
        post.setUser(user2);
        post.setPostName("Namaste");
        post.setDescription("Description Post");
        post.setCreatedDate(Instant.now());
        post.setVoteCount(0);
        post.setUrl("RENDERIZAR CONTEUDO");

        postRepository.save(post);
        return args -> {
        };
    }

}
