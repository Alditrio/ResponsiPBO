# Sistem Manajemen Pasien Rumah Sakit
**Responsi Akhir Praktikum Pemrograman Berorientasi Objek (PBO)**  
**Jurusan Informatika - Universitas Jenderal Soedirman**

---

## 1. Identitas Mahasiswa & Tema

- **Nama** : Alditrio Prabowo
- **NIM** : [Isi NIM Lengkap - Akhiran 7]
- **Tema** : Rumah Sakit (NIM Akhiran 6 dan 7)

---

## 2. Struktur Class

- **`Pasien.java`** : Abstract superclass (berisi konstanta global, atribut final, method abstract).
- **`PasienRawatInap.java`** : Subclass rawat inap (`extends Pasien implements LayananMedis, Pembayaran`).
- **`PasienRawatJalan.java`** : Subclass rawat jalan (`extends Pasien implements Pembayaran`).
- **`LayananMedis.java`** : Interface layanan medis.
- **`Pembayaran.java`** : Interface pembayaran & cetak kuitansi.
- **`Main.java`** : Class pengujian sistem.

---

## 3. Pemenuhan 10 Persyaratan Teknis

1. **State dan Behavior** : Terdapat atribut variabel, method prosedur `void` (`tampilkanInformasi()`, `cetakKuitansi()`), dan method fungsi return value (`hitungTotalBiaya()`).
2. **Immutability** : Konstanta global `public static final String NAMA_RUMAH_SAKIT` & atribut instans kekal `private final String noRekamMedis`.
3. **Enkapsulasi dan Overloading** : Seluruh atribut `private` dengan getter/setter, pemakaian keyword `this`, serta 2 variasi konstruktor (*overloaded constructor*).
4. **Inheritance** : Superclass `Pasien` diturunkan ke `PasienRawatInap` dan `PasienRawatJalan`. Class `Main` dipisah.
5. **Super Chaining** : Pemanggilan `super(...)` pada konstruktor subclass dan `super.tampilkanInformasi()` pada method penimpa.
6. **Method Overriding** : Menggunakan anotasi `@Override` pada penimpaan method di subclass.
7. **Abstract Class** : Class `Pasien` berstatus `abstract` dan memiliki abstract method `prosesPemeriksaan(String keluhan)` serta `hitungTotalBiaya()`.
8. **Logika State Percabangan** : Implementasi `prosesPemeriksaan()` memuat `if/else` yang membaca dan mengubah state internal objek (`statusPasien`, `butuhPerawatKhusus`, `biayaDasar`).
9. **Polimorfisme Interface** : Pemanggilan method objek melalui variabel referensi bertipe interface (`LayananMedis layanan = new PasienRawatInap(...);`).
10. **Multiple Interface** : `PasienRawatInap` mengimplementasikan dua interface sekaligus (`implements LayananMedis, Pembayaran`).

---

## 4. Cara Compile dan Menjalankan Program

Buka terminal pada direktori proyek, lalu jalankan:

```powershell
javac *.java
java Main
```

---

## 5. Luaran Program (Screenshot Output)

![Screenshot Luaran Program](screenshot.png)

```text
======================================
Rumah Sakit       : RS Sehat Sentosa
No. Rekam Medis   : RM001
Nama Pasien       : Budi Santoso
Usia              : 45 tahun
Status            : Pendaftaran Baru
Biaya Pendaftaran : Rp 50000.0
Tipe Layanan      : Rawat Inap
Kamar             : Melati-01
Lama Menginap     : 3 hari
Tarif Kamar/Hari  : Rp 300000.0
Perawat Khusus    : Tidak
Catatan           : Rawat inap reguler
======================================
======================================
Rumah Sakit       : RS Sehat Sentosa
No. Rekam Medis   : RM003
Nama Pasien       : Ahmad Fauzi
Usia              : 25 tahun
Status            : Pendaftaran Baru
Biaya Pendaftaran : Rp 50000.0
Tipe Layanan      : Rawat Jalan
Poli              : Poli Umum
Biaya Konsultasi  : Rp 80000.0
Resep Obat        : -
Biaya Obat        : Rp 0.0
======================================

Status Pasien 1 setelah diperiksa: ICU
Status Pasien 3 setelah diperiksa: Selesai Pemeriksaan

---------- KUITANSI RAWAT INAP ----------
Pasien            : Budi Santoso (RM001)
Total Tagihan     : Rp 2830500.0
Status            : LUNAS
-----------------------------------------
---------- KUITANSI RAWAT JALAN ---------
Pasien            : Ahmad Fauzi (RM003)
Poli              : Poli Umum
Total Tagihan     : Rp 199800.0
Status            : LUNAS
-----------------------------------------

Tindakan medis untuk Dewi Lestari: Pemberian Terapi Injeksi & Infus
Detail: Rawat Inap Mawar-03 - Pemberian Terapi Injeksi & Infus
Total Bayar via Interface: Rp 144300.0
```

---

## 6. Ringkasan Tanya Jawab Defense (Persiapan Ujian 70%)

- **Mengapa noRekamMedis dibuat private final?**  
  Agar nilainya kekal setelah objek dibuat (*immutable*), karena nomor rekam medis tidak boleh diubah-ubah demi integritas data medis pasien.
- **Mengapa Pasien adalah abstract class?**  
  Karena pasien umum tidak dapat diinstansiasi langsung tanpa kejelasan status rawat inap atau rawat jalan.
- **Bagaimana super chaining bekerja?**  
  `super(...)` memanggil konstruktor milik superclass untuk inisialisasi atribut dasar, dan `super.tampilkanInformasi()` memanggil implementasi method di superclass agar tidak perlu menulis ulang logika cetak data dasar.
- **Apa itu polimorfisme interface?**  
  Mengakses method objek melalui variabel bertipe interface (`LayananMedis layanan = new PasienRawatInap(...)`), sehingga yang terlihat hanya kontrak method dari interface tersebut.
