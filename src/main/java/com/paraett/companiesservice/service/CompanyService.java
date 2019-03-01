package com.paraett.companiesservice.service;

import com.paraett.companiesservice.exception.CompanyNotFoundException;
import com.paraett.companiesservice.model.dtos.CompanyRegisterDto;
import com.paraett.companiesservice.model.entities.Company;
import com.paraett.companiesservice.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Company> optCompany = this.companyRepository.findById(id);

        if (optCompany.isPresent()) {
            companyRepository.deleteById(id);
            return;
        }
        throw new CompanyNotFoundException("id-" + id);
    }

    public Company updateCompany(Long id, Company company) {
        Optional<Company> optCompany = this.companyRepository.findById(id);

        if (optCompany.isPresent()) {
            company.setId(optCompany.get().getId());
            return companyRepository.save(company);
        }
        throw new CompanyNotFoundException("id-" + id);
    }

    public Company getCompany(Long id) {
        Optional<Company> optCompany = this.companyRepository.findById(id);
        if (optCompany.isPresent()) {
            return optCompany.get();
        }
        throw new CompanyNotFoundException("id-" + id);
    }

    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }
}
