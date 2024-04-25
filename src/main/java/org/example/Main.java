package org.example;

import org.example.domain.client.ClientDAO;
import org.example.domain.client.ClientService;
import org.example.domain.client.DefaultClientEntityDAOImpl;
import org.example.domain.client.DefaultClientServiceImpl;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        //<<<<<<<<<<<<<<<<<<--config object to use-->>>>>>>>>>>>>>>>>>>>>>>
        //create object and make Dependency Injection manually here
        ClientDAO clientDAO = new DefaultClientEntityDAOImpl();
        ClientService clientService = new DefaultClientServiceImpl(clientDAO);
        //<<<<<<<<<<<<<<<<<<--config object to use-->>>>>>>>>>>>>>>>>>>>>>>
        //<<<<<<<<<<<<<<<<<<--run the program here-->>>>>>>>>>>>>>>>>>>>>>>
        System.out.println(clientService.showFund(1L));
        clientService.addFund(1L, new BigDecimal("100000"));
        System.out.println(clientService.showFund(1L));
        System.out.println(clientService.checkEnoughFund(1L, new BigDecimal("200000")));

        //<<<<<<<<<<<<<<<<<<--run the program here-->>>>>>>>>>>>>>>>>>>>>>>
    }
}