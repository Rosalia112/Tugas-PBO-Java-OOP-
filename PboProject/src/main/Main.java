package main;

import daoo.CharacterDAO;
import daoo.WeaponDAO; 
import java.util.List;
import java.util.Scanner;
import model.Character;

public class Main {
    public static void main(String[] args) {
        CharacterDAO charDAO = new CharacterDAO();
        WeaponDAO weapDAO = new WeaponDAO(); 
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== GAME CHARACTER COLLECTION CLI ===");
            System.out.println("1. Menu Karakter");
            System.out.println("2. Menu Upgrade");
            System.out.println("3. Menu Statistik / Keluar");
            System.out.print("Pilih Menu Utama (1-3): ");
            int pilihanUtama = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (pilihanUtama) {
                case 1:
                    boolean subMenu1 = true;
                    while (subMenu1) {
                        System.out.println("\n--- MENU KARAKTER ---");
                        System.out.println("1) Tambah Karakter");
                        System.out.println("2) Lihat Daftar Karakter");
                        System.out.println("3) Kembali");
                        System.out.print("Pilih opsi: ");
                        int opsi = scanner.nextInt();
                        scanner.nextLine();

                        if (opsi == 1) {
                            System.out.print("Nama Karakter: ");
                            String name = scanner.nextLine();
                            System.out.print("Role (Warrior/Mage/Archer): ");
                            String role = scanner.nextLine();
                            System.out.print("HP Awal: ");
                            int hp = scanner.nextInt();
                            System.out.print("ATK Awal: ");
                            int atk = scanner.nextInt();
                            System.out.print("DEF Awal: ");
                            int def = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            
                            // Menambahkan karakter baru (Level 1, Status aktif = true)
                            Character newChar = new Character(0, name, role, 1, hp, atk, def, true);
                            charDAO.addCharacter(newChar);
                        } else if (opsi == 2) {
                            System.out.println("\n--- DAFTAR KARAKTER ---");
                            List<Character> daftar = charDAO.getAllCharacters();
                            if (daftar.isEmpty()) {
                                System.out.println("Belum ada karakter di database.");
                            } else {
                                for (Character c : daftar) {
                                    c.displayInfo(); // Polymorphism
                                }
                            }
                        } else if (opsi == 3) {
                            subMenu1 = false;
                        }
                    }
                    break;
                    
                case 2:
                    boolean subMenu2 = true;
                    while (subMenu2) {
                        System.out.println("\n--- MENU UPGRADE ---");
                        System.out.println("1) Naikkan Level Karakter");
                        System.out.println("2) Ganti Senjata Karakter");
                        System.out.println("3) Kembali");
                        System.out.print("Pilih opsi: ");
                        int opsi = scanner.nextInt();
                        scanner.nextLine();

                        if (opsi == 1) {
                            System.out.println("[Fitur Naik Level sedang disiapkan]");
                        } else if (opsi == 2) {
                            System.out.println("\n--- GANTI SENJATA ---");
                            // Tampilkan list karakter dulu biar user tahu ID-nya
                            List<Character> daftar = charDAO.getAllCharacters();
                            if (daftar.isEmpty()) {
                                System.out.println("Belum ada karakter untuk dipasangkan senjata.");
                                subMenu2 = false;
                                break;
                            }
                            
                            for (Character c : daftar) {
                                System.out.printf("ID: %d | Nama: %s\n", c.getIdChar(), c.getName());
                            }
                            
                            System.out.print("\nMasukkan ID Karakter yang ingin diganti senjatanya: ");
                            int id_char = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            
                            System.out.print("Masukkan Nama Senjata Baru: ");
                            String wp_name = scanner.nextLine();
                            
                            System.out.print("Masukkan ATK Bonus Senjata: ");
                            int wp_attack = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            
                            // Eksekusi method changeWeapon dari WeaponDAO kamu
                            weapDAO.changeWeapon(id_char, wp_name, wp_attack);
                            
                        } else if (opsi == 3) {
                            subMenu2 = false;
                        }
                    }
                    break;
                    
                case 3:
                    System.out.println("Keluar program. Terima kasih!");
                    running = false;
                    break;
                    
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}