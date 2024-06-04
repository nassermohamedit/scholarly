package com.scholarly.repository;

import com.scholarly.entity.JoinRequest;
import com.scholarly.entity.UserTopicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JoinRequestRepository extends JpaRepository<JoinRequest, UserTopicId> {
}
