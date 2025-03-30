package com.challenge.LaunchCode.repositories;

import com.challenge.LaunchCode.models.ERole;
import com.challenge.LaunchCode.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);

}
