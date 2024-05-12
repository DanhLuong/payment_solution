import org.example.domain.entity.BillBuilder;
import org.example.domain.entity.BillEntity;
import org.example.utils.printer.DefaultEntityPrinterImpl;
import org.example.utils.printer.EntityPrinter;
import org.example.utils.printer.EntityPrinterManager;
import org.example.utils.printer.PrinterManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PrinterTest {
    private PrinterManager printerManager;
    private List<BillEntity> bills = new ArrayList<>();
    private static final String BILL_LIST_1 = "bill_list_1";

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
        printerManager.addPrinter(BILL_LIST_1, billPrinter1);
        printerManager.doneConfig();
        bills.add(
                new BillBuilder()
                        .withId(1001)
                        .forClientId(1L)
                        .provider("EVN")
                        .serviceType("Electric")
                        .amount(new BigDecimal("569000"))
                        .dueDate(new Date(2024, Calendar.APRIL,2,0,0))
                        .build()
        );

        bills.add(
                new BillBuilder()
                        .withId(1002)
                        .forClientId(1L)
                        .provider("SWC")
                        .serviceType("Water")
                        .amount(new BigDecimal("182000"))
                        .dueDate(new Date(2024, Calendar.MAY,2,0,0))
                        .build()
        );

        bills.add(
                new BillBuilder()
                        .withId(1003)
                        .forClientId(1L)
                        .provider("FOODIE")
                        .serviceType("Food")
                        .amount(new BigDecimal("362000"))
                        .dueDate(new Date(2024, Calendar.JANUARY,2,0,0))
                        .build()
        );
    }
    @Test
    public void  BillEntityPrinterTest() {
        String printResult = printerManager.getPrinter(BILL_LIST_1).getPrintResult(bills);
        System.out.println(printResult);
        String testedResult = "BILL_ID        TYPE           AMOUNT         DUE_DATE       PROVIDER       IS_PAID        \n" +
                              "1001           Electric       569000         04/02/24       EVN            false          \n" +
                              "1002           Water          182000         05/02/24       SWC            false          \n" +
                              "1003           Food           362000         01/02/24       FOODIE         false          \n";
        Assert.assertEquals(printResult, testedResult);
    }
}
