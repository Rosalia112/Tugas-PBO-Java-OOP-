package daoo; // Perbaikan nama package sesuai struktur foldermu

import config.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Character;

public class CharacterDAO {
    
    // Fitur 1: Tambah Karakter (8 Parameter disinkronkan ke DB)
    public void addCharacter(Character ch) {
        // Query SQL disesuaikan untuk memasukkan 7 kolom (id_character auto increment jadi tidak perlu di-insert)
        String sql = "INSERT INTO characters (name, role, level, hp, attack, def, is_active) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, ch.getName());
            stmt.setString(2, ch.getRole());
            stmt.setInt(3, ch.getLevel());
            stmt.setInt(4, ch.getHp());      // Sesuai urutan constructor: HP dulu
            stmt.setInt(5, ch.getAttack());  // Baru ATK
            stmt.setInt(6, ch.getDef());     // Parameter ke-6 (DEF) yang tadi hilang!
            stmt.setBoolean(7, ch.isActive()); // Parameter ke-7 (Status Aktif)
            
            stmt.executeUpdate();
            System.out.println("Karakter " + ch.getName() + " berhasil ditambahkan ke database!");
        } catch (SQLException e) {
            System.out.println("Gagal tambah karakter: " + e.getMessage());
        }
    }

    // Fitur 2: Lihat Daftar Karakter
    public List<Character> getAllCharacters() {
        List<Character> list = new ArrayList<>();
        String sql = "SELECT * FROM characters";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                // Menyusun objek sesuai urutan constructor di Character.java (8 parameter)
                Character ch = new Character(
                    rs.getInt("id_char"),
                    rs.getString("name"),
                    rs.getString("role"),
                    rs.getInt("level"),
                    rs.getInt("hp"),       // Urutan parameter ke-5: HP
                    rs.getInt("attack"),   // Urutan parameter ke-6: ATK
                    rs.getInt("def"),      // Urutan parameter ke-7: DEF
                    rs.getBoolean("is_active") // Urutan parameter ke-8: Status Aktif
                );
                list.add(ch);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }
        return list;
    }
}