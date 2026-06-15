public class HitungNilai extends Mahasiswa {
    
    // Constructor subclass harus memanggil constructor superclass/parent class menggunakan 'super'
    public HitungNilai(String nim, String nama, int nilai) {
        super(nim, nama, nilai);
    }

    // Method untuk menentukan Grade berdasarkan ketentuan b sampai g
    public String getGrade() {
        if (this.nilai < 0 || this.nilai > 100) {
            return "Input nilai anda salah";
        } else if (this.nilai >= 80) {
            return "A";
        } else if (this.nilai >= 70) {
            return "B";
        } else if (this.nilai >= 60) {
            return "C";
        } else if (this.nilai >= 50) {
            return "D";
        } else {
            return "E";
        }
    }

    // Method untuk mengecek status kelulusan
    public boolean isLulus() {
        String grade = getGrade();
        // Grade A, B, dan C dinyatakan lulus berdasarkan soal
        return grade.equals("A") || grade.equals("B") || grade.equals("C");
    }
}