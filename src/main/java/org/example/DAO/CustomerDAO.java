package org.example.DAO;

import org.example.Entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO extends BaseDAO<Customer> {

    public CustomerDAO() throws SQLException {
        super();
        tableName = "customer";
    }
//    @Override
//    public Customer createObjectFromResultSet(ResultSet resultSet) throws SQLException {
//        return new Customer(
//                resultSet.getInt("id"),
//                resultSet.getString("firstname"),
//                resultSet.getString("lastname"),
//                resultSet.getString("email")
//        );
//    }

    @Override
    protected void preparedStatementWithObject(PreparedStatement preparedStatement, Customer customer) throws SQLException {
        preparedStatement.setString(1, customer.getFirstname());
        preparedStatement.setString(2, customer.getLastname());
        preparedStatement.setString(3, customer.getEmail());
    }


    @Override
    public Customer create(Customer customer) throws SQLException {
        query = String.format(
                "INSERT INTO %s (firstname, lastname, email) " +
                        "VALUES (?, ?, ?)"
                , tableName);
        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatementWithObject(preparedStatement, customer);

        int nbRows = preparedStatement.executeUpdate();

        if (nbRows == 0) {
            preparedStatement.close();
            return null;
        }

        resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()) {
            customer.setId(resultSet.getInt(1));
        }

        preparedStatement.close();
        return customer;
    }

    @Override
    public List<Customer> read() throws SQLException {
        query = String.format("SELECT * FROM %s", tableName);
        statement = connection.createStatement();

        resultSet = statement.executeQuery(query);

        List<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            try {
                customers.add(DAO_Utils.createCustomerFromResultSet(resultSet));
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        statement.close();
        return customers;
    }

    @Override
    public Customer read(int id) throws SQLException {
        query = String.format(
                "SELECT * " +
                        "FROM %s " +
                        "WHERE id = ?", tableName);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();

        Customer customer = null;
        while (resultSet.next()) {
            try {
                customer = DAO_Utils.createCustomerFromResultSet(resultSet);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        preparedStatement.close();
        return customer;
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        query = String.format(
                        "UPDATE %s " +
                        "SET " +
                        " firstname = ? " +
                        " lastname = ? " +
                        " email = ? " +
                        " WHERE id = ? ");
        preparedStatement = connection.prepareStatement(query);
        preparedStatementWithObject(preparedStatement, customer);
        preparedStatement.setInt(4, customer.getId());

        int nbRows = preparedStatement.executeUpdate();

        preparedStatement.close();
        return nbRows != 0;
    }
}
