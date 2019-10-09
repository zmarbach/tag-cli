package com.improving.tagcli.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class OldSchoolDatabaseClient {

    private static final Logger logger = LoggerFactory.getLogger(OldSchoolDatabaseClient.class);

    public void readRecordIntoDatabase() throws SQLException {
        // try w/resources...try the things in the code block, and use these resources to do it
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            // ^^^ do NOT need finally block to close cuz Connection implements AutoCloseable
            // so it will automatically call the close method in AutoCloseable after the try/catch
            logger.info("Connection + Statement made");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM weapon LIMIT 10");

            ResultSetMetaData metaData = resultSet.getMetaData();
            String columns = "";
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columns = columns + "(" + i + ")" + metaData.getColumnName(i) + ",";
            }
            logger.info("Table Columns: " + columns);

            resultSet.beforeFirst();//set row index to 0, then enter the loop so it starts at the beginning of the table
            while (resultSet.next()) { //keep looping until there is no next line (no more records)
                int id = resultSet.getInt(1);
                String name = resultSet.getString(3);
                String area = resultSet.getString(17);
                logger.info("ID: {}, Name: {}, Area: {}", id, name, area);
            }

            resultSet.close();

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void insertRecordIntoDatabase() throws SQLException {
        // try w/resources...try the things in the code block, and use these resources to do it
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            // ^^^ do NOT need finally block to close cuz Connection implements AutoCloseable
            // so it will automatically call the close method in AutoCloseable after the try/catch
            logger.info("Connection + Statement made");

           int rowsAffected =  statement.executeUpdate("INSERT INTO weapon (Name, Area, ItemType) VALUES ('Test Dagger', 'Test Area', 'Weapon')");
           logger.info("Rows affected: " + rowsAffected);

           if (rowsAffected > 0) { //if actually making a change (rows were actually) , then commit the change
              logger.info("Committing...");
               connection.commit();
           }

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private Connection getConnection() throws SQLException{
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tag?serverTimezone=UTC",
                "zachlocal",
                "Buggywhip22");
        connection.setAutoCommit(false);
        return connection;
        }
}
