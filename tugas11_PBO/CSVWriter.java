import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CSVWriter {
    public static void main(String[] args) {
        String csvFile = "new_students.csv"; // File tempat menyimpan data baru
        Scanner input = new Scanner(System.in);

        // true di FileWriter artinya data baru akan ditambahkan di bawahnya (append), bukan menimpa file lama
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            System.out.println("=== Tambah Data Mahasiswa Baru ===");
            
            System.out.print("Masukkan NIM: ");
            String nim = input.nextLine();
            
            System.out.print("Masukkan Nama: ");
            String nama = input.nextLine();
            
            System.out.print("Masukkan Umur: ");
            String umur = input.nextLine();
            
            System.out.print("Masukkan Prodi: ");
            String prodi = input.nextLine();

            // Gabungkan inputan menjadi format CSV (dipisah koma)
            String lineData = nim + "," + nama + "," + umur + "," + prodi;

            // Tulis ke file
            bw.write(lineData);
            bw.newLine(); // Tambah baris baru

            System.out.println("Data berhasil disimpan ke " + csvFile);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            input.close(); // Tutup scanner
        }
    }
}