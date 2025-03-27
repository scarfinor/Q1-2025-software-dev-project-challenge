package com.challenge.LaunchCode.repositories;

import com.challenge.LaunchCode.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
