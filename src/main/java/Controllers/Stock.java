package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("stock/")
public class Stock {
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String listStock() {
        System.out.println("stock/list");
        JSONArray list = new JSONArray();

        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock ORDER by TYPE ");

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
                list.add(stock);

            }
                return list.toString();

            } catch(Exception exception){
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

            }
        }

        @GET
        @Path("listType/{Type}")
        @Produces(MediaType.APPLICATION_JSON)
                public String getlistType(@PathParam("Type") String Type) throws Exception {
            if (Type == null) {
                throw new Exception("Stock 'Type' is missing in the HTTP request's URL.");
            }
            System.out.println("stock/listType/" + Type);
            JSONArray list = new JSONArray();

            try {
                PreparedStatement ps = Main.db.prepareStatement("SELECT StockID, Brand, StockName, Price, Quantity, Type, Exclusive FROM Stock WHERE Type = ?");
                ps.setString(1, Type);
                ResultSet results = ps.executeQuery();

                while (results.next()) {
                    JSONObject type = new JSONObject();
                    type.put("Type", Type);
                    type.put("StockID", results.getInt(1));
                    type.put("Brand", results.getString(2));
                    type.put("StockName", results.getString(3));
                    type.put("Price", results.getString(4));
                    type.put("Quantity", results.getInt(5));
                    type.put("Exclusive", results.getBoolean(6));
                    list.add(type);
                }
                return list.toString();

            } catch (Exception exception) {
                System.out.println("Database error: " + exception.getMessage());
                return "{\"error\": \"Unable to get stock type, please see server console for more info.\"}";
            }
        }



    @GET
    @Path("lookUp/{StockID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getlistStock(@PathParam("StockID") Integer StockID) throws Exception {
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
                lookUp.put("Brand", results.getString(2));
                lookUp.put("StockName", results.getString(3));
                lookUp.put("Price", results.getString(4));
                lookUp.put("Quantity", results.getInt(5));
                lookUp.put("Type", results.getString(6));
                lookUp.put("Exclusive", results.getBoolean(7));
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
            if (Brand == null || StockName == null || Price == null || Quantity == null || Type == null || Exclusive == null){
                throw new Exception("One or more from data parameters are missing in the HTTP Request.");
            }
            System.out.println("stock/add Stock=" + StockID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Stock (Brand, StockName, Price, Quantity, Type, Exclusive) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, Brand);
            ps.setString(2, StockName);
            ps.setString(3, Price);
            ps.setInt(4, Quantity);
            ps.setString(5, Type);
            ps.setBoolean(6, Exclusive);
            ps.executeUpdate();
            return "{\"status\":\"OK\"}";

        } catch (Exception exception) {
            System.out.println("database error: " + exception.getMessage());
            return "{\"error\": \"Unable to add new stock item to database.\"}";
        }
    }

    @POST
    @Path("updatePrice")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePrice (@FormDataParam("StockID") Integer StockID, @FormDataParam("Price") String Price) {
        try {
            if(StockID == null || Price == null){
                throw new Exception("One or more from the data parameters are missing in the HTTP request");
            }
            System.out.println("stock/updatePrice StockID=" + StockID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Stock SET Price = ? WHERE StockID = ?");
            ps.setString(1, Price);
            ps.setInt(2, StockID);
            ps.executeUpdate();
            return "{\"status\":\"OK\"}";
        } catch (Exception exception) {

            System.out.println("Database error:" + exception.getMessage());
            return"{\"error\":\"unable to update the price Stock item.\"}";
        }
    }

    @POST
    @Path("updateQuantity")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePrice (@FormDataParam("StockID") Integer StockID, @FormDataParam("Quantity") Integer Quantity) {
        try {
            if(StockID == null || Quantity == null){
                throw new Exception("One or more from the data parameters are missing in the HTTP request");
            }
            System.out.println("stock/updatePrice StockID=" + StockID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Stock SET Quantity = ? WHERE StockID = ?");
            ps.setInt(1, Quantity);
            ps.setInt(2, StockID);
            ps.executeUpdate();
            return "{\"status\":\"OK\"}";
        } catch (Exception exception) {

            System.out.println("Database error:" + exception.getMessage());
            return"{\"error\":\"unable to update quantity of Stock item.\"}";
        }
    }

    @POST
    @Path("delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteStock (@FormDataParam("StockID") Integer StockID){

        try {
            if(StockID == null){
                throw new Exception("A data parameter is missing from the HTTP request.");
            }
            System.out.println("stock/delete StockID=" + StockID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Stock WHERE StockID = ?");
            ps.setInt(1, StockID);
            ps.executeUpdate();
            return "{\"status\":\"OK\"}";

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return "{\"error\":\"Unable to delete item from table.\"}";
        }

    }
}
