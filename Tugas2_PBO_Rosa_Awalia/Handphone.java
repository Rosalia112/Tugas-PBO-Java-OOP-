public class Handphone {
    String jenis_hp;
    int tahun_pembuatan;

    public void setDataHp(String jenis_hp, int tahun_pembuatan){
        this.jenis_hp = jenis_hp;
        this.tahun_pembuatan = tahun_pembuatan;
    }

    String getJenisHp(){
        return this.jenis_hp;
    }

    int getTahunPembuatan(){
        return this.tahun_pembuatan;
    }

    public static void main(String[] args) {
        Handphone hp1 = new Handphone();
        hp1.setDataHp("Samsung", 2021);
        System.out.println("Jenis HP: " + hp1.getJenisHp());
        System.out.println("Tahun Pembuatan: " + hp1.getTahunPembuatan());
    }
}


