// Simpan dengan nama file: Mahasiswa.java

public class Mahasiswa {
    // memakai 'protected' agar bisa diakses langsung oleh sub class nanti
    protected String nim;
    protected String nama;
    protected int nilai;

    // Ini adalah constructor untuk menginisialisasi data saat objek dibuat
    public Mahasiswa(String nim, String nama, int nilai) {
        this.nim = nim;
        this.nama = nama;
        this.nilai = nilai;
    }
}