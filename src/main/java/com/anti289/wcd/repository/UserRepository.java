package com.anti289.wcd.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anti289.wcd.repository.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, UUID> {

	UserEntity findByName(String name);

	UserEntity findByNameAndExternalId(String name, String externalId);

}
