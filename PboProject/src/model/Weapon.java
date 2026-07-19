package model;

public class Weapon {
    private int wp_id;
    private int id_char;
    private String wp_name;
    private int wp_attack;

    public Weapon(int wp_id, int id_char, String wp_name, int wp_attack) {
        this.wp_id = wp_id;
        this.id_char = id_char;
        this.wp_name = wp_name;
        this.wp_attack = wp_attack;
    }

    // Getter dan Setter
    public int getIdWeapon() { return wp_id; }
    public int getIdChar() { return id_char; }
    public String getNameWeapon() { return wp_name; }
    public int getAttackBonus() { return wp_attack; }
}