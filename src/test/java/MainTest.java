import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void some() {
//        DefaultEntityPrinterImpl<ClientEntity> clientPrint = new DefaultEntityPrinterImpl<>();
//        DefaultEntityPrinterImpl<BillEntity> billPrint = new DefaultEntityPrinterImpl<>();
//        System.out.println(clientPrint.clazz.toString());
//        System.out.println(clientPrint.clazz.toString());
        String origin = "TYPE";
        String format = "%-15s";
        System.out.println(String.format(format, origin));
    }

}
