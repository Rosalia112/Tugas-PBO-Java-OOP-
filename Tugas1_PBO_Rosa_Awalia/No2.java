import javax.swing.JOptionPane;
public class No2 {
    public static void main(String[] args) {
        // Mengambil input
        String pelajaran = JOptionPane.showInputDialog("Anda sedang belajar apa?");
        
        // Menampilkan output (menggabungkan teks)
        JOptionPane.showMessageDialog(null, "Belajar " + pelajaran + " sangat mudah");
    }
    
}
