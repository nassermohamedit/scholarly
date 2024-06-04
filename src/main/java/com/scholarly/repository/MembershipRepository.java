package com.scholarly.repository;

import com.scholarly.entity.Membership;
import com.scholarly.entity.UserTopicId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MembershipRepository extends JpaRepository<Membership, UserTopicId> {
}
