package org.example;

import org.example.batch.JobManager;
import org.example.domain.DAO.*;
import org.example.domain.entity.TransactionJoinBillTemplate;
import org.example.domain.service.*;
import org.example.domain.entity.BillEntity;
import org.example.domain.exception.BusinessException;
import org.example.utils.printer.DefaultEntityPrinterImpl;
import org.example.utils.printer.EntityPrinter;
import org.example.utils.printer.EntityPrinterManager;
import org.example.utils.printer.PrinterManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.*;

public class Main {
    private static final String LIST_BILL_PRINTER = "printer$1";
    private static final String LIST_PAYMENT_PRINTER = "printer$2";
    private static final String SCHEDULE_PAYMENT_JOB = "job$1";
    public static void main(String[] args) {
        //<<<<<<<<<<<<<<<<<<--config object to use-->>>>>>>>>>>>>>>>>>>>>>>
        //create object and make Dependency Injection manually here
        ClientDAO clientDAO = new DefaultClientEntityDAOImpl();
        ClientService clientService = new DefaultClientServiceImpl(clientDAO);
        BillDAO billDAO = new DefaultBillEntityDAOImpl();
        BillService billService = new DefaultBillServiceImpl(billDAO);
        TransactionDAO transactionDAO = new DefaultTransactionEntityDAOImpl();
        TransactionService transactionService = new DefaultTransactionServiceImpl(clientDAO, billDAO, transactionDAO);
        Scanner sc = new Scanner(System.in);
        //config printers
        EntityPrinter<BillEntity> list_bill_printer = new DefaultEntityPrinterImpl<>(BillEntity.class);
        list_bill_printer.registerColumn("BILL_ID", "id");
        list_bill_printer.registerColumn("TYPE", "serviceType");
        list_bill_printer.registerColumn("AMOUNT", "amount");
        list_bill_printer.registerColumn("DUE_DATE", "dueDate");
        list_bill_printer.registerColumn("PROVIDER", "provider");
        list_bill_printer.registerColumn("IS_PAID", "isPaid");
        list_bill_printer.doneConfig();
        EntityPrinter<TransactionJoinBillTemplate> list_payment_printer = new DefaultEntityPrinterImpl<>(TransactionJoinBillTemplate.class);
        list_payment_printer.registerColumn("TRAN_ID", "id");
        list_payment_printer.registerColumn("BILL_ID", "billId");
        list_payment_printer.registerColumn("AMOUNT", "amount");
        list_payment_printer.registerColumn("TRAN_DATE", "createdAt");
        list_payment_printer.registerColumn("DUE_DATE", "dueDate");
        list_payment_printer.registerColumn("PROVIDER", "provider");
        list_payment_printer.registerColumn("TYPE", "serviceType");
        list_payment_printer.doneConfig();
        //add printers to printer manager
        EntityPrinterManager printerManager = new EntityPrinterManager();
        printerManager.addPrinter(LIST_BILL_PRINTER, list_bill_printer);
        printerManager.addPrinter(LIST_PAYMENT_PRINTER, list_payment_printer);
        printerManager.doneConfig();
        //config and run all job available
        ScheduledExecutorService job1ExeService = new ScheduledThreadPoolExecutor(1);
        //SchedulePaymentJob job1 = new SchedulePaymentJob(job1ExeService, 1, );
        JobManager jobManager = new JobManager();
        //jobManager.addJob(SCHEDULE_PAYMENT_JOB, job1);
        jobManager.addJob("abc", ()->{
            System.out.println("abc run");
        });
        jobManager.doneConfig();
        jobManager.startAllJobs();
        //<<<<<<<<<<<<<<<<<<--config object to use-->>>>>>>>>>>>>>>>>>>>>>>
        //<<<<<<<<<<<<<<<<<<--run the program here-->>>>>>>>>>>>>>>>>>>>>>>
        final String WELCOME = "Welcome to payment app! \n 1. Sign in \n 2. Support \n 3. Exit \n";
        String answer = null;
        System.out.println(WELCOME);
        do {
            System.out.print("Please choose option to proceed: ");
            answer = sc.nextLine();
            switch (answer) {
                case "1" -> {
                    System.out.print("ID: ");
                    String IDString = sc.nextLine();
                    Long id = 0L;
                    try {
                        id = Long.parseLong(IDString);
                    } catch (NumberFormatException e) {
                        System.out.println("Can not authenticate. ID must be number!");
                        continue;
                    }
                    System.out.print("password: ");
                    String password = sc.nextLine();
                    //verify client
                    if(clientService.authenticate(id, password)) {
                        UUID session = UUID.randomUUID();
                        serve(id,
                                session,
                                sc,
                                printerManager,
                                clientService,
                                billService,
                                transactionService);
                    } else {
                        System.out.println("Incorrect ID or password!");
                    }
                }
                case "2" -> {
                    System.out.println("Contact abcdef for further support");
                    System.out.println(WELCOME);
                }
                case "3" -> System.out.println("Bye bye. See you again!");
                default -> System.out.println("Please choose option:1-2-3 only!!!");
            }
        } while(!answer.equals("3"));
        //<<<<<<<<<<<<<<<<<<------------------------>>>>>>>>>>>>>>>>>>>>>>>
    }

