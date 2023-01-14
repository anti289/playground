package com.anti289.wcd.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.anti289.wcd.dto.FetchMe;
import com.anti289.wcd.endpoint.response.FetchMeResponse;
import com.anti289.wcd.repository.entity.FetchEntity;


@Mapper
public interface FetchMapper {

	FetchMe map(FetchEntity entity);

	List<FetchMe> map(List<FetchEntity> entities);

	FetchMeResponse mapResponse(FetchMe fetchMe);
	List<FetchMeResponse> mapResponse(List<FetchMe> fetchMes);
}
