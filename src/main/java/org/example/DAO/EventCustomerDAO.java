package org.example.DAO;

import org.example.Entity.EventCustomer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventCustomerDAO extends BaseDAO<EventCustomer> {

    public EventCustomerDAO() throws SQLException {
        super();
        this.tableName = "customer_event";
    }

    @Override
    public EventCustomer createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return new EventCustomer(
                resultSet.getInt("customer_id"),
                resultSet.getInt("event_id")
        );
    }

    @Override
    protected void preparedStatementWithObject(PreparedStatement preparedStatement, EventCustomer eventCustomer) throws SQLException {
        preparedStatement.setInt(1, eventCustomer.getCustomerId());
        preparedStatement.setInt(2, eventCustomer.getEventId());
    }

    @Override
    public List<EventCustomer> read() throws SQLException {
        query = String.format("SELECT * FROM %s", tableName);
        statement = connection.createStatement();

        resultSet = statement.executeQuery(query);

        List<EventCustomer> eventCustomers = new ArrayList<>();
        while (resultSet.next()) {
            eventCustomers.add(createObjectFromResultSet(resultSet));
        }

        statement.close();
        return eventCustomers;
    }

    @Override
    public EventCustomer read(int id) throws SQLException {
        query = String.format(
                        "SELECT * " +
                        "FROM %s " +
                        "WHERE id = ? ", tableName);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();

        EventCustomer eventCustomer = null;
        while (resultSet.next()) {
            eventCustomer = createObjectFromResultSet(resultSet);
        }

        preparedStatement.close();
        return eventCustomer;
    }

    @Override
    public EventCustomer create(EventCustomer eventCustomer) throws SQLException {
        query = String.format(
                        "INSERT INTO %s " +
                        "VALUES (? , ?) ", tableName);
        preparedStatement = connection.prepareStatement(query);
        preparedStatementWithObject(preparedStatement, eventCustomer);

        int nbRows = preparedStatement.executeUpdate();

        preparedStatement.close();
        if (nbRows == 0) {
            return null;
        }
        return eventCustomer;
    }

    @Override
    public boolean update(EventCustomer eventCustomer) throws SQLException {
        query = String.format(
                        "UPDATE %s " +
                        "SET " +
                        " customer_id = ? " +
                        " event_id = ? " +
                        "WHERE id =  "
                , tableName);

        preparedStatement = connection.prepareStatement(query);
        preparedStatementWithObject(preparedStatement,eventCustomer);

        int nbRows = preparedStatement.executeUpdate();

        preparedStatement.close();
        return nbRows != 0;
    }
}
