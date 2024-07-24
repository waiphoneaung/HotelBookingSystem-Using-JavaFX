package staff;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DBConnection;

public class StaffManager {
    public static List<Staff> getAllStaff() {
        ArrayList<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Staff s = new Staff(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4));
                list.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getNewID() {
        String sql = "SELECT MAX(staffID)+1 newNo FROM staff";
        String newNo = "1";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                newNo = rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newNo;
    }

    public static boolean addNewStaff(Staff s) {
        String sql = "INSERT INTO Staff(username, password, is_admin) VALUE(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, s.getName());
            psmt.setString(2, hashPassword(s.getPassword())); // Hash the password before storing
            psmt.setString(3, s.getPosition());
            int r = psmt.executeUpdate();
            return r > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStaff(Staff s) {
        String sql = "UPDATE staff SET username=?, password=?, is_admin=? WHERE staffID=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);{
                psmt.setString(1, s.getName());
                psmt.setString(2, hashPassword(s.getPassword())); // Hash the password before updating
                psmt.setString(3, s.getPosition());
                psmt.setString(4, s.getId());
                int r = psmt.executeUpdate();
                return r > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteStaff(Staff todelete) {
        String sql = "DELETE FROM staff WHERE staffID=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement psmt = con.prepareStatement(sql);{
                psmt.setString(1, todelete.getId());
                int r = psmt.executeUpdate();
                return r > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte hashedByte : hashedBytes) {
                String hex = Integer.toHexString(0xff & hashedByte);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
