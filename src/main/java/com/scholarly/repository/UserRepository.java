package com.scholarly.repository;

import com.scholarly.dto.Profile;
import com.scholarly.dto.Credentials;
import com.scholarly.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Profile> findProfileById(long id);

    Optional<Credentials> findCredentialsByUsername(String username);

    Optional<String> findPasswordById(long id);

    @Transactional
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    void updatePassword(long id, String password);

    List<User> findByUsernameContaining(String like, PageRequest pr);
}
