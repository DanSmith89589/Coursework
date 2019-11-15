package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Customers {
    public static void listCustomers() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT CustomerID, CustomerName FROM Customers");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int CustomerID = results.getInt(1);
                String CustomerName = results.getString(2);
                System.out.println(CustomerID + " " + CustomerName);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertCustomer(String CustomerName){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Customers (CustomerName) VALUES (?)");

            ps.setString(1, CustomerName);
            ps.executeUpdate();
            System.out.println("Record added to Customers table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something as gone wrong.");
        }
    }
    public static void updateUser (int CustomerID, String CustomerName){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Controllers.Customer SET CustomerName = ? WHERE CustomerID = ?");
            ps.setInt(1, CustomerID);
            ps.setString(2, CustomerName);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deleteCustomer (int CustomerID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Customer WHERE CustomerID = ?");
            ps.setInt(1, CustomerID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
