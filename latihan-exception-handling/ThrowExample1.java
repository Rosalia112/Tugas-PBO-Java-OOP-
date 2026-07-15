public class ThrowExample1 {
    static void demo(){
        NullPointerException t;
        // throw ddibuat untuk memaksa error terjadi(dalam kasus nyata kita bisa membust error/aturan sendiri)
        // sehingga kita bisa menghandle error tersebut
        t = new NullPointerException("Coba Throw");
        throw t;
        //Baris ini tidak akan dieksekusi karena throw akan menghentikan eksekusi method demo()
        // System.out.println("Ini tidak akan dicetak");
    }

    public static void main(String[] args) {
        try
        {
            demo();
            System.out.println("Selesai");
        }
        catch(NullPointerException e)
        {
            System.out.println("Ada pesan eerror: " + e);
        }
    }
}
