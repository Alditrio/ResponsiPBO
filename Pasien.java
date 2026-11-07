/**
 * Abstract Class Pasien (Superclass)
 * 
 * Merepresentasikan entitas pasien umum di rumah sakit.
 * Memenuhi Persyaratan:
 * - Persyaratan 1: State dan Behavior (variabel, prosedur void, fungsi return value)
 * - Persyaratan 2: Immutability (public static final dan private final)
 * - Persyaratan 3: Enkapsulasi dan Overloading (private, getter/setter, keyword this, overloaded constructor)
 * - Persyaratan 4: Inheritance (bertindak sebagai superclass)
 * - Persyaratan 7: Abstract Class (status abstract dan memiliki abstract method)
 */
public abstract class Pasien {

    // =========================================================================
    // PERSYARATAN 2: IMMUTABILITY (Konstanta Global: public static final)
    // =========================================================================
    public static final String NAMA_RUMAH_SAKIT = "RSUD Margono Sehat Soedirman";
    public static final double TARIF_PAJAK_PPN = 0.11; // PPN 11%

    // =========================================================================
    // PERSYARATAN 1 & 3: STATE & ENKAPSULASI (Seluruh atribut berstatus private)
    // PERSYARATAN 2: IMMUTABILITY (Atribut instans kekal: private final)
    // =========================================================================
    private final String noRekamMedis; // Atribut instans kekal (tidak dapat diubah setelah inisialisasi)
    private String nama;
    private int usia;
    private String statusPasien;
    private double biayaDasar;

    // =========================================================================
    // PERSYARATAN 3: OVERLOADED CONSTRUCTOR (Variasi Konstruktor 1)
    // Inisialisasi ringkas dengan nilai default untuk status dan biaya dasar
    // Menggunakan keyword 'this'
    // =========================================================================
    public Pasien(String noRekamMedis, String nama, int usia) {
        this(noRekamMedis, nama, usia, "Pendaftaran Baru", 50000.0);
    }

    // =========================================================================
    // PERSYARATAN 3: OVERLOADED CONSTRUCTOR (Variasi Konstruktor 2)
    // Inisialisasi lengkap seluruh parameter atribut
    // Menggunakan keyword 'this'
    // =========================================================================
    public Pasien(String noRekamMedis, String nama, int usia, String statusPasien, double biayaDasar) {
        this.noRekamMedis = noRekamMedis;
        this.nama = nama;
        this.usia = usia;
        this.statusPasien = statusPasien;
        this.biayaDasar = biayaDasar;
    }

    // =========================================================================
    // PERSYARATAN 1 & 5: METHOD PROSEDUR (void)
    // Menampilkan informasi dasar pasien (akan dipanggil oleh subclass via super.tampilkanInformasi())
    // =========================================================================
    public void tampilkanInformasi() {
        System.out.println("==================================================");
        System.out.println("Instansi            : " + NAMA_RUMAH_SAKIT);
        System.out.println("No. Rekam Medis     : " + this.noRekamMedis + " (Immutable)");
        System.out.println("Nama Pasien         : " + this.nama);
        System.out.println("Usia Pasien         : " + this.usia + " tahun");
        System.out.println("Status Pasien       : " + this.statusPasien);
        System.out.println("Biaya Pendaftaran   : Rp " + String.format("%,.2f", this.biayaDasar));
    }

    // =========================================================================
    // PERSYARATAN 1: METHOD FUNGSI (return value)
    // Mengembalikan ringkasan identitas pasien dalam format String
    // =========================================================================
    public String getRingkasanPasien() {
        return "[" + this.noRekamMedis + "] " + this.nama + " (" + this.usia + " thn) - Status: " + this.statusPasien;
    }

    // =========================================================================
    // PERSYARATAN 7: ABSTRACT METHOD
    // Wajib diimplementasikan oleh setiap subclass dengan logika spesifik
    // =========================================================================
    /**
     * Memproses pemeriksaan berdasarkan kondisi/urgensi pasien.
     * Implementasi di subclass memuat logika percabangan if/else (Persyaratan 8).
     * @param keluhanAtauKondisi catatan keluhan atau tingkat urgensi medis
     */
    public abstract void prosesPemeriksaan(String keluhanAtauKondisi);

    /**
     * Menghitung total keseluruhan biaya yang harus dibayarkan pasien.
     * @return nominal total biaya
     */
    public abstract double hitungTotalBiaya();

    // =========================================================================
    // PERSYARATAN 3: GETTER DAN SETTER (Enkapsulasi dengan keyword this)
    // =========================================================================
    public String getNoRekamMedis() {
        return this.noRekamMedis;
    }
    // Catatan: noRekamMedis tidak memiliki setter publik karena berstatus 'final' (Immutability).

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUsia() {
        return this.usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getStatusPasien() {
        return this.statusPasien;
    }

    public void setStatusPasien(String statusPasien) {
        this.statusPasien = statusPasien;
    }

    public double getBiayaDasar() {
        return this.biayaDasar;
    }

    public void setBiayaDasar(double biayaDasar) {
        this.biayaDasar = biayaDasar;
    }
}
