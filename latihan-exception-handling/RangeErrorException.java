// Membuat custom exception dengan mewarisi (extends) Throwable.
// Throwable adalah induk tertinggi dari semua exception dan error di Java.
class RangeErrorException extends Throwable {
    
    // Constructor, untuk menerima pesan error kustom (String s)
    public RangeErrorException(String s) {
        // super(s) meneruskan pesan error ke constructor Throwable agar bisa dibaca lewat e.getMessage()
        super(s);
    }
}

class MainApp { // Dibungkus class utama agar bisa dijalankan

    public static void main(String[] args) {
        int position = 1;
        
        try {
            // Mengecek kondisi, jika posisi lebih dari 0 maka exception sengaja dipicu
            if (position > 0) {
                // Keyword 'throw new' digunakan untuk melempar/memicu secara paksa objek exception ke runtime
                throw new RangeErrorException("Position " + position);
            }
            
        } catch (RangeErrorException e) {
            // e.getMessage() otomatis mengambil teks "Position 1" yang kita kirim lewat 'super(s)' di atas tadi
            System.out.println("Range error: " + e.getMessage());
        }
        
        // Tetap dieksekusi normal karena exception di blok try sudah berhasil ditangani oleh catch
        System.out.println("This is the last program.");
    }
}