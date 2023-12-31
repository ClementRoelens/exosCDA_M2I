package org.example.DAO;

import org.example.Entity.Customer;
import org.example.Entity.Event;
import org.example.Entity.EventLocation;
import org.example.Exception.CustomFormatException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Utils {

    public static Event createEventFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            return new Event(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getTimestamp("date_time").toLocalDateTime(),
                    resultSet.getInt("event_location_id"),
                    resultSet.getDouble("price"),
                    resultSet.getInt("tickets_sold_number")
            );
        } catch (CustomFormatException e){
            System.out.println(e);
            return null;
        }
    }

    public static Customer createCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Customer(
                resultSet.getInt("id"),
                resultSet.getString("firstname"),
                resultSet.getString("lastname"),
                resultSet.getString("email")
        );
    }

    public static EventLocation createEventLocationFromResultSet(ResultSet resultSet) throws SQLException {
        try {
            return new EventLocation(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("address"),
                    resultSet.getInt("capacity")
            );
        } catch (CustomFormatException e){
            System.out.println(e);
            return null;
        }
    }

}
