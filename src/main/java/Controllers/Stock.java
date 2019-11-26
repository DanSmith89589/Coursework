package Controllers;

import Server.Main;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("stock")
public class Stock {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listStock() {
        System.out.println("stock/list");
        JSONArray list = new JSONArray();

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("StockID", results.getInt(1));
                item.put("Brand", results.getString(2));
                item.put("StockName", results.getString(3));
                item.put("Price", results.getString(4));
                item.put("Quantity", results.getInt(5));
                item.put("Type", results.getString(6));
                item.put("Exclusive", results.getBoolean(7));

            }
                return list.toString();

            } catch(Exception exception){
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

            }
        }

        @GET
        @Path("listFootwear/{Type}")
        @Produces(MediaType.APPLICATION_JSON)
                public String getlistFootwear(@PathParam("Type") String Type) throws Exception {
            if (Type == null) {
                throw new Exception("Stock 'Type' is missing in the HTTP request's URL.");
            }
            System.out.println("stock/listFootwear/" + Type);
            JSONObject item = new JSONObject();

            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE Type = Footwear");
                ResultSet results = ps.executeQuery();
                if (results.next()) {
                    item.put("Type", Type);
                    item.put("StockID", results.getInt(1));
                    item.put("Brand", results.getString(2));
                    item.put("StockName", results.getString(3));
                    item.put("Price", results.getString(4));
                    item.put("Quantity", results.getInt(5));
                    item.put("Type", results.getString(6));
                    item.put("Exclusive", results.getBoolean(7));
                }
                return item.toString();

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
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
