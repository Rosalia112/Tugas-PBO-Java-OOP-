public class Exception1 {
    public static void main(String[] args) {
        // Kita menulis array sampe 5 (bebrarti indeks hanya sampai no 4)
        int a[] = new int[5];
        try
        {
            // disini kita mengisi array dengan 5, yang berrati indeks 6
            // maka codingan akan error karena kita hanya menulis array sampai 5
            a[5] = 100;
        }
        catch(Exception e)
        {
            System.out.println("Terjadi pelanggaran memory!!");
        }   
    }
}

