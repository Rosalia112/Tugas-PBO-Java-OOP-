package view;

import config.Koneksi;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormBarang extends JFrame {
    private JTable tabelBarang;
    private DefaultTableModel model;
    private JTextField txtNama, txtHarga, txtStok;
    private JButton btnSimpan, btnHapus;

    public FormBarang() {
        setTitle("Manajemen Data Barang");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Komponen GUI
        JLabel lblNama = new JLabel("Nama Barang:"); lblNama.setBounds(20, 20, 100, 25); add(lblNama);
        txtNama = new JTextField(); txtNama.setBounds(130, 20, 150, 25); add(txtNama);

        JLabel lblHarga = new JLabel("Harga:"); lblHarga.setBounds(20, 50, 100, 25); add(lblHarga);
        txtHarga = new JTextField(); txtHarga.setBounds(130, 50, 150, 25); add(txtHarga);

        JLabel lblStok = new JLabel("Stok:"); lblStok.setBounds(20, 80, 100, 25); add(lblStok);
        txtStok = new JTextField(); txtStok.setBounds(130, 80, 150, 25); add(txtStok);

        btnSimpan = new JButton("Simpan (SP)"); btnSimpan.setBounds(20, 120, 120, 30); add(btnSimpan);
        btnHapus = new JButton("Hapus"); btnHapus.setBounds(160, 120, 100, 30); add(btnHapus);

        // Tabel
        tabelBarang = new JTable();
        JScrollPane scroll = new JScrollPane(tabelBarang);
        scroll.setBounds(20, 170, 545, 170);
        add(scroll);

        model = new DefaultTableModel(new String[]{"ID", "Nama Barang", "Harga", "Stok"}, 0);
        tabelBarang.setModel(model);

        // Load Data awal
        muatDataTabel();

        // Action Listener Simpan menggunakan Stored Procedure
        btnSimpan.addActionListener(e -> {
            try {
                Connection c = Koneksi.getKoneksi();
                // Memanggil Stored Procedure 'tambah_barang'
                CallableStatement cs = c.prepareCall("{call tambah_barang(?, ?, ?)}");
                cs.setString(1, txtNama.getText());
                cs.setInt(2, Integer.parseInt(txtHarga.getText()));
                cs.setInt(3, Integer.parseInt(txtStok.getText()));
                cs.execute();
                
                JOptionPane.showMessageDialog(this, "Data Berhasil Disimpan via SP!");
                muatDataTabel();
                bersihkanForm();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        // Action Listener Hapus
        btnHapus.addActionListener(e -> {
            int row = tabelBarang.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih baris yang mau dihapus!");
                return;
            }
            try {
                int id = (int) model.getValueAt(row, 0);
                Connection c = Koneksi.getKoneksi();
                PreparedStatement ps = c.prepareStatement("DELETE FROM barang WHERE id_barang=?");
                ps.setInt(1, id);
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "Data Terhapus!");
                muatDataTabel();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error hapus data: " + ex.getMessage());
            }
        });
    }

    private void muatDataTabel() {
        model.setRowCount(0);
        try {
            Connection c = Koneksi.getKoneksi();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM barang");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id_barang"),
                    rs.getString("nama_barang"),
                    rs.getInt("harga"),
                    rs.getInt("stok")
                });
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void bersihkanForm() {
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormBarang().setVisible(true));
    }
}