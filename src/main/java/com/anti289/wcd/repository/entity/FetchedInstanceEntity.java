package com.anti289.wcd.repository.entity;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class FetchedInstanceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Lob
	private byte[] data;

	@CreatedDate
	private Instant createdDate;

	@ManyToOne
	private FetchEntity fetch;
}
