// Class sebelum dibuah
// memililiki output gnirts a si sihT
// All done

/*class Propagate {

    public static void main(String[] args) {
        try {
            System.out.println(reverse("This is a string"));
        } catch (Exception e) {
            System.out.println("The String was blank");
        } finally {
            System.out.println("All done");
        }
    }

    public static String reverse(String s) throws Exception {
        if (s.length() == 0) {
            throw new Exception();
        }
        String reverseStr = "";
        for (int i = s.length() - 1; i >= 0; --i) {
            reverseStr += s.charAt(i);
        }
        return reverseStr;
    }

    
}
*/

class Propagate {

    public static void main(String[] args) {
        try {
            // Isi string di bawah ini dikosongkan 
            System.out.println(reverse(""));
        } catch (Exception e) {
            System.out.println("The String was blank");
        } finally {
            System.out.println("All done");
        }
    }

    public static String reverse(String s) throws Exception {
        // Karena s.length() == 0, blok ini akan memicu Exception
        if (s.length() == 0) {
            throw new Exception();
        }
        String reverseStr = "";
        for (int i = s.length() - 1; i >= 0; --i) {
            reverseStr += s.charAt(i);
        }
        return reverseStr;
    }
}