    private static void serve(long clientId,
                              UUID session,
                              Scanner sc,
                              PrinterManager printerManager,
                              ClientService clientService,
                              BillService billService,
                              TransactionService transactionService) {
        System.out.format("Start new session << %s >>===============================================================\n", session);
        final String COMMANDS = "Command format:\n" +
                "1. CASH_IN {amount}                         => add money to current fund\n" +
                "2. LIST_BILL                                => list all bill sorted by descending due date\n" +
                "3. PAY {bill_id1} ... {bill_idx}            => pay the bill with bill_id1 to bill_idx\n" +
                "4. DUE_DATE                                 => check all unpaid bills sorted by descending due date\n" +
                "5. SCHEDULE {bill_id} {pay_date:dd/mm/yyyy} => schedule payment for bill with bill_id at pay_date\n" +
                "6. LIST_PAYMENT                             => list all payment sorted by descending pay date\n" +
                "7. SEARCH_BILL_BY_PROVIDER                  => Search Bill from provider sorted by descending due date\n" +
                "8. EXIT                                     => log out from current\n" +
                "Type your command:";
        System.out.println(COMMANDS);
        String command = null;
        do {
            System.out.print(">>");
            command = sc.nextLine();
            String[] commandToken = command.split(" ");
            List<String> commandArg = new ArrayList<>();
            for (int i = 1; i < commandToken.length; i++) {
                if(!commandToken[i].isEmpty()) {
                    commandArg.add(commandToken[i]);
                }
            }
            switch (commandToken[0].trim().toUpperCase()) {
                case "CASH_IN" -> {
                    if(commandArg.size()!=1) {
                        System.out.println("Wrong command format: this command have 1 argument only!");
                    } else {
                        try {
                            BigDecimal amount = new BigDecimal(commandArg.get(0));
                            clientService.addFund(clientId, amount);
                            System.out.format("Your available balance: %s\n", clientService.showFund(clientId).toString());
                        } catch (NumberFormatException e) {
                            System.out.println("Amount must be number. Operation is canceled!");
                        }

                    }
                }
                case "LIST_BILL" -> {
                    if(!commandArg.isEmpty()) {
                        System.out.println("Wrong command format: this command has no argument");
                    }  else {
                        List<BillEntity> bills = billService.findBillByClientIdSortDueDateDesc(clientId);
                        printerManager.getPrinter(LIST_BILL_PRINTER).print(System.out, bills);
                    }
                }
                case "PAY" -> {
                    if(commandArg.isEmpty()) {
                        System.out.println("Wrong command format: this command has at least 1 argument");
                    } else {
                        List<Long> billIds = new ArrayList<>();
                        try {
                            for(String str : commandArg) {
                                billIds.add(Long.parseLong(str));
                            }
                            transactionService.payBillWithBillIds(clientId, billIds);
                        } catch (NumberFormatException e) {
                            System.out.println("Wrong command format: Bill Id must be number");
                        } catch (BusinessException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                case "DUE_DATE" -> {
                    if(!commandArg.isEmpty()) {
                        System.out.println("Wrong command format: this command has no argument");
                    }  else {
                        List<BillEntity> bills = billService.findByClientIdSortByDueDateDesc(clientId, false);
                        printerManager.getPrinter(LIST_BILL_PRINTER).print(System.out, bills);
                    }
                }
                case "SCHEDULE" -> {
                    System.out.println("SCHEDULE");

                }
                case "LIST_PAYMENT" -> {
                    if(!commandArg.isEmpty()) {
                        System.out.println("Wrong command format: this command has no argument");
                    }  else {
                        List<TransactionJoinBillTemplate> templates = transactionService.findTranByClientIdSortByPayDateDesc(clientId);
                        printerManager.getPrinter(LIST_PAYMENT_PRINTER).print(System.out, templates);
                    }
                }
                case "SEARCH_BILL_BY_PROVIDER" -> {
                    if(commandArg.size() != 1) {
                        System.out.println("Wrong command format: this command have 1 argument only!");
                    }  else {
                        List<BillEntity> bills = billService.findByClientIdAndProviderSortByDueDateDesc(clientId, commandArg.get(0), false);
                        printerManager.getPrinter(LIST_BILL_PRINTER).print(System.out, bills);
                    }
                }
                case "EXIT" -> {}
                default -> System.out.println("Wrong command format. Please try again");
            }
        } while(!"EXIT".equals(command.trim()));
        System.out.format("End session       << %s >>===============================================================\n", session);
    };
}