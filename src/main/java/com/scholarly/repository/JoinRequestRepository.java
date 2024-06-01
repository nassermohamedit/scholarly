package com.scholarly.repository;

import com.scholarly.entity.JoinRequest;
import com.scholarly.entity.ProfileTopicId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoinRequestRepository extends JpaRepository<JoinRequest, ProfileTopicId> {
}
