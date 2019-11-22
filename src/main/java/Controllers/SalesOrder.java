package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalesOrder {
    public static void listSO() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT SalesNo, Date, UserID, CustomerID FROM SalesOrder");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SalesNo = results.getInt(1);
                String Date = results.getString(2);
                int UserID = results.getInt(3);
                int CustomerID = results.getInt(4);
                System.out.println(SalesNo + " " + Date + " " + UserID + " " + CustomerID);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertSO(int SalesNo, String Date, int UserID, int CustomerID){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO SalesOrder (SalesNo, Date, UserID, CustomerID) VALUES (?, ?, ?, ?)");
            ps.setInt(1, SalesNo);
            ps.setString(2, Date);
            ps.setInt(3, UserID);
            ps.setInt(4, CustomerID);
            ps.executeUpdate();
            System.out.println("Record added to Sales Order table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something has gone wrong.");
        }
    }
    public static void updateSO (String Date){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE SlaesOrder SET Date = ? WHERE SalesNo = ?");
            ps.setString(1, Date);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deleteSO (int SalesNo){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM SalesOrder WHERE SalesNo= ?");
            ps.setInt(1, SalesNo);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
