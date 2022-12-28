package com.anti289.wcd.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anti289.wcd.repository.entity.UserEntity;


@Repository
interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByName(String name);
}
