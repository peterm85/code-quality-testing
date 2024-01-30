package com.example.code.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.example.code.repository.entity.UserEntity;

@Component
public interface UserJpaRepository extends CrudRepository<UserEntity, Integer> {}
