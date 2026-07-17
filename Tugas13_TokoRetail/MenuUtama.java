import java.sql.*;
import java.util.Scanner;

public class MenuUtama {

    // KONEKSI DATABASE 
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/toko_ritel";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement state;
    static ResultSet rs;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            state = conn.createStatement();

            while (true) {
                System.out.println("\n=================================");
                System.out.println("        MENU TOKO RETAIL         ");
                System.out.println("=================================");
                System.out.println("1. Tampil Semua Data");
                System.out.println("2. Tambah Data");
                System.out.println("3. Cari Data");
                System.out.println("4. Ubah Data");
                System.out.println("5. Hapus Data");
                System.out.println("0. Keluar");
                System.out.print("Pilihan : ");
                
                int pilihan = input.nextInt();
                input.nextLine(); // Clear buffer enter

                switch (pilihan) {
                    case 1:
                        tampilData();
                        break;
                    case 2:
                        tambahData();
                        break;
                    case 3:
                        cariData();
                        break;
                    case 4:
                        ubahData();
                        break;
                    case 5:
                        hapusData();
                        break;
                    case 0:
                        System.out.println("Terima kasih! Keluar dari program...");
                        state.close();
                        conn.close();
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 1. TAMPIL DATA (Logika dari ShowData.java kamu)
    public static void tampilData() {
        try {
            String query = "SELECT * FROM tbl_barang";
            rs = state.executeQuery(query);
            
            System.out.println("\n+-------------------------------------------------------------+");
            System.out.println("| # | Kode | Nama Barang               | Harga     | Stok     |");
            System.out.println("+-------------------------------------------------------------+");
            
            int nomor = 0;
            while (rs.next()) {
                nomor++;
                System.out.printf("| %-1d | %-4s | %-25s | %-9d | %-8d |\n", 
                    nomor,
                    rs.getString("kode_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga_barang"),
                    rs.getInt("stok_barang")
                );
            }
            System.out.println("+-------------------------------------------------------------+");
            System.out.println("Total: " + nomor + " barang");
        } catch (Exception e) {
            System.out.println("Gagal menampilkan data: " + e.getMessage());
        }
    }

    // 2. TAMBAH DATA (Logika dari Insert.java)
    public static void tambahData() {
        try {
            System.out.print("Masukkan Kode Barang (4 Karakter): ");
            String kode = input.nextLine();
            System.out.print("Masukkan Nama Barang: ");
            String nama = input.nextLine();
            System.out.print("Masukkan Harga Barang: ");
            int harga = input.nextInt();
            System.out.print("Masukkan Stok Barang: ");
            int stok = input.nextInt();
            input.nextLine();

            String query = "INSERT INTO tbl_barang (kode_barang, nama_barang, harga_barang, stok_barang) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, kode);
            pstmt.setString(2, nama);
            pstmt.setInt(3, harga);
            pstmt.setInt(4, stok);
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Data barang berhasil ditambahkan!");
        } catch (Exception e) {
            System.out.println("Gagal menambah data: " + e.getMessage());
        }
    }

    // 3. CARI DATA (Logika dari Search.java)
    public static void cariData() {
        try {
            System.out.print("Masukkan Kode Barang yang dicari: ");
            String kode = input.nextLine();

            String query = "SELECT * FROM tbl_barang WHERE kode_barang = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, kode);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- Data Ditemukan ---");
                System.out.println("Kode Barang  : " + rs.getString("kode_barang"));
                System.out.println("Nama Barang  : " + rs.getString("nama_barang"));
                System.out.println("Harga Barang : " + rs.getInt("harga_barang"));
                System.out.println("Stok Barang  : " + rs.getInt("stok_barang"));
            } else {
                System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
            }
        } catch (Exception e) {
            System.out.println("Gagal mencari data: " + e.getMessage());
        }
    }

    // 4. UBAH DATA (Fitur Tambahan)
    public static void ubahData() {
        try {
            System.out.print("Masukkan Kode Barang yang akan diubah: ");
            String kode = input.nextLine();

            System.out.print("Nama Barang Baru: ");
            String nama = input.nextLine();
            System.out.print("Harga Barang Baru: ");
            int harga = input.nextInt();
            System.out.print("Stok Barang Baru: ");
            int stok = input.nextInt();
            input.nextLine();

            String query = "UPDATE tbl_barang SET nama_barang=?, harga_barang=?, stok_barang=? WHERE kode_barang=?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nama);
            pstmt.setInt(2, harga);
            pstmt.setInt(3, stok);
            pstmt.setString(4, kode);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Data barang berhasil diubah!");
            } else {
                System.out.println("Barang tidak ditemukan atau tidak ada perubahan data.");
            }
        } catch (Exception e) {
            System.out.println("Gagal mengubah data: " + e.getMessage());
        }
    }

    // 5. HAPUS DATA (Logika dari Delete.java)
    public static void hapusData() {
        try {
            System.out.print("Masukkan Kode Barang yang akan dihapus: ");
            String kode = input.nextLine();

            String query = "DELETE FROM tbl_barang WHERE kode_barang = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, kode);

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Data barang berhasil dihapus!");
            } else {
                System.out.println("Barang dengan kode tersebut tidak ditemukan.");
            }
        } catch (Exception e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }
}