package com.halit.mapper;

import com.halit.dto.request.NewCreateUserDto;
import com.halit.dto.request.UpdateRequestDto;
import com.halit.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,componentModel = "spring")
public interface IUserMapper {


    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);


    UserProfile toUserProfile(final NewCreateUserDto dto);


    UserProfile toUserProfile(final UpdateRequestDto dto);




}
