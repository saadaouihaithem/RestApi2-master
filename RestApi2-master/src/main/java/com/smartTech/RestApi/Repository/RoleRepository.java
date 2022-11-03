package com.smartTech.RestApi.Repository;

import com.smartTech.RestApi.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
