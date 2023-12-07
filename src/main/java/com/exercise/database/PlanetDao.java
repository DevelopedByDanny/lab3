package com.exercise.database;

import javax.naming.Name;
import java.sql.*;

public class PlanetDao {
    public void selectAllPlanets() {
        String sql = "SELECT * FROM planets";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("PlanetID") + "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getDouble("Mass") + "\t" +
                        rs.getDouble("Radius"));
            }
        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    private static void planetInsert(String name, int mass, int radius) {
        String sql = "INSERT INTO planets (Name, Mass, Radius) VALUES(?,?,?)";

        try {
            Connection conn = DatabaseConnection.getConnection();
            var preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, mass);
            preparedStatement.setInt(3, radius);
            preparedStatement.executeUpdate();
            System.out.println("Added a planet");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
