package com.anti289.wcd.endpoint.response;

import java.util.List;


public record FetchEmListResponse(String username, List<FetchMeResponse> fetchMes) {
}
