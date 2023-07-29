package com.eviro.assessment.grad001.njabulokhoza.model;

import jakarta.persistence.*;

@Entity
@Table(name = "account_profile")
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    @Column(name = "account_holder_surname")
    private String accountHolderSurname;
    @Column(name = "http_image_link")
    private String httpImageLink;

    public AccountProfile() {
    }
    public AccountProfile(String accountHolderName,
                          String accountHolderSurname,
                          String httpImageLink) {
        this.accountHolderName = accountHolderName;
        this.accountHolderSurname = accountHolderSurname;
        this.httpImageLink = httpImageLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAccountHolderSurname() {
        return accountHolderSurname;
    }

    public void setAccountHolderSurname(String accountHolderSurname) {
        this.accountHolderSurname = accountHolderSurname;
    }

    public String getHttpImageLink() {
        return httpImageLink;
    }

    public void setHttpImageLink(String httpImageLink) {
        this.httpImageLink = httpImageLink;
    }
}
