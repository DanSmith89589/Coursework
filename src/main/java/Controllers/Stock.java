package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Stock {
    public static void listStock() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int StockID = results.getInt(1);
                String Brand = results.getString(2);
                String StockName = results.getString(3);
                String Price = results.getString(4);
                int Quantity = results.getInt(5);
                String Type = results.getString(6);
                boolean Exclusive = results.getBoolean(7);

                System.out.println(StockID + " " + Brand + " " + StockName + " " + Price + " " + Quantity + " " + Type + " " + Exclusive);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }
    }

        public static void listFootwear() {
            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE Type = Footwear");

                ResultSet results = ps.executeQuery();
                while (results.next()) {
                    int StockID = results.getInt(1);
                    String Brand = results.getString(2);
                    String StockName = results.getString(3);
                    String Price = results.getString(4);
                    int Quantity = results.getInt(5);
                    String Type = results.getString(6);
                    boolean Exclusive = results.getBoolean(7);

                    System.out.println(StockID + " " + Brand + " " + StockName + " " + Price + " " + Quantity + " " + Type + " " + Exclusive);
                }

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
            }

    }

    public static void listClothes() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE Type = Clothes");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int StockID = results.getInt(1);
                String Brand = results.getString(2);
                String StockName = results.getString(3);
                String Price = results.getString(4);
                int Quantity = results.getInt(5);
                String Type = results.getString(6);
                boolean Exclusive = results.getBoolean(7);

                System.out.println(StockID + " " + Brand + " " + StockName + " " + Price + " " + Quantity + " " + Type + " " + Exclusive);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }

    public static void lookUp() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE StockID = ?");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int StockID = results.getInt(1);
                String Brand = results.getString(2);
                String StockName = results.getString(3);
                String Price = results.getString(4);
                int Quantity = results.getInt(5);
                String Type = results.getString(6);
                boolean Exclusive = results.getBoolean(7);

                System.out.println(StockID + " " + Brand + " " + StockName + " " + Price + " " + Quantity + " " + Type + " " + Exclusive);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }

    public static void insertStock(int StockID, String Brand, String StockName, String Price, int Quantity, String Type, boolean Exclusive){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Stock (StockID, Brand, StockName, Price, Quantity, Type, Exclusive) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, StockID);
            ps.setString(2, Brand);
            ps.setString(3, StockName);
            ps.setString(4, Price);
            ps.setInt(5, Quantity);
            ps.setString(6, Type);
            ps.setBoolean(7, Exclusive);
            ps.executeUpdate();
            System.out.println("Record added to Stock table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something has gone wrong.");
        }
    }

    public static void updateStock (int StockID, String Price) {
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Stock SET Price = ? WHERE StockID = ?");
            ps.setString(1, Price);
            ps.setInt(2, StockID);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

        public static void updateStock(int StockID, int Quantity){
            try {

                PreparedStatement ps = Main.db.prepareStatement("UPDATE Stock SET Quantity = ? WHERE StockID = ?");
                ps.setInt(1, Quantity);
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
