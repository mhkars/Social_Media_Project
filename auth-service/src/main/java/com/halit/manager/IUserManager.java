package com.halit.manager;

import com.halit.dto.request.ActivateReguestDto;
import com.halit.dto.request.NewCreateUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "http://localhost:8091/user",name = "user-service-userprofile",decode404 = true)
public interface IUserManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserDto dto);
    @PostMapping("/activate")
    public ResponseEntity<Boolean> activateStatus(@RequestBody ActivateReguestDto dto);

    @PostMapping("/activate/{authid}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authid);

}
