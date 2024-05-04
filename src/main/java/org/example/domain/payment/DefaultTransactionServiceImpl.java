package org.example.domain.payment;

import org.example.domain.bill.BillDAO;
import org.example.domain.bill.BillEntity;
import org.example.domain.client.ClientDAO;
import org.example.domain.client.ClientEntity;
import org.example.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class DefaultTransactionServiceImpl implements TransactionService{
    private final ClientDAO clientDAO;
    private final BillDAO billDAO;
    private final TransactionDAO transactionDAO;

    public DefaultTransactionServiceImpl(ClientDAO clientDAO, BillDAO billDAO, TransactionDAO transactionDAO) {
        this.clientDAO = clientDAO;
        this.billDAO = billDAO;
        this.transactionDAO = transactionDAO;
    }
    //transactional
    @Override
    public void payBillWithBillId(long clientId, long billId) throws BusinessException {
        BillEntity bill = billDAO.findByID(billId);
        if(bill == null) {
            throw new BusinessException("Bill with ID: " + billId + " does not exist");
        } else if(bill.isPaid()){
            throw new BusinessException("Bill is already paid!");
        }
        ClientEntity client = clientDAO.findByID(clientId);
        if(client.getFund().compareTo(bill.getAmount()) < 0) {
            throw new BusinessException("Current fund is not enough for payment. Please add more fund");
        }
        client.setFund(client.getFund().subtract(bill.getAmount()));
        clientDAO.update(clientId, client);
        bill.setPaid(true);
        billDAO.update(bill.getId(), bill);
        TransactionEntity transaction = new TransactionEntity(clientId, billId, bill.getAmount());
        transactionDAO.create(transaction);
    }
    //transactional-propagation = nested => rollback to latest save point
    @Override
    public void payBillWithBillIds(long clientId, List<Long> billIds) {
        for (Long billId : billIds) {
            try {
                payBillWithBillId(clientId, billId);
            } catch (BusinessException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //transactional
    @Override
    public List<TransactionEntity> findTranByClientIdSortByPayDateDesc(long clientId) {
        List<TransactionEntity> trans = transactionDAO.findByClientIdSortByPayDateDesc(clientId);
        return transactionDAO.findByClientIdSortByPayDateDesc(clientId);
    }
    //transactional
    @Override
    public List<TransactionEntity> findTranByClientIdAndProviderSortByPayDateDesc(long clientId, String provider) {
        //join in DB is more efficient, this is demo only
        List<BillEntity> bills = billDAO.findByClientIdAndProviderSortByDueDateDesc(clientId, provider);
        List<TransactionEntity> trans = new ArrayList<>();
        for(BillEntity bill : bills) {
            Long billId = bill.getId();
            trans.add(transactionDAO.findByClientIdAndBillId(clientId, billId));
        }
        return trans;
    }
}
