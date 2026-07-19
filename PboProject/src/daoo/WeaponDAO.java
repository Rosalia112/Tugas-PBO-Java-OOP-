package daoo;

import config.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WeaponDAO {
    // Fitur Ganti Senjata yang akan memicu TRIGGER before_weapon_update di database kamu
    public void changeWeapon(int id_char, String wp_name, int wp_attack) {
        String sql = "UPDATE weapons SET wp_name = ?, wp_attack = ?, id_char = ? WHERE id_char = ?"; 

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, wp_name);
            stmt.setInt(2, wp_attack);
            stmt.setInt(3, id_char);
            stmt.setInt(4, id_char);
            
            int rows = stmt.executeUpdate();
            if (rows == 0) {
                // Jika data weapon untuk karakter tersebut belum ada, insert baru
                    String insertSql = "INSERT INTO weapons (id_char, wp_name, wp_level, wp_attack) VALUES (?, ?, 1, ?)";                try (PreparedStatement insStmt = conn.prepareStatement(insertSql)) {
                    insStmt.setInt(1, id_char);
                    insStmt.setString(2, wp_name);
                    insStmt.setInt(3, wp_attack);
                    insStmt.executeUpdate();
                }
            }
            System.out.println("Senjata berhasil diperbarui! Nama Senjata: " + wp_name + ", ATK Bonus: " + wp_attack);
        } catch (SQLException e) {
            System.out.println("Gagal mengganti senjata: " + e.getMessage());
        }
    }
}