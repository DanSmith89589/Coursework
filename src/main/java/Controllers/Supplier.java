package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Supplier {
    public static void listSuppliers() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT SupplierID, SupplierName, Location, StockID FROM Supplier");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SupplierID = results.getInt(1);
                String SupplierName = results.getString(2);
                String Location = results.getString(3);
                int StockID = results.getInt(4);
                System.out.println(SupplierID + " " + SupplierName + " " + Location + " " + StockID);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertSupplier(int SupplierID, String SupplierName, String Location, int StockID){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Supplier (SupplierID, SupplierName, Location, StockID) VALUES (?, ?, ?)");
            ps.setInt(1, SupplierID);
            ps.setString(2, SupplierName);
            ps.setString(3, Location);
            ps.setInt(4, StockID);
            ps.executeUpdate();
            System.out.println("Supplier added to Supplier table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something has gone wrong.");
        }
    }
    public static void updateSuppliers (int StockID, int SupplierID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Supplier SET StockID WHERE SupplierID = ?");
            ps.setInt(1, StockID);
            ps.setInt(2, SupplierID);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deleteSupplier (int SupplierID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Supplier WHERE SupplierID = ?");
            ps.setInt(1, SupplierID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
