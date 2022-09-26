package com.engeto.example.FirstApi;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoItemService {

    Connection connection;

    public TodoItemService() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ukol11",
                "ukol11",
                "qay123WSX");
    }

    public List<TodoItem> getAllTodoItems() throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeQuery("SELECT * FROM item");

        ResultSet resultSet = statement.getResultSet();

        List<TodoItem> resultList = new ArrayList<>();

        while (resultSet.next()) {
            resultList.add(new TodoItem(
                    resultSet.getInt("id"),
                    resultSet.getString("message"),
                    resultSet.getBoolean("isDone")
            ));
        }
        return resultList;
    }

    public Integer insertNewItem(TodoItem newItem) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate(
                "INSERT INTO item(message, isDone) VALUES ('" + newItem.getItem() + "', " + newItem.getDone() + ")",
                1);

        return statement.getGeneratedKeys().getInt(1);
    }

    public void deleteItemById(Integer itemId) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate("DELETE FROM item WHERE id = " + itemId);
    }

    public void setItemToDone(Integer itemId) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeUpdate("UPDATE item SET isDone = true WHERE id = " + itemId);
    }

    public TodoItem getById(Integer itemId) throws SQLException {
        Statement statement = connection.createStatement();

        statement.executeQuery("SELECT * FROM item WHERE id = " + itemId);

        ResultSet resultSet = statement.getResultSet();
        if (resultSet.next()) {
            return new TodoItem(
                    resultSet.getInt("id"),
                    resultSet.getString("message"),
                    resultSet.getBoolean("isDone")
            );
        }

        return null;
    }

    public void editItem(TodoItem todoItem) throws SQLException {
        System.out.println(todoItem.getDone());
        System.out.println(todoItem.getItem());
        System.out.println(todoItem.getId());
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE item SET message = '"
                + todoItem.getItem() + "', isDone ="
                + todoItem.getDone() + " WHERE id = " + todoItem.getId());
    }
}
