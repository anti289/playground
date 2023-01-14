package com.anti289.wcd.repository;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.data.repository.CrudRepository;

import com.anti289.wcd.repository.entity.FetchedInstanceEntity;


public interface FetchedInstanceRepository extends CrudRepository<FetchedInstanceEntity, UUID> {

	Optional<FetchedInstanceEntity> findFirstByFetch_IdOrderByCreatedDateDesc(UUID fetchId);

	Stream<FetchedInstanceEntity> findAllByFetch_Id(UUID fetchId);
}
