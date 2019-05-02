package com.paraett.companiesservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="zuul-api-gateway-server")
@RibbonClient(name="free-days-service")
public interface FreeDaysServiceProxy {
    @DeleteMapping("/free-days-service/free-days")
    ResponseEntity<Object> deleteFreeDays(@RequestParam(name="companyId") Long companyId);
}