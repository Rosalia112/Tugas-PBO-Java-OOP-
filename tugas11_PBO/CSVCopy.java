import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCopy {
    public static void main(String[] args) {
        String fileAsal = "students.csv";       // File yang mau dicopy
        String fileTujuan = "students_backup.csv"; // File hasil copyan
        String line;

        // Membuka file asal untuk dibaca, dan file tujuan untuk ditulis
        try (BufferedReader br = new BufferedReader(new FileReader(fileAsal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileTujuan))) {
            
            System.out.println("Sedang menyalin file...");
            
            // Baca baris per baris dari file asal, lalu langsung tulis ke file tujuan
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
            
            System.out.println("Salin data selesai! File baru tersimpan sebagai: " + fileTujuan);

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyalin file.");
            e.printStackTrace();
        }
    }
}