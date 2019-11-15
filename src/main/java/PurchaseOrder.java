import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PurchaseOrder {
    public static void listPO() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT PurchaseID, Date, UserID, SupplierID FROM PurchaseOrder");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int PurchaseID = results.getInt(1);
                String Date = results.getString(2);
                int UserID = results.getInt(3);
                int SupplierID = results.getInt(4);
                System.out.println(PurchaseID + " " + Date + " " + UserID + " " + SupplierID);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertPO(int PurchaseID, String Date, int UserID, int SupplierID){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO PurchaseOrder (PurchaseID, Date, UserID, SupplierID) VALUES (?, ?, ?, ?)");
            ps.setInt(1, PurchaseID );
            ps.setString(2, Date);
            ps.setInt(3, UserID);
            ps.setInt(4, SupplierID);
            ps.executeUpdate();
            System.out.println("Record added to Purchase Order table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something as gone wrong.");
        }
    }
    public static void updatePO (int SupplierID, int UserID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE PurchaseOrder SET SupplierID = ? WHERE PurchaseID = ?");
            ps.setInt(1, SupplierID);
            ps.setInt(2, UserID);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deletePO (String Date){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM PurchaseOrder WHERE Date = ?");
            ps.setString(1, Date);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
