package org.example.domain.DAO;

import org.example.domain.entity.ClientBuilder;
import org.example.domain.entity.ClientEntity;

import java.math.BigDecimal;

public class DefaultClientEntityDAOImpl extends DefaultLongIDEntityDAO<ClientEntity> implements ClientDAO {

    @Override
    protected void initSampleData() {
        create(new ClientBuilder()
                .name("Jack")
                .email("jackabl@gmail.com")
                .password("a")
                .build());
        create(new ClientEntity("Lily", "lilyado@pas.com", "b", BigDecimal.ZERO));
        create(new ClientEntity("Albert", "aberta@cosco.com", "c", BigDecimal.ZERO));
    }
}
