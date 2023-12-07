package com.exercise;

import com.exercise.database.PlanetDao;
import com.exercise.util.InputReader;

import java.sql.*;
import java.util.Scanner;

public class App {
//    private static Scanner scanner = new Scanner(System.in);
    private static final PlanetDao planetDao = new PlanetDao();
//        private static Connection connect() {
//            // SQLite connection string
//            String url = "jdbc:sqlite:/home/perfectpoodle/Documents/SQL/labb3/planets.db";
//            Connection conn = null;
//            try {
//                conn = DriverManager.getConnection(url);
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//            return conn;
//        }

    private static void printActions() {
        System.out.println("\nVälj:\n");
        System.out.println("0  - Stäng av\n" +
                "1  - Visa alla planeter\n" +
                "2  - Lägga till en ny skiva\n" +
                "3  - Sök på en skiva\n" +
                "4  - Statistik\n" +
                "5  - Visa en lista över alla val.");
    }

//        private static void planetsSelectAll(){
//            String sql = "SELECT * FROM planets";
//
//            try {
//                Connection conn = connect();
//                if (conn == null) System.out.println("conn is null");
//                Statement stmt  = conn.createStatement();
//                ResultSet rs    = stmt.executeQuery(sql);
//
//                // loop through the result set
//                while (rs.next()) {
//                    System.out.println(
//                            rs.getInt("PlanetID") +  "\t" +
//                                    rs.getString("Name") + "\t" +
//                                    rs.getInt("Mass") + "\t" +
//                                    rs.getString("Radius")
//                    );
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//                System.out.println(e.getMessage());
//            }
//        }

//        private static void albumSearchAll(){
//            System.out.println("Sök på en skiva: ");
//            String inputArtist = scanner.nextLine();
//
//            String sql = "SELECT * FROM album WHERE albumArtist = ?";
//
//            try {
//                Connection conn = connect();
//                PreparedStatement pstmt  = conn.prepareStatement(sql);
//
//                pstmt.setString(1,inputArtist);
//
//                ResultSet rs  = pstmt.executeQuery();
//
//                // loop through the result set
//                while (rs.next()) {
//                    System.out.println(
//                            rs.getInt("albumId") +  "\t" +
//                                    rs.getString("albumArtist") + "\t" +
//                                    rs.getString("albumTitle") + "\t" +
//                                    rs.getString("albumYear") + "\t" +
//                                    rs.getString("albumRating")
//                    );
//                }
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }

//        private static void albumCount(){
//            String sql = "SELECT COUNT(albumId) AS albumCount FROM album";
//
//            try {
//                Connection conn = connect();
//                Statement stmt  = conn.createStatement();
//                ResultSet rs    = stmt.executeQuery(sql);
//
//                // loop through the result set
//                while (rs.next()) {
//                    System.out.println(
//                            "Antal skivor: " +
//                                    rs.getString("albumCount")
//                    );
//                }
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }

//        private static void albumInput(){
//            System.out.println("Skriv in albumets artist: ");
//            String inputArtist = scanner.nextLine();
//            System.out.println("Skriv in albumets titel: ");
//            String inputTitle = scanner.nextLine();
//            System.out.println("Skriv in albumets betyg: ");
//            int inputRating = scanner.nextInt();
//            scanner.nextLine();
//
//            albumInsert(inputArtist,inputTitle,inputRating);
//        }

//        private static void albumInsert(String albumArtist, String albumTitle, int albumRating) {
//            String sql = "INSERT INTO album (albumArtist, albumTitle, albumRating) VALUES(?,?,?)";
//
//            try{
//                Connection conn = connect();
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                pstmt.setString(1, albumArtist);
//                pstmt.setString(2, albumTitle);
//                pstmt.setInt(3, albumRating);
//                pstmt.executeUpdate();
//                System.out.println("Du har lagt till en ny skiva");
//            } catch (SQLException e) {
//                System.out.println(e.getMessage());
//            }
//        }

    public static void main(String[] args) {

        boolean quit = false;
        while (!quit) {
            printActions();
//                int action = scanner.nextInt();
//                scanner.nextLine();

            var action = InputReader.inputInt("Enter a number: ");
            switch (action) {
                case 0 -> {
                    System.out.println("\nStänger ner...");
                    quit = true;
                }
                case 1 -> planetDao.selectAllPlanets();
                case 2 -> {
                }
//                        albumInput();
                //insert("Sagan om ringen", "Tolkien, J.R.R", 120);
                case 3 -> {
                }
                //update("Bilbo", "Tolkien, J.R.R", 100, 1);
//                        albumSearchAll();
                case 4 -> {
                }
//                        albumCount();
                //delete(1);
                //deleteBook();
                case 5 -> printActions();
            }
        }

    }
}
