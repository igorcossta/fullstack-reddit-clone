package com.reddit.spring.repository;

import com.reddit.spring.model.Comment;
import com.reddit.spring.model.Post;
import com.reddit.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentRepository> findByPost(Post post);
    List<CommentRepository> findAllByUser(User user);
}
