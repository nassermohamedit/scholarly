package com.scholarly.repository;

import com.scholarly.dto.Profile;
import com.scholarly.dto.Credentials;
import com.scholarly.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Profile> findProfileById(long id);

    Optional<Credentials> findCredentialsByUsername(String username);

}
