package com.halit.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequestDto {
    @NotNull
    String token;
    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;
}
