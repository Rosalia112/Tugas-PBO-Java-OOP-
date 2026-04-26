class atributeChar{
    // Data Member
    String NameChar;
    int LevelChar;

    atributeChar(String NameChar, int LevelChar){
        this.NameChar = NameChar;
        this.LevelChar = LevelChar;
    }

    //Method tanpa return
    void shower(){
        System.out.println("Nama Karakter: " + this.NameChar);
        System.out.println("Level Karakter: " + this.LevelChar);
    }

    void ChangeShower(){
        System.out.println("Nama Karakter Setelah diubah: " + this.NameChar);
        System.out.println("Level Karakter Setelah Ditingkatkan: " + this.LevelChar);
    }

    //Method tanpa return dengan parameter
    //Method ini bisa digunakan untuk mengubah value data class diatas
    //Bisa disebut Setter
    void setNameChar(String NameChar){
        this.NameChar = NameChar;
    }

    void setLevelChar(int LevelChar){
        this.LevelChar = LevelChar;
    }

    //Method dengan return tapi tidak ada parameter
    //Bisa disebut getter
    String getNameChar(){
        return this.NameChar;
    }
    int getLevelChar(){
        return this.LevelChar;
    }

    //Method dengan return dan parameter
    int UpLevelChar(int addLevel){
        return addLevel + this.LevelChar;
    }
}

public class jenis_jenis_method {
    public static void main(String[] args) {
        atributeChar atributeChar1 = new atributeChar("Igor", 10);
        // memanggil method
        atributeChar1.shower();
        atributeChar1.setNameChar("Getian");
        atributeChar1.setLevelChar(30);
        atributeChar1.ChangeShower();
        System.out.println(atributeChar1.getNameChar());
        System.out.println(atributeChar1.getLevelChar());
        int DataLevelChar = atributeChar1.UpLevelChar(10);
        System.out.println("Level " + atributeChar1.NameChar + " setelah diupgrade = " + DataLevelChar);
    }


}
