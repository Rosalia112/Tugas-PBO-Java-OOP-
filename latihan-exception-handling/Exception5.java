public class Exception5 {
    public static void main(String[] args) {
        int bil = 10;
        try
        {
            System.out.println(bil/0);
        }
        catch(ArithmeticException e)
        {
            System.out.println("Pesan Error:");
            //mengambil pesan singkat dari si e(ArithmeticExecption)
            System.out.println(e.getMessage());
            System.out.println("Info Stack erase:");
            //mengambil pesan yang lebih rinci dari si e(ArithmeticExecption)
            //trace(jejak) dari error yang terjadi
            e.printStackTrace();
            e.printStackTrace(System.out);
        }
        catch(Exception e)
        {
            System.out.println("Ini menghandle error yang terjadi");
        }
    }
}
