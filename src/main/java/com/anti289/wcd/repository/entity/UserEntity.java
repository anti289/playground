package com.anti289.wcd.repository.entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(unique = true)
	private String externalId;

	@Column(unique = true)
	private String name;

	@OneToMany
	private List<FetchEntity> fetchMes;

	@CreatedDate
	private Instant createdDate;

}
