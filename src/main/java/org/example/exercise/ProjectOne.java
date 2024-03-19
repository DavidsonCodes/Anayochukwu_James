package org.example.exercise;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectOne implements Developers {
    private final String url;
    private final String username;
    private final String password;

    public ProjectOne(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

//    @Override
    public ResultSet getResultSet() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             BufferedReader reader = new BufferedReader(new FileReader("/Users/decagon/Desktop/Anayochukwu_James/Project1/src/main/java/org/example/exercise/project.txt"))) {

            // Create a table called developers
            String createTableQuery = "CREATE TABLE IF NOT EXISTS developers (name VARCHAR(255), age INT, location VARCHAR(255), skill VARCHAR(255))";
            try (PreparedStatement createTableStmt = connection.prepareStatement(createTableQuery)) {
                createTableStmt.executeUpdate();
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String insertQuery = "INSERT INTO developers (name, age, location, skill) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, data[0].trim());
                    insertStmt.setInt(2, Integer.parseInt(data[1].trim()));
                    insertStmt.setString(3, data[2].trim());
                    insertStmt.setString(4, data[3].trim());
                    insertStmt.executeUpdate();
                }
            }

            // Query to fetch the loaded contents
            String selectQuery = "SELECT * FROM developers";
            try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery)) {
                return selectStmt.executeQuery();
            }
        } catch (IOException | SQLException e) {
            throw new SQLException("Error occurred while processing database operations", e);
        }
    }

    public static void main(String[] args) {
        // Example usage
        String url = "jdbc:postgresql://localhost:5432/developer";
        String username = "postgres";
        String password = "Anayojames";

        ProjectOne projectOne = new ProjectOne(url, username, password);
        try {
            ResultSet rs = projectOne.getResultSet();
            while (rs.next()) {
                // Process each row of the result set
                System.out.println(rs.getString("name") + ", " + rs.getInt("age") + ", " + rs.getString("location") + ", " + rs.getString("skill"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
