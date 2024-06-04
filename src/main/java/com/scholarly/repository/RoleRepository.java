package com.scholarly.repository;

import com.scholarly.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {

    short findIdByName(String nane);

    default short getDefaultRoleId() {
        return findIdByName("user");
    }
}
