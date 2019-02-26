package com.paraett.companiesservice.controller;

import com.paraett.companiesservice.model.dtos.CompanyRegisterDto;
import com.paraett.companiesservice.model.entities.Company;
import com.paraett.companiesservice.proxy.UsersServiceProxy;
import com.paraett.companiesservice.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private UsersServiceProxy usersServiceProxy;

    public CompanyController(CompanyService companyService, UsersServiceProxy usersServiceProxy) {
        this.companyService = companyService;
        this.usersServiceProxy = usersServiceProxy;
    }

    @PostMapping("")
    public ResponseEntity<Object> createCompany(@RequestBody CompanyRegisterDto companyRegisterDto) {
        Company company = this.companyService.createCompany(companyRegisterDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(company.getId())
                .toUri();

        ResponseEntity<Object> response = usersServiceProxy.registerOwner(companyRegisterDto.getOwnerRegisterUserDto(), Math.toIntExact(company.getId()));

        if (response.getStatusCode() != HttpStatus.CREATED) {
            this.companyService.deleteCompany(company.getId());
            return response;
        }

        return ResponseEntity.created(location).body(company);
    }
}
