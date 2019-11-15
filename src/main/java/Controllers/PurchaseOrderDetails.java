package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PurchaseOrderDetails {
    public static void POD() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT PurchaseID, Quantity, StockID FROM Controllers.PurchaseOrderDetails");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int PurchaseID = results.getInt(1);
                int Quantity = results.getInt(2);
                int StockID = results.getInt(3);
                System.out.println(PurchaseID + " " + Quantity + " " + StockID);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertPOD(int PurchaseID, int Quantity, int StockID){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Controllers.PurchaseOrderDetails (PurchaseID, Quantity, StockID) VALUES (?, ?, ?)");
            ps.setInt(1, PurchaseID);
            ps.setInt(2, Quantity);
            ps.setInt(3, StockID);
            ps.executeUpdate();
            System.out.println("Record added to Purchase Order Details table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something as gone wrong.");
        }
    }
    public static void updatePOD (int Quantity){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Controllers.PurchaseOrderDetails SET Quantity = ? WHERE PurchaseID = ?");
            ps.setInt(1, Quantity);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deletePOD (int PurchaseID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM PurchaseOrderdDetails WHERE PurchaseID = ?");
            ps.setInt(1, PurchaseID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
