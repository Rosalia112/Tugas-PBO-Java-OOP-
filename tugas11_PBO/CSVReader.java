import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static void main(String[] args) {
        // Sesuaikan path file-nya. Jika satu folder dengan project, cukup "students.csv"
        String csvFile = "students.csv"; 
        String line;
        String csvSplitBy = ",";
        int totalBaris = 0; // Variabel untuk menghitung baris

        System.out.println("NIM \t| NAMA \t| UMUR \t| PRODI");
        System.out.println("---------------------------------");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Membaca baris pertama (header)
            String header = br.readLine(); 
            
            // Membaca baris-baris data selanjutnya
            while ((line = br.readLine()) != null) {
                totalBaris++; // Setiap nemu baris baru, counter bertambah
                
                // Memisahkan data berdasarkan koma
                String[] student = line.split(csvSplitBy); 
                
                // Menampilkan data agar rapi
                System.out.println(student[0] + " \t| " + student[1] + " \t| " + student[2] + " \t| " + student[3]);
            }
            
            System.out.println("---------------------------------");
            System.out.println("Jumlah total baris data: " + totalBaris);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}