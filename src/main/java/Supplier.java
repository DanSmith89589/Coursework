import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Supplier {
    public static void listUsers() {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT UserID, username, dateOfBirth FROM Users");

            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int userID = results.getInt(1);
                String username = results.getString(2);
                String dateOfBirth = results.getString(3);
                System.out.println(userID + " " + username + " " + dateOfBirth);
            }

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
        }

    }
    public static void insertWeight(String date, int weightInKG, int userID){
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO Weights (Date, WeightInKG, UserID) VALUES (?, ?, ?)");
            ps.setString(1, date);
            ps.setInt(2, weightInKG);
            ps.setInt(3, userID);
            ps.executeUpdate();
            System.out.println("Record added to Weights table");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            System.out.println("Error: Something as gone wrong. Please contact the administrator with the error code WC-WA.");
        }
    }
    public static void updateUser (String firstName, String lastName, int userID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Users SET FirstName = ?, LastName = ? WHERE UserID = ?");
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setInt(3, userID);
            ps.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

    }
    public static void deleteWeight (int weightID){
        try {

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Weights WHERE WeightID = ?");
            ps.setInt(1, weightID);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }

    }
}
