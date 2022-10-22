package com.halit.mapper;

import com.halit.dto.request.LoginRequestDto;
import com.halit.dto.request.NewCreateUserDto;
import com.halit.dto.request.RegisterRequestDto;
import com.halit.dto.response.LoginResponseDto;
import com.halit.dto.response.RegisterResponseDto;
import com.halit.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);
    RegisterRequestDto toRegisterRequestDto(final Auth auth);
    Auth toAuth(final RegisterRequestDto dto);

    Auth toAuth(final  LoginRequestDto dto);

    LoginResponseDto toLoginResponseDto(final Auth auth);

    RegisterResponseDto toRegisterResponseDto(final Auth auth);

    NewCreateUserDto toNewCreateUserDto(final  Auth auth);




}
