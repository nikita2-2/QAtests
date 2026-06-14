package utils;

import java.sql.*;

public class DbManager {
    private static final String URL = "jdbc:postgresql://86.57.161.116:50432/register_office";
    private static final String USER = "user";
    private static final String PASSWORD = "user_senla";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String getApplicantNameById(int applicantId) {
        String sql = "SELECT name FROM reg_office.applicants WHERE applicantid = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, applicantId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getAdminNameById(int staffid) {
        String sql = "SELECT name FROM reg_office.staff WHERE staffid = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, staffid);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}