import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // List untuk menampung banyak objek mahasiswa
        ArrayList<HitungNilai> daftarMahasiswa = new ArrayList<>();
        
        System.out.println("--- DATA NILAI MAHASISWA ---");
        String pilihan;
        
        // Loop untuk mengentri data mahasiswa
        do {
            System.out.print("Masukkan NIM   : ");
            String nim = input.nextLine();
            System.out.print("Masukkan Nama  : ");
            String nama = input.nextLine();
            System.out.print("Masukkan Nilai : ");
            int nilai = input.nextInt();
            input.nextLine(); // Membersihkan sisa enter di scanner

            // Membuat objek dari class anak
            HitungNilai mhs = new HitungNilai(nim, nama, nilai);
            
            // Validasi input nilai salah sesuai poin g
            if (mhs.getGrade().equals("Input nilai anda salah")) {
                System.out.println("NOTIFIKASI: Input nilai anda salah! Data tidak tersimpan.");
            } else {
                daftarMahasiswa.add(mhs); // Simpan ke list jika nilainya benar
            }

            System.out.print("Input data lagi? (y/n): ");
            pilihan = input.nextLine();
            System.out.println();
        } while (pilihan.equalsIgnoreCase("y"));

        // =======================================================
        // BAGIAN OUTPUT & LAYOUT (SESUAI GAMBAR SOAL)
        // =======================================================
        
        // Variabel untuk kalkulasi statistik ringkasan di bawah
        int jumlahLulus = 0;
        int jumlahTidakLulus = 0;
        int gradeA = 0, gradeB = 0, gradeC = 0, gradeD = 0, gradeE = 0;
        double totalNilai = 0;
        
        String namaLulus = "";
        String namaTidakLulus = "";
        String namaGradeA = "";
        String namaGradeB = "";
        String namaGradeD = "";
        String rumusRataRata = "";

        // Tampilkan data per mahasiswa satu per satu
        for (int i = 0; i < daftarMahasiswa.size(); i++) {
            HitungNilai m = daftarMahasiswa.get(i);
            String grade = m.getGrade();

            System.out.println("NIM   : " + m.nim);
            System.out.println("Nama  : " + m.nama);
            System.out.println("Nilai : " + m.nilai);
            System.out.println("Grade : " + grade);
            System.out.println("=========================================");

            // Akumulasi nilai rata-rata
            totalNilai += m.nilai;
            if (i == 0) {
                rumusRataRata += m.nilai;
            } else {
                rumusRataRata += "+" + m.nilai;
            }

            // Filter kelulusan
            if (m.isLulus()) {
                jumlahLulus++;
                namaLulus += (namaLulus.isEmpty() ? "" : ", ") + m.nama;
            } else {
                jumlahTidakLulus++;
                namaTidakLulus += (namaTidakLulus.isEmpty() ? "" : ", ") + m.nama;
            }

            // Hitung statistik per grade
            if (grade.equals("A")) {
                gradeA++;
                namaGradeA += (namaGradeA.isEmpty() ? "" : ", ") + m.nama;
            } else if (grade.equals("B")) {
                gradeB++;
                namaGradeB += (namaGradeB.isEmpty() ? "" : ", ") + m.nama;
            } else if (grade.equals("C")) {
                gradeC++;
            } else if (grade.equals("D")) {
                gradeD++;
                namaGradeD += (namaGradeD.isEmpty() ? "" : ", ") + m.nama;
            } else if (grade.equals("E")) {
                gradeE++;
            }
        }

        // Tampilkan Ringkasan Jumlah dan Statistik Akhir
        int totalMhs = daftarMahasiswa.size();
        double rataRata = totalMhs > 0 ? totalNilai / totalMhs : 0;

        System.out.println("Jumlah Mahasiswa : " + totalMhs);
        System.out.println("Jumlah Mahasiswa yg Lulus : " + jumlahLulus + " yaitu " + (namaLulus.isEmpty() ? "-" : namaLulus));
        System.out.println("Jumlah Mahasiswa yg Tidak Lulus : " + jumlahTidakLulus + " yaitu " + (namaTidakLulus.isEmpty() ? "-" : namaTidakLulus));
        System.out.println("Jumlah Mahasiswa dengan Nilai A = " + gradeA + " yaitu " + (namaGradeA.isEmpty() ? "-" : namaGradeA));
        System.out.println("Jumlah Mahasiswa dengan Nilai B = " + gradeB + " yaitu " + (namaGradeB.isEmpty() ? "-" : namaGradeB));
        System.out.println("Jumlah Mahasiswa dengan Nilai D = " + gradeD + " yaitu " + (namaGradeD.isEmpty() ? "-" : namaGradeD));
        System.out.println("Rata-rata nilai mahasiswa adalah : " + rumusRataRata + " / " + totalMhs + " = " + rataRata);
        
        input.close();
    }
}