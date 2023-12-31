package org.example.DAO;

import org.example.Entity.EventLocation;
import org.example.Exception.CustomFormatException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventLocationDAO extends BaseDAO<EventLocation> {
    public EventLocationDAO() throws SQLException {
        super();
        this.tableName = "event_location";
    }

//    @Override
//    public EventLocation createObjectFromResultSet(ResultSet resultSet) throws SQLException {
//        try {
//            return new EventLocation(
//                    resultSet.getInt("id"),
//                    resultSet.getString("name"),
//                    resultSet.getString("address"),
//                    resultSet.getInt("capacity")
//            );
//        } catch (CustomFormatException e){
//            System.out.println(e);
//            return null;
//        }
//    }

    @Override
    protected void preparedStatementWithObject(PreparedStatement preparedStatement, EventLocation eventLocation) throws SQLException {
        preparedStatement.setString(1, eventLocation.getName());
        preparedStatement.setString(2, eventLocation.getAddress());
        preparedStatement.setInt(3, eventLocation.getCapacity());
    }

    @Override
    public List<EventLocation> read() throws SQLException {
        query = "SELECT * FROM event_location";
        statement = connection.createStatement();

        resultSet = statement.executeQuery(query);

        List<EventLocation> eventLocations = new ArrayList<>();
        while (resultSet.next()) {
            eventLocations.add(DAO_Utils.createEventLocationFromResultSet(resultSet));
        }
        statement.close();

        return eventLocations;
    }

    @Override
    public EventLocation read(int id) throws SQLException {
        query = "SELECT * " +
                "FROM event_location " +
                "WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        resultSet = preparedStatement.executeQuery();

        EventLocation eventLocation = null;
        while (resultSet.next()) {
            eventLocation = DAO_Utils.createEventLocationFromResultSet(resultSet);
        }
        preparedStatement.close();

        return eventLocation;
    }

    @Override
    public EventLocation create(EventLocation eventLocation) throws SQLException {
        query = "INSERT INTO event_location (name, address, capacity) " +
                "VALUES (?, ?, ?)";
        preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatementWithObject(preparedStatement, eventLocation);

        int nbRows = preparedStatement.executeUpdate();

        if (nbRows == 0) {
            preparedStatement.close();
            return null;
        }
        resultSet = preparedStatement.getGeneratedKeys();
        while (resultSet.next()) {
            eventLocation.setId(resultSet.getInt(1));
        }

        preparedStatement.close();
        return eventLocation;
    }

    @Override
    public boolean update(EventLocation eventLocation) throws SQLException {
        query = "UPDATE event_location " +
                "SET " +
                "  name = ? ," +
                "  address = ? ," +
                "  capacity = ? " +
                "WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatementWithObject(preparedStatement, eventLocation);
        preparedStatement.setInt(4,eventLocation.getId());

        int nbRows = preparedStatement.executeUpdate();

        preparedStatement.close();
        return nbRows != 0;
    }
}
