package com.paraett.companiesservice.service;

import com.paraett.companiesservice.model.dtos.CompanyRegisterDto;
import com.paraett.companiesservice.model.entities.Company;
import com.paraett.companiesservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(CompanyRegisterDto companyRegisterDto) {
        Company company = this.companyRepository.save(companyRegisterDto.getCompany());

        return company;
    }

    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
