package com.reddit.spring.repository;

import com.reddit.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE user a " +
            "SET a.enabled = TRUE WHERE a.username = ?1")
    int enableUser(String email);
}
