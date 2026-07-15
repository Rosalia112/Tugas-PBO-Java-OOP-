// File 1: Test3.java
import java.io.*; // Mengimpor seluruh class di paket java.io (seperti IOException) agar bisa digunakan di kode ini

public class Test3 {
    
    // Method biasa tanpa penanganan exception khusus
    public void methodA(){
        System.out.println("Method A"); 
    }
    
    /**
     * Keyword 'throws IOException' di sini berfungsi untuk mendeklarasikan bahwa methodB berpotensi menghasilkan error berjenis IOException.
     * Ini memaksa siapa pun yang memanggil methodB() untuk bersiap menangkap error tersebut.
     */
    public void methodB() throws IOException 
    {
        //Baris di bawah ini secara matematis memicu ArithmeticException (pembagian dengan nol),
        //bukan IOException. Saat dijalankan, program akan langsung crash di baris ini!
        System.out.println(20/0); 
        
        // Baris ini tidak akan pernah dieksekusi karena program sudah crash di baris sebelumnya.
        System.out.println("Method B"); 
    }
}

//class Utama
//{
    /*
     * Karena di dalam main() kita memanggil methodB() yang memiliki label 'throws IOException',
     * maka main() juga wajib ikut menangani atau mendeklarasikan ulang dengan 'throws IOException'.
     * Dengan begini, tanggung jawab penanganan error dioper ke Java Virtual Machine (JVM).
     
    public static void main(String[] args) throws IOException 
    {
        // Membuat objek 'c' dari class Test3 agar method di dalamnya bisa dipanggil
        Test3 c = new Test3();
        
        // Memanggil methodA(), akan berjalan normal dan mencetak "Method A"
        c.methodA();
        
        // Memanggil methodB(). Karena ada pembagian dengan nol di dalam methodB(),
        // program akan melempar ArithmeticException dan langsung berhenti total di sini.
        c.methodB();
    }
} */

// File 2: Utama.java (Running Class)
class Utama
{
    public static void main(String[] args) throws IOException 
    {
        // Membuat objek 'o' dari class Test3 agar method di dalamnya bisa dipanggil
        Test3 o = new Test3();
        //method a tidak akan error, maka tidak perlu try catch
        //Memanggil methodA(), akan berjalan normal dan mencetak "Method A"
        o.methodA();
        try {
            //method b akan error, karena ada pembagian dengan nol.
            o.methodB();
        } catch(Exception e){
            System.out.println("Error di method B");
        }
        //finally akan selalu dieksekusi, baik terjadi error maupun tidak.
        finally {
            System.out.println("Ini selalu dicetak");
        }
    }
}
