package com.roleAuth.roleAuthorisation.Repository;

import com.roleAuth.roleAuthorisation.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
