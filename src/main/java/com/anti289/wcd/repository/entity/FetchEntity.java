package com.anti289.wcd.repository.entity;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Version;
import lombok.Data;


@Entity
@Data
public class FetchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column
	private String url;

	@ManyToOne
	private UserEntity user;

	@CreatedDate
	private Instant createdDate;

	@LastModifiedDate
	private Instant modifiedDate;

	@Version
	private Integer version;
}
