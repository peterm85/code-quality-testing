package com.example.code.repository;

import com.example.code.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserJpaRepository extends CrudRepository<UserEntity, Integer> {}
