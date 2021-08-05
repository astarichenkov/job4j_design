package ru.job4j.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        try {
            properties.load(new FileInputStream("app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void createTable(String tableName) {
        String queryStr = String.format(
                "create table if not exists %s(%s);",
                tableName,
                "id serial primary key"
        );
        executeQuery(queryStr);
    }

    public void dropTable(String tableName) {
        String queryStr = String.format(
                "drop table if exists %s;",
                tableName
        );
        executeQuery(queryStr);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String queryStr = String.format(
                "alter table %s add column %s %s",
                tableName, columnName, type
        );
        executeQuery(queryStr);
    }

    public void dropColumn(String tableName, String columnName) {
        String queryStr = String.format(
                "alter table %s drop column %s",
                tableName, columnName
        );
        executeQuery(queryStr);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String queryStr = String.format(
                "alter table %s rename column %s to %s",
                tableName, columnName, newColumnName
        );
        executeQuery(queryStr);
    }

    private void executeQuery(String queryStr) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(queryStr);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}