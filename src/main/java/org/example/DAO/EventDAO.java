package org.example.DAO;

import org.example.Entity.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDAO extends BaseDAO<Event> {

    public EventDAO() throws SQLException {
        super();
        this.tableName = "event";
    }

    @Override
    protected Event createObjectFromResultSet(ResultSet resultSet) throws SQLException {
        return new Event(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getTimestamp("date_time").toLocalDateTime(),
                resultSet.getInt("event_location_id"),
                resultSet.getDouble("price"),
                resultSet.getInt("tickets_sold_number")
        );
    }

    @Override
    protected void preparedStatementWithObject(PreparedStatement preparedStatement, Event event) throws SQLException{
        preparedStatement.setString(1, event.getName());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(event.getDateTime()));
        preparedStatement.setInt(3,event.getEventLocationId());
        preparedStatement.setDouble(4, event.getPrice());
        preparedStatement.setInt(5, event.getTicketsSoldNumber());
    }

    @Override
    public List<Event> read() throws SQLException {
        query = "SELECT * FROM event";
        statement = connection.createStatement();

        resultSet = statement.executeQuery(query);

        List<Event> events = new ArrayList<>();
        while (resultSet.next()){
            events.add(createObjectFromResultSet(resultSet));
        }
        statement.close();

        return events;
    }

    @Override
    public Event read(int id) throws SQLException {
        query = "SELECT * " +
                "FROM event " +
                "WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);

        resultSet = preparedStatement.executeQuery();

        Event event = null;
        while (resultSet.next()){
            event = createObjectFromResultSet(resultSet);
        }
        statement.close();

        return event;
    }

    @Override
    public Event create(Event event) throws SQLException {
        query = "INSERT INTO event (name, date_time, event_location_id, price, tickets_sold_number) " +
                "VALUES (?, ? , ? , ? , ? )";
        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatementWithObject(preparedStatement,event);

        int nbRows = preparedStatement.executeUpdate();

        if (nbRows == 0){
            preparedStatement.close();
            return null;
        }
        resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()){
            event.setId(resultSet.getInt(1));
        }

        preparedStatement.close();
        return event;
    }

    @Override
    public boolean update(Event event) throws SQLException {
        query = "UPDATE event " +
                "SET " +
                "  name = ? " +
                "  date_time = ? " +
                "  event_location_id = ? " +
                "  price = ? " +
                "  tickets_sold_number = ? " +
                "WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatementWithObject(preparedStatement, event);

        int nbRows = preparedStatement.executeUpdate();

        preparedStatement.close();
        return nbRows != 0;
    }
}
