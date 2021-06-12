package com.reddit.spring;

import com.reddit.spring.model.Comment;
import com.reddit.spring.model.Post;
import com.reddit.spring.model.Subreddit;
import com.reddit.spring.model.User;
import com.reddit.spring.repository.CommentRepository;
import com.reddit.spring.repository.PostRepository;
import com.reddit.spring.repository.SubredditRepository;
import com.reddit.spring.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;

@SpringBootApplication
@EnableAsync
@Slf4j
public class RedditSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditSpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            SubredditRepository subredditRepository,
            PostRepository postRepository,
            CommentRepository commentRepository,
            PasswordEncoder passwordEncoder
    ) {
        User user = new User();
        user.setUsername("anasmith");
        user.setPassword(passwordEncoder.encode("123"));
        user.setEmail("ana@email.com");
        user.setCreated(Instant.now());
        user.setEnabled(true);
        userRepository.save(user);

        Subreddit subreddit = new Subreddit();
        subreddit.setUser(user);
        subreddit.setCreatedDate(Instant.now());
        subreddit.setName("Reddit title");
        subreddit.setDescription("Descrição do subreddit");
        subreddit.setPosts(null);
        subredditRepository.save(subreddit);

        Post post = new Post();
        post.setUser(user);
        post.setSubreddit(subreddit);
        post.setCreatedDate(Instant.now());
        post.setPostName("Post title");
        post.setDescription("Descrição do post");
        post.setUrl("http://localhost:8080/api/post");
        postRepository.save(post);

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedDate(Instant.now().plusSeconds(1300));
        comment.setText("Comentário do post " + post.getPostName());
        commentRepository.save(comment);

        return args -> {
            log.info("dummy created");
        };
    }

}
