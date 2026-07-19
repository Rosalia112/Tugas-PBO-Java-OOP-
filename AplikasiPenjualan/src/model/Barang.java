package model;

public class Barang extends ModelData {
    private String namaBarang;
    private int harga;
    private int stok;

    public Barang(String namaBarang, int harga, int stok) {
        this.namaBarang = namaBarang;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter (Enkapsulasi)
    public String getNamaBarang() { return namaBarang; }
    public void setNamaBarang(String namaBarang) { this.namaBarang = namaBarang; }
    public int getHarga() { return harga; }
    public void setHarga(int harga) { this.harga = harga; }
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    @Override
    public String getInfo() {
        return "Barang: " + namaBarang + " | Harga: " + harga;
    }
}