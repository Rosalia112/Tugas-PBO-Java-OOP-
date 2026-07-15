public class Exception3 {
    public static void main(String[] args) {
        int bil = 10;
        try {
            // sistem tidak bisa membagi bilangan dengan nol, maka akan terjadi error
            System.out.println(bil/0);
        }
        catch(ArithmeticException e) {
            System.out.println("Terjadi aritmatika error!!");
        }
        catch(Exception e) {
            System.out.println("Ini menghandle error yang terjadi");
        }
    }
}
