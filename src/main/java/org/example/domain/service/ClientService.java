package org.example.domain.service;

import java.math.BigDecimal;

public interface ClientService {
    void addFund(Long clientID, BigDecimal amount);
    BigDecimal showFund(Long clientID);
    boolean checkEnoughFund(Long clientID, BigDecimal payment);
    boolean authenticate(Long id, String password);
}
