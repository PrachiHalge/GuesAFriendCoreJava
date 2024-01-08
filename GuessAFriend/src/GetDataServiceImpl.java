import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetDataServiceImpl {

    public ArrayList<SecretSanta> getNamesWithNoToFriend() {
        ArrayList<SecretSanta> listWithNoToFriend = new ArrayList<>();
        try (Connection connection = ConnectionJDBC.getConnection()) {
            String query = "SELECT * FROM testsanta WHERE ToName IS NULL";
            try (PreparedStatement ps = connection.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SecretSanta person = new SecretSanta();
                    person.setPersonId(rs.getInt("PersonId"));
                    person.setFromName(rs.getString("FromName"));
                    person.setToName(rs.getString("ToName"));
                    listWithNoToFriend.add(person);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately (e.g., log, throw a custom exception)
        }
        return listWithNoToFriend;
    }

    public ArrayList<SecretSanta> getNamesWithNoFromFriend() {
        ArrayList<SecretSanta> listWithNoFromFriend = new ArrayList<>();
        try (Connection connection = ConnectionJDBC.getConnection()) {
            String query = "SELECT * FROM testsanta WHERE FromName NOT IN (SELECT DISTINCT ToName FROM testsanta WHERE ToName IS NOT NULL)";
            try (PreparedStatement ps = connection.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SecretSanta person = new SecretSanta();
                    person.setPersonId(rs.getInt("PersonId"));
                    person.setFromName(rs.getString("FromName"));
                    listWithNoFromFriend.add(person);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately (e.g., log, throw a custom exception)
        }
        return listWithNoFromFriend;
    }

    public SecretSanta getRandomElement(List<SecretSanta> list, String fromFriendName) {
        SecretSanta toFriend = new SecretSanta();
        Random rand = new Random();
        for (int i = 0; i < list.size(); i++) {
            int randomIndex = rand.nextInt(list.size());
            toFriend = list.get(randomIndex);
            if (!(fromFriendName.equals(toFriend.getFromName().toLowerCase()))) {
                return toFriend;
            }
        }
        return toFriend;
    }

    public boolean updateToName(String fromName, String toName) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            String query = "UPDATE testsanta SET ToName=? WHERE FromName=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, toName);
                ps.setString(2, fromName);
                ps.executeUpdate();
                connection.setAutoCommit(false);
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately (e.g., log, throw a custom exception)
            return false;
        }
    }

    public String getFriend(String fromName) {
        try (Connection connection = ConnectionJDBC.getConnection()) {
            String query = "SELECT ToName FROM testsanta WHERE FromName LIKE ?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, fromName);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        return rs.getString("ToName");
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception appropriately (e.g., log, throw a custom exception)
        }
        return null;
    }
}
