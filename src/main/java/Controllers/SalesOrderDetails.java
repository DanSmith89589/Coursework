package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalesOrderDetails {
    public static void listSOD() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT SalesNo, Quantity, StockID FROM Controllers.SalesOrderDetails");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SalesNo = results.getInt(1);
                String Quantity = results.getString(2);
                int StockID = results.getInt(3);
                System.out.println(SalesNo + " " + Quantity + " " + StockID);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertSOD(int SalesNo, int Quantity, int StockID){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO SalesOrederDetails (SalesNo, Quantity, StockID) VALUES (?, ?, ?)");
            ps.setInt(1, SalesNo);
            ps.setInt(2, Quantity);
            ps.setInt(3, StockID);
            ps.executeUpdate();
            System.out.println("Record added to SalesOrderDetails table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something has gone wrong.");
        }
    }
    public static void updateSOD (int SalesNo, int Quantity, int StockID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Controllers.SalesOrderDetails SET Quantity = ? WHERE SalesNo = ?");
            ps.setInt(1, Quantity);
            ps.setInt(2, SalesNo);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deleteSOD (int SalesNo){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM SalesOrderDetails WHERE SalesNo = ?");
            ps.setInt(1, SalesNo);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
