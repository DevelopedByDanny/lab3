package com.exercise.database;

import com.exercise.model.Moon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MoonDao {

    // Create a Moon
    public void addMoon(int planetId, String name, double mass) {
        String sql = "INSERT INTO moons (PlanetID, Name, Mass) VALUES (?, ?, ?)";

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, planetId);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, mass);
            preparedStatement.executeUpdate();
            System.out.println("Moon added successfully.");
        } catch (SQLException e) {
            System.out.println("Insertion failed: " + e.getMessage());
        }
    }

    // Read All Moons
    public void listAllMoons() {
        String sql = "SELECT * FROM moons ORDER BY PlanetID";

        try (var connection = DatabaseConnection.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("MoonID") +
                        ", PlanetID: " + resultSet.getInt("PlanetID") +
                        ", Name: " + resultSet.getString("Name") +
                        ", Mass: " + resultSet.getDouble("Mass"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    // Find one moon
    public Moon findMoonByName(String moonName) {
        String sql = "SELECT * FROM moons WHERE Name = ?";

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, moonName);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return new Moon(
                        rs.getInt("MoonID"),
                        rs.getInt("PlanetID"),
                        rs.getString("Name"),
                        rs.getDouble("Mass")
                );
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return null;
    }

    // Update a Moon
    public void updateMoon(int moonId, int planetId, String name, double mass) {
        String sql = "UPDATE moons SET PlanetID = ?, Name = ?, Mass = ? WHERE MoonID = ?";

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, planetId);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, mass);
            preparedStatement.setInt(4, moonId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Moon updated successfully.");
            } else {
                System.out.println("No moon found with ID " + moonId);
            }
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    // Delete a Moon
    public void deleteMoon(String moonName) {
        String sql = "DELETE FROM moons WHERE Name = ?";

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, moonName);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Moon deleted successfully.");
            } else {
                System.out.println("No moon found with Name " + moonName);
            }
        } catch (SQLException e) {
            System.out.println("Deletion failed: " + e.getMessage());
        }
    }

}
