package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection conn;
    
    public static Connection getKoneksi() {
        if (conn == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/db_penjualan"; 
                String user = "root";
                String pass = "";
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                conn = DriverManager.getConnection(url, user, pass);
                System.out.println("Koneksi Database Berhasil!");
            } catch (SQLException e) {
                System.err.println("Koneksi Gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}