package model;

public class Battler extends Character {
    private String rarity;

    public Battler(int idChar, String name, String role, int level, int hp, int attack, int def, boolean isActive, String rarity) {
        super(idChar, name, role, level, hp, attack, def, isActive);
        this.rarity = rarity;
    }

    public String getRarity() { return rarity; }

    @Override
    public void displayInfo() {
        System.out.print("[" + rarity + "] ");
        super.displayInfo();
    }
}