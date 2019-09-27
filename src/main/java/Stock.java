import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Stock {
    public static void listStock() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT SotckID, Brand, StockName, Quantity, Exclusive FROM Stock");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int StockID = results.getInt(1);
                String Brand = results.getString(2);
                String StockName = results.getString(3);
                int Quantity = results.getInt(4);
                boolean Exclusive = results.getBoolean(5);

                System.out.println(StockID + " " + Brand + " " + StockName + "" + Quantity +  "" + Exclusive);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertStock(int StockID, String Brand, String StockName, int Quantity, boolean Exclusive){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Stock (StockID, Brand, StockName, Quantity, Exclusive) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, StockID);
            ps.setString(2, Brand);
            ps.setString(3, StockName);
            ps.setInt(4, Quantity);
            ps.setBoolean(5, Exclusive);
            ps.executeUpdate();
            System.out.println("Record added to Stock table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something as gone wrong. Please contact the administrator with the error code SWW.");
        }
    }
    public static void updateStock (int StockID, boolean Exclusive){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Stock SET Exclusive = ? WHERE StockID = ?");
            ps.setBoolean(1, Exclusive);
            ps.setInt(2, StockID);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deleteStock (int StockID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Stock WHERE StockID = ?");
            ps.setInt(1, StockID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
