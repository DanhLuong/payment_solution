import org.example.domain.DAO.ClientDAO;
import org.example.domain.DAO.DefaultClientEntityDAOImpl;
import org.example.domain.service.ClientService;
import org.example.domain.service.DefaultClientServiceImpl;
import org.junit.Before;
import org.junit.Test;

public class ClientServiceTest {
    private ClientDAO clientDAO;
    private ClientService clientService;

    @Before
    public void init() {
        clientDAO = new DefaultClientEntityDAOImpl();
        clientService = new DefaultClientServiceImpl(clientDAO);
    }

    @Test
    public void some() {

    }
}
