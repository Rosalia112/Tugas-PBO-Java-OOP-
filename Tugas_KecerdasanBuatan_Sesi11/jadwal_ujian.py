import random

# =====================================================================
# 1. DEFINISI DATA & PARAMETER
# =====================================================================
MATA_UJIAN = ["Machine Learning", "Kecerdasan Buatan", "Analisis Perangkat Lunak", "RPL"]
RUANGAN = ["Ruang A", "Ruang B"]
SESI = ["Sesi 1", "Sesi 2"]

UKURAN_POPULASI = 10
PROBABILITAS_MUTASI = 0.2
MAKS_GENERASI = 100

# =====================================================================
# 2. FUNGSI UTAMA ALGORITMA GENETIKA
# =====================================================================

def buat_kromosom():
    """Inisialisasi 1 solusi jadwal secara acak."""
    kromosom = []
    for _ in range(len(MATA_UJIAN)):
        ruang_acak = random.choice(RUANGAN)
        sesi_acak = random.choice(SESI)
        kromosom.append((ruang_acak, sesi_acak))
    return kromosom

def hitung_fitness(kromosom):
    """Menghitung kelayakan jadwal (mengurangi nilai jika ada bentrok)."""
    bentrok = 0
    terjadwal = []
    for gen in kromosom:
        if gen in terjadwal:
            bentrok += 1
        else:
            terjadwal.append(gen)
    return 1.0 / (1.0 + bentrok)

def seleksi_tournament(populasi, nilai_fitness):
    """Memilih induk terbaik dari 3 kandidat acak."""
    peserta = random.sample(list(zip(populasi, nilai_fitness)), 3)
    peserta_terbaik = max(peserta, key=lambda x: x[1])
    return peserta_terbaik[0]

def crossover(induk1, induk2):
    """Menukar setengah formasi jadwal antara dua induk."""
    titik_potong = len(induk1) // 2
    anak1 = induk1[:titik_potong] + induk2[titik_potong:]
    anak2 = induk2[:titik_potong] + induk1[titik_potong:]
    return anak1, anak2

def mutasi(kromosom):
    """Mengubah acak ruang/sesi berdasarkan probabilitas mutasi."""
    if random.random() < PROBABILITAS_MUTASI:
        indeks_acak = random.randint(0, len(kromosom) - 1)
        kromosom[indeks_acak] = (random.choice(RUANGAN), random.choice(SESI))
    return kromosom

def cetak_jadwal(id_generasi, kromosom, fitness):
    """Menampilkan output hasil susunan jadwal ke terminal."""
    print(f"\n=======================================================")
    print(f" HASIL OPTIMAL PADA GENERASI KE-{id_generasi} (Fitness: {fitness:.2f})")
    print(f"=======================================================")
    for i, mata_kuliah in enumerate(MATA_UJIAN):
        ruang, sesi = kromosom[i]
        print(f" -> {mata_kuliah:<12} : {ruang} | {sesi}")
    print(f"=======================================================")

# =====================================================================
# 3. PROSES EVOLUSI (MAIN LOOP)
# =====================================================================

print("[SISTEM] Memulai proses pencarian jadwal dengan Algoritma Genetika...")

# Buat populasi awal
populasi = [buat_kromosom() for _ in range(UKURAN_POPULASI)]
solusi_ditemukan = False

for generasi in range(1, MAKS_GENERASI + 1):
    nilai_fitness = [hitung_fitness(ind) for ind in populasi]
    
    fitness_tertinggi = max(nilai_fitness)
    indeks_terbaik = nilai_fitness.index(fitness_tertinggi)
    kromosom_terbaik = populasi[indeks_terbaik]
    
    # Tampilkan log pencarian tiap generasi di terminal
    print(f"-> Generasi {generasi}: Fitness Terbaik = {fitness_tertinggi:.2f}")
    
    # Jika fitness mencapai 1.0 (Artinya 0 bentrok, jadwal sempurna ketemu)
    if fitness_tertinggi == 1.0:
        cetak_jadwal(generasi, kromosom_terbaik, fitness_tertinggi)
        print("\n[INFO] Sukses! Jadwal ujian optimal tanpa bentrok telah ditemukan.")
        solusi_ditemukan = True
        break
        
    # Regenerasi populasi jika belum optimal
    populasi_baru = []
    while len(populasi_baru) < UKURAN_POPULASI:
        induk1 = seleksi_tournament(populasi, nilai_fitness)
        induk2 = seleksi_tournament(populasi, nilai_fitness)
        anak1, anak2 = crossover(induk1, induk2)
        populasi_baru.extend([mutasi(anak1), mutasi(anak2)])
        
    populasi = populasi_baru[:UKURAN_POPULASI]

# Jika sampai generasi maksimal belum juga 1.0, cetak hasil terbaik yang ada
if not solusi_ditemukan:
    nilai_fitness = [hitung_fitness(ind) for ind in populasi]
    fitness_tertinggi = max(nilai_fitness)
    indeks_terbaik = nilai_fitness.index(fitness_tertinggi)
    print("\n[INFO] Batas batas generasi tercapai. Menampilkan hasil terbaik:")
    cetak_jadwal(MAKS_GENERASI, populasi[indeks_terbaik], fitness_tertinggi)
