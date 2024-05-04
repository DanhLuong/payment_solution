package org.example;

import org.example.domain.bill.*;
import org.example.domain.client.ClientDAO;
import org.example.domain.client.ClientService;
import org.example.domain.client.DefaultClientEntityDAOImpl;
import org.example.domain.client.DefaultClientServiceImpl;
import org.example.domain.payment.DefaultTransactionEntityDAOImpl;
import org.example.domain.payment.DefaultTransactionServiceImpl;
import org.example.domain.payment.TransactionDAO;
import org.example.domain.payment.TransactionService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
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
                        serve(id, session, sc, clientService, billService, transactionService);
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

    private static void serve(long clientId, UUID session, Scanner sc, ClientService clientService, BillService billService, TransactionService transactionService) {
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
                    if(commandArg.size()>1) {
                        System.out.println("Wrong command format. Please try again");
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
                    List<BillEntity> bills = billService.findBillByClientIdSortDueDateDesc(clientId);

                }
                case "PAY" -> {
                    System.out.println("PAY");
                }
                case "DUE_DATE" -> {
                    System.out.println("DUE_DATE");
                }
                case "SCHEDULE" -> {
                    System.out.println("SCHEDULE");
                }
                case "LIST_PAYMENT" -> {
                    System.out.println("LIST_PAYMENT");
                }
                case "SEARCH_BILL_BY_PROVIDER" -> {
                    System.out.println("SEARCH_BILL_BY_PROVIDER");
                }
                case "EXIT" -> {}
                default -> System.out.println("Wrong command format. Please try again");
            }
        } while(!"EXIT".equals(command.trim()));
        System.out.format("End session       << %s >>===============================================================\n", session);
    };
}