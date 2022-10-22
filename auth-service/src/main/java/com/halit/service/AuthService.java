package com.halit.service;

import com.halit.dto.request.ActivateReguestDto;
import com.halit.dto.request.LoginRequestDto;
import com.halit.dto.request.NewCreateUserDto;
import com.halit.dto.request.RegisterRequestDto;
import com.halit.dto.response.LoginResponseDto;
import com.halit.dto.response.RegisterResponseDto;
import com.halit.exception.AuthManagerException;
import com.halit.exception.ErrorType;
import com.halit.manager.IUserManager;
import com.halit.mapper.IAuthMapper;
import com.halit.repository.IAuthRepository;
import com.halit.repository.entity.Auth;
import com.halit.repository.enums.Roles;
import com.halit.repository.enums.Status;
import com.halit.utility.CodeGenaretor;
import com.halit.utility.JwtTokenManager;
import com.halit.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * 1-ActiveterequstDto -> id ,activateCode
 * - booelaan dönüslü bir end point (ActiveterequstDto )
 * servicede gelen dto dan kontroller yapacağız
 * databasede bu ıdli kullanıcı varmı
 * varsa gondrdiğimiz code ile databasede ki kod aynı mı
 *
 *
 *
 *
 */
@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    private final IUserManager userManager;

    private JwtTokenManager jwtTokenManager;


    public AuthService(IAuthRepository authRepository,IUserManager userManager,JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userManager=userManager;
        this.jwtTokenManager=jwtTokenManager;
    }


    public RegisterResponseDto register(RegisterRequestDto dto) {

        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);


//        if (userIsExist(dto.getUsername())){
//            throw  new AuthManagerException(ErrorType.USERNAME_DUPLICATE);
//        }else {
//
//            if (dto.getAdminCode()!=null&& dto.getAdminCode().equals("admin"))  {
//                auth.setRole(Roles.ADMIN);
//            }
//            try {
//                return   save(auth);
//            }catch (Exception e){
//                throw  new AuthManagerException(ErrorType.USER_NOT_CREATED);
//            }
//
//        }
            if (dto.getAdminCode()!=null&& dto.getAdminCode().equals("admin"))  {
                auth.setRole(Roles.ADMIN);
             }
            try {
                auth.setActivatedCode(CodeGenaretor.generateCode(UUID.randomUUID().toString()));
                 save(auth);
                 userManager.createUser(NewCreateUserDto.builder()
                         .authid(auth.getId())
                         .email(auth.getEmail())
                         .username(auth.getUsername())
                         .build());
                 return  IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
            }catch (AuthManagerException a){
                throw  new AuthManagerException(ErrorType.USER_NOT_CREATED);

            }


            }










    public  boolean userIsExist(String username){

   return authRepository.existUserName(username);
    }

    public Optional<LoginResponseDto> login(LoginRequestDto dto) {
        Optional<Auth> auth=authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());

           if (auth.isPresent()){
                LoginResponseDto loginResponseDto=IAuthMapper.INSTANCE.toLoginResponseDto(auth.get());
                String token= jwtTokenManager.createToken(loginResponseDto.getId());
                loginResponseDto.setToken(token);
               return Optional.of(loginResponseDto);
           }else {
               throw  new AuthManagerException(ErrorType.LOGIN_ERROR_WRONG);
           }
    }

    public boolean activeteStatus(ActivateReguestDto dto) {
        Optional<Auth> auth=authRepository.findById(dto.getId());
        if (auth.isEmpty()){
            throw  new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (auth.get().getActivatedCode().equals(dto.getActivatedCode())){
            auth.get().setStatus(Status.ACTIVE);
            userManager.activateStatus(dto.getId());
            save(auth.get());
            return true;
        }

        throw  new AuthManagerException(ErrorType.INVALID_ACTİVATE_CODE);


    }
}
