package Controllers;

import Server.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Users {
    public static void listUsers() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, StoreNo FROM Controllers.Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int UserID = results.getInt(1);
                int StoreNo = results.getInt(2);
                System.out.println(UserID + " " + StoreNo);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertUsers(int UserID, int StoreNo){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Users (UserID, StoreNo) VALUES (?, ?, ?)");
            ps.setInt(1, UserID);
            ps.setInt(2, StoreNo);
            ps.executeUpdate();
            System.out.println("Record added to Users table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something as gone wrong.");
        }
    }
    public static void updateUser (int UserID, int StoreNo){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Controllers.Users SET StoreNo = ? WHERE UserID = ?");
            ps.setInt(1, UserID);
            ps.setInt(2, StoreNo);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deleteWeight (int UserID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Users WHERE UserID = ?");
            ps.setInt(1, UserID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }


}
