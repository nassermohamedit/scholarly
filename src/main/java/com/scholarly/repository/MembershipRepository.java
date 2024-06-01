package com.scholarly.repository;

import com.scholarly.entity.Membership;
import com.scholarly.entity.ProfileTopicId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, ProfileTopicId> {
}
