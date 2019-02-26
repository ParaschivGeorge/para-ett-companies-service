package com.paraett.companiesservice.model.dtos;

import com.paraett.companiesservice.model.entities.Company;

public class CompanyRegisterDto {
    private Company company;
    private OwnerRegisterUserDto ownerRegisterUserDto;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public OwnerRegisterUserDto getOwnerRegisterUserDto() {
        return ownerRegisterUserDto;
    }

    public void setOwnerRegisterUserDto(OwnerRegisterUserDto ownerRegisterUserDto) {
        this.ownerRegisterUserDto = ownerRegisterUserDto;
    }

    public CompanyRegisterDto(Company company, OwnerRegisterUserDto ownerRegisterUserDto) {
        this.company = company;
        this.ownerRegisterUserDto = ownerRegisterUserDto;
    }

    public CompanyRegisterDto() {
    }
}
