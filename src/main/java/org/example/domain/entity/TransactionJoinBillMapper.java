package org.example.domain.entity;

public class TransactionJoinBillMapper {
    public static TransactionJoinBillTemplate from(TransactionEntity tran, BillEntity bill) {
        TransactionJoinBillTemplate template = new TransactionJoinBillTemplate();
        template.setId(tran.getId());
        template.setClientId(tran.getClientId());
        template.setBillId(tran.getBillId());
        template.setAmount(tran.getAmount());
        template.setCreatedAt(tran.getCreatedAt());

        template.setProvider(bill.getProvider());
        template.setPaid((bill.getIsPaid()));
        template.setServiceType(bill.getServiceType());
        template.setDueDate(bill.getDueDate());
        return template;
    }
}
