package com.paraett.companiesservice.proxy;

import com.paraett.companiesservice.model.dtos.OwnerRegisterUserDto;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="users-service")
public interface UsersServiceProxy {
    @PostMapping("/users-service/users/registerOwner")
    public ResponseEntity<Object> registerOwner(@RequestBody OwnerRegisterUserDto ownerRegisterUserDto, @RequestParam(name="companyId") Long companyId);
}
