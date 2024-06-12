package service;

import java.math.BigDecimal;

import pojo.CashAccount;
import pojo.MarginAccount;
import pojo.TradeAccount;
import repository.TradeAccountRepository;

public class MarginAccountService implements TradeAccountService {
    private TradeAccountRepository repository;

    public MarginAccountService(TradeAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public void deposit(String id, BigDecimal amount) {
        MarginAccount account = retrieveTradeAccount(id);
        account.setMargin(account.getMargin().add(amount));
        updateTradeAccount(account);
    }

    @Override
    public void withdraw(String id, BigDecimal amount) {
        MarginAccount account = retrieveTradeAccount(id);
        if (account.getMargin().compareTo(amount) < 0) {
            System.out.println("Current margin is less than the amount you want to withdraw. Please try again.");
        }
        account.setMargin(account.getMargin().subtract(amount));
        updateTradeAccount(account);
    }

    public void createTradeAccount(TradeAccount account) {
        this.repository.createTradeAccount(account);
    }

    public MarginAccount retrieveTradeAccount(String id) {
        return (MarginAccount)this.repository.retrieveTradeAccount(id);
    }

    public void updateTradeAccount(TradeAccount account) {
        this.repository.updateTradeAccount(account);
    }

    public void deleteTradeAccount(String id) {
        this.repository.deleteTradeAccount(id);
    }

}