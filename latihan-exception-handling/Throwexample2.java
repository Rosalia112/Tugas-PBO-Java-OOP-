public class Throwexample2 {
    public static void main(String[] args) {
        try {
            // membuat 'aturan' baru
            throw new Exception("Here's my exception");
        }
        // exception dibuat dan langsung sieksekusi, dan catch akan menghandle error tersebut
        catch (Exception e) {
            System.out.println("Caught an exception: ");
            System.out.println("e.getMessage():" + e.getMessage());
            System.out.println("e.toString():" + e.toString());
            System.out.println("e.printStackTrace():");
            e.printStackTrace();
        }
    }
}
