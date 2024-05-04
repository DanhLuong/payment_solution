package org.example.domain.client;

import org.example.domain.common.BaseEntity;

import java.math.BigDecimal;

public class ClientEntity extends BaseEntity<Long> {
    private String name;
    private String email;
    private String password;
    private BigDecimal fund;

    public ClientEntity(String name, String email, String password, BigDecimal fund) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.fund = fund;
    }

    public BigDecimal getFund() {
        return fund;
    }

    public void setFund(BigDecimal fund) {
        this.fund = fund;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
