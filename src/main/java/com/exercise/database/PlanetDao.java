package com.exercise.database;

import java.sql.*;

public class PlanetDao {
    public void countPlanets() {
        String sql = "SELECT COUNT(PlanetID) AS NumberOfPlanets FROM planets";
        try {
            var connection = DatabaseConnection.getConnection();
            var statement = connection.createStatement();
            var resultSet = statement.executeQuery(sql);

            // loop through the result set
            while (resultSet.next()) {
                System.out.println(
                        "Planets: " +
                                resultSet.getString("NumberOfPlanets")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectAllPlanets() {
        String sql = "SELECT * FROM planets";
        try (var connection = DatabaseConnection.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("PlanetID") + "\t" +
                        resultSet.getString("Name") + "\t" +
                        resultSet.getDouble("Mass") + "\t" +
                        resultSet.getDouble("Radius"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public void planetInsert(String name, double mass, int radius) {
        String sql = "INSERT INTO planets (Name, Mass, Radius) VALUES(?,?,?)";

        try {
            var connection = DatabaseConnection.getConnection();
            var preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, mass);
            preparedStatement.setInt(3, radius);
            preparedStatement.executeUpdate();
            System.out.println("Added a planet");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String name) {
        String sql = "DELETE FROM planets WHERE Name = ?";

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Planet with name " + name + " was deleted.");
            } else {
                System.out.println("No planet found with name " + name + ".");
            }
        } catch (SQLException e) {
            System.out.println("Deletion failed: " + e.getMessage());
        }
    }

    public void updatePlanet(int planetId, String newName, double newMass, double newRadius) {
        String sql = "UPDATE planets SET Name = ?, Mass = ?, Radius = ? WHERE PlanetID = ?";

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, newName);
            preparedStatement.setDouble(2, newMass);
            preparedStatement.setDouble(3, newRadius);
            preparedStatement.setInt(4, planetId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Planet updated successfully.");
            } else {
                System.out.println("No planet found with ID " + planetId + ".");
            }
        } catch (SQLException e) {
            System.out.println("Update failed: " + e.getMessage());
        }
    }

    public void find(int planetId) {
        String sql = "SELECT * FROM planets WHERE PlanetID = ?";
        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, planetId);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("Planet Found: ");
                System.out.println("ID: " + resultSet.getInt("PlanetID") +
                        ", Name: " + resultSet.getString("Name") +
                        ", Mass: " + resultSet.getDouble("Mass") +
                        ", Radius: " + resultSet.getDouble("Radius"));
            } else System.out.println("No planet found with ID " + planetId);

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }
    public void listPlanetsWithMoonCount() {
        String sql = "SELECT p.Name, COUNT(m.MoonID) as MoonCount " +
                "FROM planets p LEFT JOIN moons m ON p.PlanetID = m.PlanetID " +
                "GROUP BY p.PlanetID";

        try (var connection = DatabaseConnection.getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println("Planet: " + resultSet.getString("Name") +
                        ", Number of Moons: " + resultSet.getInt("MoonCount"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public void listMoonsOfPlanet(String planetName) {
        String sql = "SELECT m.Name FROM moons m " +
                "JOIN planets p ON m.PlanetID = p.PlanetID " +
                "WHERE p.Name = ?";

        try (var connection = DatabaseConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, planetName);
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("Moons of Planet " + planetName + ":");
            while (rs.next()) {
                System.out.println("- " + rs.getString("Name"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }
}

