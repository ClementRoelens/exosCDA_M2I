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
import java.util.List;


public class Service {

    private final ClientDAO clientDAO;
    private final AccountDAO accountDAO;
    private final OperationDAO operationDAO;


    public Service(){
        clientDAO = ClientDAO.getInstance();
        accountDAO = AccountDAO.getInstance();
        operationDAO = OperationDAO.getInstance();
    }


    public Client createClient(String firstName, String lastName) {
        Client client = null;

        try {
            client = clientDAO.create(new Client(firstName, lastName));
            client.addAccount(createAccount(client.getId()));
        } catch (SQLException e){
            System.out.println(e);
        }

        return client;
    }

    public Account createAccount(int clientId) {
        Account newAccount = null;

        try {
            newAccount = accountDAO.create(new Account(clientId));
        } catch (SQLException e){
            System.out.println(e);
        }

        return newAccount;
    }

    public Client getClient(int id) {
        Client client = null;

        try {
            client = clientDAO.read(id);
            client.setAccounts(accountDAO.readFromClient(client));
            for (Account account : client.getAccounts()){
                account.setOperations(operationDAO.readFromAccount(account));
            }
        } catch (SQLException e){
            System.out.println(e);
        }

        return client;
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

    public boolean depositOrWithdraw(int accountId, double amount, boolean isDeposit) {
        try {
            Account account = accountDAO.read(accountId);
            if (account != null){
                operationDAO.create(new Operation(amount, isDeposit ? Status.DEPOSIT : Status.WITHDRAWAL, accountId));
                account.setBalance(isDeposit ?
                                account.getBalance() + amount :
                                account.getBalance() - amount);
                return  accountDAO.update(account);
            }
            System.out.println("Aucun compte n'a été trouvé à ce numéro");
            return false;
        } catch (SQLException e){
            System.out.println(e);
            return false;
        }
    }



}
