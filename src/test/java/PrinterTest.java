import org.example.domain.entity.BillBuilder;
import org.example.domain.entity.BillEntity;
import org.example.utils.printer.DefaultEntityPrinterImpl;
import org.example.utils.printer.EntityPrinter;
import org.example.utils.printer.EntityPrinterManager;
import org.example.utils.printer.PrinterManager;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrinterTest {
    PrinterManager printerManager;
    List<BillEntity> bills = new ArrayList<>();

    @Before
    public void init() {
        EntityPrinter<BillEntity> billPrinter1 = new DefaultEntityPrinterImpl<>(BillEntity.class);
        billPrinter1.registerColumn("BILL_ID", "id");
        billPrinter1.registerColumn("TYPE", "serviceType");
        billPrinter1.registerColumn("AMOUNT", "amount");
        billPrinter1.registerColumn("DUE_DATE", "dueDate");
        billPrinter1.registerColumn("PROVIDER", "provider");
        billPrinter1.registerColumn("IS_PAID", "isPaid");
        billPrinter1.doneConfig();
        printerManager = new EntityPrinterManager();
        printerManager.addPrinter("bill_list_1", billPrinter1);
        printerManager.doneConfig();
        bills.add(
                new BillBuilder()
                        .forClientId(1L)
                        .provider("EVN")
                        .serviceType("Electric")
                        .amount(new BigDecimal("569000"))
                        .dueDate(new Date(2024, Calendar.APRIL,2,0,0))
                        .build()
        );

        bills.add(
                new BillBuilder()
                        .forClientId(1L)
                        .provider("SWC")
                        .serviceType("Water")
                        .amount(new BigDecimal("182000"))
                        .dueDate(new Date(2024, Calendar.MAY,2,0,0))
                        .build()
        );

        bills.add(
                new BillBuilder()
                        .forClientId(1L)
                        .provider("FOODIE")
                        .serviceType("Food")
                        .amount(new BigDecimal("362000"))
                        .dueDate(new Date(2024, Calendar.JANUARY,2,0,0))
                        .build()
        );
//        for(int i =0; i< bills.size(); i++) {
//            bills.get(i).setId((long) i);
//        }
    }
    @Test
    public void  BillEntityTest() {
        printerManager.getPrinter("bill_list_1").print(System.out, bills);
    }
}
