package com.paraett.companiesservice.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name = "company_tbl")
public class Company {
    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Max(50)
    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Max(255)
    @Column
    private String description;

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
