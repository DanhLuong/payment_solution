package org.example.domain.client;

import java.math.BigDecimal;

//All method is in transaction in case real DB (wrap by a transactional proxy)
public class DefaultClientServiceImpl implements ClientService{
    private final ClientDAO clientDAO;
    public DefaultClientServiceImpl(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    @Override
    public void addFund(Long clientID, BigDecimal amount) {
        ClientEntity client = clientDAO.findByID(clientID);
        client.setFund(client.getFund().add(amount));
        clientDAO.update(clientID, client);
    }

    @Override
    public BigDecimal showFund(Long clientID) {
        ClientEntity client = clientDAO.findByID(clientID);
        return client.getFund();
    }

    @Override
    public boolean checkEnoughFund(Long clientID, BigDecimal payment) {
        ClientEntity client = clientDAO.findByID(clientID);
        return payment.compareTo(client.getFund()) < 0;
    }

    @Override
    public boolean authenticate(Long id, String password) {
        ClientEntity client = clientDAO.findByID(id);
        if(client == null) {
            return false;
        }
        return client.getPassword().equals(password.trim());
    }
}
