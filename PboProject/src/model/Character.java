package model;

public class Character {
    private int id_char;
    private String name;
    private String role;
    private int level;
    private int hp;
    private int attack;
    private int def;
    private boolean isActive;

    public Character(int idChar, String name, String role, int level, int hp, int attack, int def, boolean isActive) {
        this.id_char = idChar;
        this.name = name;
        this.role = role;
        this.level = level;
        this.hp = hp;
        this.attack = attack;
        this.def = def;
        this.isActive = isActive;
    }

    // Getters dan Setters (Encapsulation)
    public int getIdChar() { return id_char; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public int getLevel() { return level; }
    public int getHp() { return hp; }
    public int getAttack() { return attack; }
    public int getDef() { return def; }
    public boolean isActive() { return isActive; }

    public void displayInfo() {
        System.out.printf("[%d] %s (Role: %s) | Lvl: %d | HP: %d | ATK: %d | DEF: %d | Status: %s\n",
                id_char, name, role, level, hp, attack, def, (isActive ? "Aktif" : "Non-Aktif"));
    }
}