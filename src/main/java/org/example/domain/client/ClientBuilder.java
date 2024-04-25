package org.example.domain.client;

import java.math.BigDecimal;

public class ClientBuilder {
    private String name = "";
    private String email = "";
    private String password = "";
    private BigDecimal fund = BigDecimal.ZERO;

    public ClientBuilder() {
    }

    public ClientBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder email(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder password(String password) {
        this.password = password;
        return this;
    }

    public ClientBuilder fund(BigDecimal fund) {
        this.fund = fund;
        return this;
    }

    public ClientEntity build() {
        return new ClientEntity(name, email, password, fund);
    }
}
