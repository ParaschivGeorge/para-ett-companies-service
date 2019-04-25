package com.paraett.companiesservice.controller;

import com.paraett.companiesservice.model.dtos.CompanyRegisterDto;
import com.paraett.companiesservice.model.entities.Company;
import com.paraett.companiesservice.proxy.FreeDaysServiceProxy;
import com.paraett.companiesservice.proxy.RequestsServiceProxy;
import com.paraett.companiesservice.proxy.TimesheetRecordsServiceProxy;
import com.paraett.companiesservice.proxy.UsersServiceProxy;
import com.paraett.companiesservice.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;
    private UsersServiceProxy usersServiceProxy;
    private RequestsServiceProxy requestsServiceProxy;
    private FreeDaysServiceProxy freeDaysServiceProxy;
    private TimesheetRecordsServiceProxy timesheetRecordsServiceProxy;

    public CompanyController(CompanyService companyService, UsersServiceProxy usersServiceProxy, RequestsServiceProxy requestsServiceProxy, FreeDaysServiceProxy freeDaysServiceProxy, TimesheetRecordsServiceProxy timesheetRecordsServiceProxy) {
        this.companyService = companyService;
        this.usersServiceProxy = usersServiceProxy;
        this.requestsServiceProxy = requestsServiceProxy;
        this.freeDaysServiceProxy = freeDaysServiceProxy;
        this.timesheetRecordsServiceProxy = timesheetRecordsServiceProxy;
    }

    @GetMapping("")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = this.companyService.getAllCompanies();

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = this.companyService.getCompany(id);

        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCompany(@PathVariable Long id) {
        this.companyService.deleteCompany(id);
        this.usersServiceProxy.deleteUsers(id);
        this.usersServiceProxy.deleteProjects(id);
        this.requestsServiceProxy.deleteRequests(id);
        this.freeDaysServiceProxy.deleteFreeDays(id);
        this.timesheetRecordsServiceProxy.deleteTimesheetRecords(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<Object> createCompany(@RequestBody CompanyRegisterDto companyRegisterDto) {
        Company company = this.companyService.createCompany(companyRegisterDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(company.getId())
                .toUri();

        ResponseEntity<Object> response = usersServiceProxy.registerOwner(companyRegisterDto.getOwnerRegisterUserDto(), company.getId());

        if (response.getStatusCode() != HttpStatus.CREATED) {
            this.companyService.deleteCompany(company.getId());
            return response;
        }

        return ResponseEntity.created(location).body(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Company updatedCompany = this.companyService.updateCompany(id, company);

        return ResponseEntity.accepted().body(updatedCompany);
    }
}
