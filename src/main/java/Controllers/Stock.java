package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
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
                JSONObject stock = new JSONObject();
                stock.put("StockID", results.getInt(1));
                stock.put("Brand", results.getString(2));
                stock.put("StockName", results.getString(3));
                stock.put("Price", results.getString(4));
                stock.put("Quantity", results.getInt(5));
                stock.put("Type", results.getString(6));
                stock.put("Exclusive", results.getBoolean(7));

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
            JSONObject footwear = new JSONObject();

            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE Type = Footwear");
                ResultSet results = ps.executeQuery();
                if (results.next()) {

                    footwear.put("StockID", results.getInt(1));
                    footwear.put("Brand", results.getString(2));
                    footwear.put("StockName", results.getString(3));
                    footwear.put("Price", results.getString(4));
                    footwear.put("Quantity", results.getInt(5));
                    footwear.put("Type", results.getString(6));
                    footwear.put("Exclusive", results.getBoolean(7));
                }
                return footwear.toString();

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to get footwear, please see server console for more info.\"}";
            }
        }



    @GET
    @Path("listClothes/{Type}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getlistClothes(@PathParam("Type") String Type) throws Exception {
        if (Type == null) {
            throw new Exception("Stock 'Type' is missing in the HTTP request's URL.");
        }
        System.out.println("stock/listClothes/" + Type);
        JSONObject clothes = new JSONObject();

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE Type = Footwear");
            ResultSet results = ps.executeQuery();
            if (results.next()) {

                clothes.put("StockID", results.getInt(1));
                clothes.put("Brand", results.getString(2));
                clothes.put("StockName", results.getString(3));
                clothes.put("Price", results.getString(4));
                clothes.put("Quantity", results.getInt(5));
                clothes.put("Type", results.getString(6));
                clothes.put("Exclusive", results.getBoolean(7));
            }
            return clothes.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get clothes, please see server console for more info.\"}";
        }
    }

    @GET
    @Path("lookUp/{StockID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getlistClothes(@PathParam("StockID") Integer StockID) throws Exception {
        if (StockID == null) {
            throw new Exception("Stock 'StockID' is missing in the HTTP request's URL.");
        }
        System.out.println("stock/lookUp/" + StockID);
        JSONObject lookUp = new JSONObject();

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE StockID = ?");
            ps.setInt(1,StockID);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                lookUp.put("StockID", StockID);
                lookUp.put("Brand", results.getString(1));
                lookUp.put("StockName", results.getString(2));
                lookUp.put("Price", results.getString(3));
                lookUp.put("Quantity", results.getInt(4));
                lookUp.put("Type", results.getString(5));
                lookUp.put("Exclusive", results.getBoolean(6));
            }
            return lookUp.toString();

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }

    @POST
    @Path("add")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertStock(@FormDataParam("StockID") Integer StockID, @FormDataParam("Brand") String Brand, @FormDataParam("StockName") String StockName, @FormDataParam("Price") String Price, @FormDataParam("Quantity") Integer Quantity, @FormDataParam("Type") String Type, @FormDataParam("Exclusive") Boolean Exclusive ){
        try {
            if (StockID == null || Brand == null || StockName == null || Price == null || Quantity == null || Type == null || Exclusive == null){
                throw new Exception("One or more from data parameters are missing in the HTTP Request.");
            }
            System.out.println("stock/add Stock=" + StockID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Stock (StockID, Brand, StockName, Price, Quantity, Type, Exclusive) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, StockID);
            ps.setString(2, Brand);
            ps.setString(3, StockName);
            ps.setString(4, Price);
            ps.setInt(5, Quantity);
            ps.setString(6, Type);
            ps.setBoolean(7, Exclusive);
            return "{\"status\":\"OK\"}";

        } catch (Exception exception) {
            System.out.println("database error: " + exception.getMessage());
            return "{\"error\": \"Unable to add new stock item to database.\"}";
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
