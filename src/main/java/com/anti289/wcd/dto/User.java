package com.anti289.wcd.dto;

import java.util.UUID;


public record User(UUID id, String externalId, String name) {
}
