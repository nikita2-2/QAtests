package utils;

import java.sql.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbManager {
    private static final Logger log = LoggerFactory.getLogger(DbManager.class);

    private static String url;
    private static String user;
    private static String password;

    static {
        Properties properties = new Properties();
        try (InputStream input = DbManager.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("файл database.properties не найден");
            }
            properties.load(input);

            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Не удалось прочитать конфигурацию базы данных!");
        }
    }
    public static Connection getConnection() throws SQLException {
        log.info("Подключение к БД установлено");
        return DriverManager.getConnection(url, user, password);
    }

    public static String getApplicantNameById(int applicantId) {
        String sql = "SELECT name FROM reg_office.applicants WHERE applicantid = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, applicantId);
            log.info("Поиск пользователя по айди в БД");

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
            log.info("Поиск админа по айди в БД");

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