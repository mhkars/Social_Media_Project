package com.halit.controller;

import com.halit.dto.request.ActivateReguestDto;
import com.halit.dto.request.LoginRequestDto;
import com.halit.dto.request.RegisterRequestDto;
import com.halit.dto.response.LoginResponseDto;
import com.halit.dto.response.RegisterResponseDto;
import com.halit.service.AuthService;
import com.halit.utility.JwtTokenManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;
    private  final JwtTokenManager jwtTokenManager;


    @PostMapping("/register")
    @Operation(summary = "Kullanıcı kayıt eden metot")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));


    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto){


        return  ResponseEntity.ok(authService.login(dto).get());
    }

    @GetMapping("/token")
    public String getToken(Long id){

return jwtTokenManager.createToken(id);
    }
    @GetMapping("/getId")
    public Long getId(String token){

        return jwtTokenManager.getUserId(token).get();
    }



        @PostMapping("/activate")
        public ResponseEntity<Boolean> activateStatus(@RequestBody  ActivateReguestDto dto){

           return   ResponseEntity.ok(authService.activeteStatus(dto))  ;

        }



}
