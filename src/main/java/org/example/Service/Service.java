package org.example.Service;

import org.example.DAO.AccountDAO;
import org.example.DAO.OperationDAO;
import org.example.Models.Account;
import org.example.Models.Client;
import org.example.DAO.ClientDAO;
import org.example.Models.Operation;
import org.example.Models.Status;

import java.sql.SQLException;
import java.util.ArrayList;


public class Service {

    private final ClientDAO clientDAO;
    private final AccountDAO accountDAO;
    private final OperationDAO operationDAO;


    public Service(){
        clientDAO = ClientDAO.getInstance();
        this.accountDAO = AccountDAO.getInstance();
        this.operationDAO = OperationDAO.getInstance();
    }


    public Client createClient(String firstName, String lastName) {
        Client client = null;

        try {
            client = clientDAO.create(new Client(0,firstName, lastName, new ArrayList<>()));
        } catch (SQLException e){
            System.out.println(e);
        }

        return client;
    }

    public Client findClient(int id) {
        Client client = null;

        try {
            client = clientDAO.read(id);
        } catch (SQLException e){
            System.out.println(e);
        }

        return client;
    }

    public boolean depositOrWithdraw(int accountId, double amount, boolean isDeposit) {
        try {
            Account account = accountDAO.read(accountId);
            if (account != null){
                operationDAO.create(new Operation(accountId,amount, isDeposit ? Status.DEPOSIT : Status.WITHDRAWAL));
                account.setBalance(isDeposit ?
                                account.getBalance() + amount :
                                account.getBalance() - amount);
                accountDAO.update(account);
                return true;
            }
            System.out.println("Aucun compte n'a été trouvé à ce numéro");
            return false;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }

    public Account getAccount(int id) {
        Account account = null;

        try {
            account = accountDAO.read(id);
        } catch (SQLException e){
            System.out.println(e);
        }

        return account;
    }

    public Account createAccount(Client client) {
        Account newAccount = null;

        try {
            newAccount = accountDAO.create(new Account(0,client,0,new ArrayList<>()));
        } catch (SQLException e){
            System.out.println(e);
        }

        return newAccount;
    }
}
