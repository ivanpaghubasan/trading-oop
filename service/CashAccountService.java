package service;

import java.math.BigDecimal;

import pojo.CashAccount;
import pojo.TradeAccount;
import repository.TradeAccountRepository;

public class CashAccountService implements TradeAccountService {

    private TradeAccountRepository repository;

    public CashAccountService(TradeAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deposit(String id, BigDecimal amount) {
        CashAccount account = retrieveTradeAccount(id);
        account.setCashBalance(account.getCashBalance().add(amount));
        updateTradeAccount(account);
    }

    @Override
    public void withdraw(String id, BigDecimal amount) {
        CashAccount account = retrieveTradeAccount(id);
        if (account.getCashBalance().compareTo(amount) < 0) {
            System.out.println("Current cash balance is less than the amout you want to withdraw. Please try again.");
        }
        account.setCashBalance(account.getCashBalance().subtract(amount));
        updateTradeAccount(account);
    }

    public void createTradeAccount(TradeAccount account) {
        this.repository.createTradeAccount(account);
    }

    public CashAccount retrieveTradeAccount(String id) {
        return (CashAccount) this.repository.retrieveTradeAccount(id);
    }

    public void updateTradeAccount(TradeAccount account) {
        this.repository.updateTradeAccount(account);
    }

    public void deleteTradeAccount(String id) {
        this.repository.deleteTradeAccount(id);
    }

}
