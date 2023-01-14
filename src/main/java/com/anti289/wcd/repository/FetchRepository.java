package com.anti289.wcd.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anti289.wcd.repository.entity.FetchEntity;
import com.anti289.wcd.repository.entity.UserEntity;


@Repository
public interface FetchRepository extends CrudRepository<FetchEntity, UUID> {

	FetchEntity findByUserAndUrl(UserEntity user, String url);

	List<FetchEntity> findByUser(UserEntity user);
}
