package model;

// Class Induk
public abstract class ModelData {
    protected int id; // Enkapsulasi protected

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    // Polimorfisme: Metode yang wajib di-override anak kelasnya
    public abstract String getInfo(); 
}