package com.halit.controller;


import com.halit.dto.request.ActivateReguestDto;
import com.halit.dto.request.NewCreateUserDto;
import com.halit.dto.request.UpdateRequestDto;
import com.halit.exception.ErrorType;
import com.halit.exception.UserManagerException;
import com.halit.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * -buil gradle ba��ml�l�klar�n� ekleyelim
 * -NewUserCreateDto ->
 * -bir create metodu olu�turual�m (NewUserCreateDto)
 *
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserProfileController {

private final UserProfileService userProfileService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto){

        try {
            userProfileService.createUser(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw  new UserManagerException(ErrorType.USER_NOT_CREATED);
        }

    }

    @PostMapping("/activate")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateReguestDto dto){

        return ResponseEntity.ok(userProfileService.activateStatus(dto));

    }
    @PostMapping("/activate/{authid}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid){

        return ResponseEntity.ok(userProfileService.activateStatus(authid));

    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateProfile(@RequestBody @Valid UpdateRequestDto dto){


        return ResponseEntity.ok(userProfileService.updateUser(dto)) ;
    }


}
