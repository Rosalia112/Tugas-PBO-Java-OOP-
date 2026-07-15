import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AplikasiBiodata extends JFrame {
    // 1. Deklarasi Komponen GUI
    private JTextField txtNim, txtNama, txtProdi;
    private JTextArea txtOutput;
    private JButton btnTampilkan, btnReset;

    public AplikasiBiodata() {
        // 2. Atur window Interface Utama (pake JFrame)
        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Agar window interface muncul di tengah layar
        setLayout(new BorderLayout(10, 10));

        // === PANEL INPUT (Bagian Atas) ===
        JPanel panelInput = new JPanel();
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));
        // Karena akan ada 3 inputan maka:
        // Menggunakan GridLayout: 3 baris, 2 kolom (Label dan TextField)
        panelInput.setLayout(new GridLayout(3, 2, 5, 5)); 

        panelInput.add(new JLabel("NIM"));
        txtNim = new JTextField();
        panelInput.add(txtNim);

        panelInput.add(new JLabel("Nama"));
        txtNama = new JTextField();
        panelInput.add(txtNama);

        panelInput.add(new JLabel("Program Studi"));
        txtProdi = new JTextField();
        panelInput.add(txtProdi);

        // === PANEL TOMBOL (Bagian Tengah) ===
        JPanel panelTombol = new JPanel();
        panelTombol.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        btnTampilkan = new JButton("Tampilkan");
        btnReset = new JButton("Reset");
        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        // === PANEL OUTPUT (Bagian paling Bawah) ===
        JPanel panelOutput = new JPanel();
        panelOutput.setBorder(BorderFactory.createTitledBorder("Output"));
        panelOutput.setLayout(new BorderLayout());
        
        txtOutput = new JTextArea(10, 30);
        txtOutput.setEditable(false); // Supaya area output tidak bisa diketik manual
        txtOutput.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Pake font Monospaced biar rapi dan sejajar
        JScrollPane scrollPane = new JScrollPane(txtOutput); // Biar kalau kepanjangan bisa di-scroll
        panelOutput.add(scrollPane, BorderLayout.CENTER);

        // Menggabungkan semua panel ke Window Utama
        JPanel panelAtasDanTengah = new JPanel(new BorderLayout(5, 5));
        panelAtasDanTengah.add(panelInput, BorderLayout.NORTH);
        panelAtasDanTengah.add(panelTombol, BorderLayout.SOUTH);

        add(panelAtasDanTengah, BorderLayout.NORTH);
        add(panelOutput, BorderLayout.CENTER);

        // 3. EVENT HANDLING (Aksi Ketika Tombol Diklik)

        // Aksi yang akan terjadi ketika Tombol Tampilkan
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ambil data dari input text field
                String nim = txtNim.getText().trim();
                String nama = txtNama.getText().trim();
                String prodi = txtProdi.getText().trim();

                // Validasi sederhana, jangan sampai ada kolom kosong
                if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua data harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // A. Tampilkan teks ke JTextArea (Sesuai format di gambar soal)
                String hasilOutput = "========== BIODATA MAHASISWA ==========\n\n" +
                                     "NIM           : " + nim + "\n" +
                                     "Nama          : " + nama + "\n" +
                                     "Program Studi : " + prodi + "\n";
                txtOutput.setText(hasilOutput);

                //  Simpan data otomatis ke students.csv (nerapin materi File Handling hehe)
                // Biar data pas udah di nput bisa disimpan
                simpanKeCSV(nim, nama, prodi);
            }
        });

        // Tombol Reset
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Kosongin semua komponen input dan output
                txtNim.setText("");
                txtNama.setText("");
                txtProdi.setText("");
                txtOutput.setText("");
                txtNim.requestFocus(); // Fokuskan kursor kembali ke inputan NIM
            }
        });
    }

    // Fungsi khusus untuk menulis data ke file CSV
    private void simpanKeCSV(String nim, String nama, String prodi) {
        String csvFile = "students.csv";
        // Pake parameter 'true' agar data baru di-append (ditambah ke bawah), bukan menimpa yang lama
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            String barisData = nim + "," + nama + "," + prodi;
            bw.write(barisData);
            bw.newLine();
            
            // Nampilin notifikasi kecil kalau berhasil disimpan ke file
            System.out.println("Data " + nama + " sukses disimpan ke " + csvFile);
        } catch (IOException ex) {
            System.out.println("Gagal menyimpan data ke file CSV.");
            ex.printStackTrace();
        }
    }

    // 4. Main Method untuk Menjalankan Program
    public static void main(String[] args) {
        // Menjalankan GUI di Thread yang aman (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplikasiBiodata().setVisible(true);
            }
        });
    }
}