import java.io.*;

class RandomAccessRevisi {

    public static void main(String[] args) {
        // Menyiapkan data array berupa String dan int yang akan ditulis ke dalam file
        String bookList[] = {"Satu", "Dua", "Tiga"};
        int yearList[] = {1920, 1230, 1400}; // Di gambar tertulis 1940, sesuaikan kebutuhan

        try {
            // Membuat atau membuka file bernama "books.txt" dengan mode "rw" (read and write / baca dan tulis)
            RandomAccessFile books = new RandomAccessFile("books.txt", "rw");

            // Melakukan perulangan untuk menulis seluruh isi data array ke dalam file
            for (int i = 0; i < 3; i++) {
                // writeUTF digunakan untuk menulis data teks (String) format UTF-8
                books.writeUTF(bookList[i]);
                // writeInt digunakan untuk menulis data angka (integer) 
                books.writeInt(yearList[i]);
            }

            // Mengembalikan posisi pointer/kursor baca ke indeks 0 (awal file) agar file bisa dibaca dari awal
            books.seek(0);

            // Membaca dan menampilkan data pertama (indeks ke-0) yang ada di dalam file
            System.out.println(books.readUTF() + " " + books.readInt());
            
            // Membaca dan menampilkan data kedua (indeks ke-1) yang ada di dalam file secara berurutan
            System.out.println(books.readUTF() + " " + books.readInt());
            
            books.close();

        } catch (IOException e) {
            // Menangkap error jika terjadi kegagalan proses Input/Output (misal file tidak ditemukan atau hak akses ditolak)
            System.out.println("Indeks melebihi batas");
        }
        
        System.out.println("test");
    }
}