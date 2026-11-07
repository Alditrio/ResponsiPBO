/**
 * Subclass PasienRawatJalan
 * 
 * Merepresentasikan pasien poliklinik / rawat jalan yang tidak menginap.
 * Memenuhi Persyaratan:
 * - Persyaratan 1: State dan Behavior
 * - Persyaratan 3: Enkapsulasi & Overloaded Constructor
 * - Persyaratan 4: Inheritance (extends Pasien)
 * - Persyaratan 5: Super Chaining (super(...) dan super.tampilkanInformasi())
 * - Persyaratan 6: Method Overriding (@Override)
 * - Persyaratan 8: Logika State Percabangan (if/else membaca & mengubah state internal)
 * - Persyaratan 9: Polimorfisme Interface (implements Pembayaran)
 */
public class PasienRawatJalan extends Pasien implements Pembayaran {

    // =========================================================================
    // PERSYARATAN 1 & 3: ENKAPSULASI (Atribut berstatus private)
    // =========================================================================
    private String namaPoli;
    private double biayaKonsultasiDokter;
    private String resepObat;
    private double biayaObat;

    // =========================================================================
    // PERSYARATAN 3 & 5: OVERLOADED CONSTRUCTOR 1 & SUPER CHAINING
    // Konstruktor ringkas dengan default biaya konsultasi
    // =========================================================================
    public PasienRawatJalan(String noRekamMedis, String nama, int usia, String namaPoli) {
        super(noRekamMedis, nama, usia); // Pemanggilan super(...) ke konstruktor Pasien 3 parameter
        this.namaPoli = namaPoli;
        this.biayaKonsultasiDokter = 100000.0;
        this.resepObat = "Belum Diperiksa";
        this.biayaObat = 0.0;
    }

    // =========================================================================
    // PERSYARATAN 3 & 5: OVERLOADED CONSTRUCTOR 2 & SUPER CHAINING
    // Konstruktor dengan parameter lengkap
    // =========================================================================
    public PasienRawatJalan(String noRekamMedis, String nama, int usia, String statusPasien,
                            double biayaDasar, String namaPoli, double biayaKonsultasiDokter) {
        super(noRekamMedis, nama, usia, statusPasien, biayaDasar); // Pemanggilan super(...) lengkap
        this.namaPoli = namaPoli;
        this.biayaKonsultasiDokter = biayaKonsultasiDokter;
        this.resepObat = "Belum Diperiksa";
        this.biayaObat = 0.0;
    }

    // =========================================================================
    // PERSYARATAN 5 & 6: SUPER CHAINING & METHOD OVERRIDING
    // Penimpaan method superclass dengan pemanggilan super.namaMethod()
    // =========================================================================
    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi(); // PERSYARATAN 5: Pemanggilan super.namaMethod()
        System.out.println("Tipe Pasien         : Rawat Jalan / Poliklinik");
        System.out.println("Poli Spesialis      : " + this.namaPoli);
        System.out.println("Biaya Konsultasi    : Rp " + String.format("%,.2f", this.biayaKonsultasiDokter));
        System.out.println("Resep Terapi        : " + this.resepObat);
        System.out.println("Biaya Obat/Farmasi  : Rp " + String.format("%,.2f", this.biayaObat));
        System.out.println("--------------------------------------------------");
    }

    // =========================================================================
    // PERSYARATAN 6, 7 & 8: IMPLEMENTASI ABSTRACT METHOD & LOGIKA STATE PERCABANGAN
    // Membaca dan mengubah state internal objek melalui if/else
    // =========================================================================
    @Override
    public void prosesPemeriksaan(String keluhanAtauKondisi) {
        System.out.println("\n[Proses Konsultasi Poli: " + this.namaPoli + "]");
        System.out.println("Keluhan Pasien      : " + keluhanAtauKondisi);

        // Membaca state internal objek sebelum percabangan
        String statusAwal = this.getStatusPasien();
        System.out.println("Status internal awal: " + statusAwal);

        // Logika percabangan if/else yang MEMBACA dan MENGUBAH state internal objek
        if (keluhanAtauKondisi.equalsIgnoreCase("Darurat") || keluhanAtauKondisi.equalsIgnoreCase("Sesak Akut")) {
            // Pasien mengalami pemburukan mendadak
            this.setStatusPasien("Dirujuk ke IGD Segera");
            this.biayaKonsultasiDokter += 150000.0; // Biaya tindakan emergensi dokter spesialis
            this.resepObat = "Injeksi Penstabil Saluran Nafas & Oksigenasi";
            this.biayaObat = 250000.0;
            System.out.println("-> PERUBAHAN STATE: Bahaya! Pasien dialihkan ke IGD, biaya darurat ditambahkan.");
        } else if (keluhanAtauKondisi.equalsIgnoreCase("Infeksi Berat") || keluhanAtauKondisi.equalsIgnoreCase("Perlu Obat")) {
            // Pasien butuh obat farmasi lengkap
            this.setStatusPasien("Menunggu Obat di Farmasi");
            this.resepObat = "Antibiotik Spektrum Luas + Anti-inflamasi + Vitamin C";
            this.biayaObat = 135000.0;
            System.out.println("-> PERUBAHAN STATE: Resep obat racikan dikeluarkan, pasien menuju loket farmasi.");
        } else {
            // Gejala ringan / kontrol berkala
            this.setStatusPasien("Selesai Konsultasi (Rawat Mandiri)");
            this.resepObat = "Multivitamin Daya Tahan Tubuh & Edukasi Pola Hidup";
            this.biayaObat = 45000.0;
            System.out.println("-> PERUBAHAN STATE: Kondisi terkendali, pasien cukup rawat mandiri di rumah.");
        }
    }

    // =========================================================================
    // PERSYARATAN 9: IMPLEMENTASI INTERFACE Pembayaran
    // Method fungsi (return value) dan prosedur (void)
    // =========================================================================
    @Override
    public double hitungTotalBiaya() {
        // Total kotor = biaya pendaftaran + biaya konsultasi + biaya obat
        double totalKotor = this.getBiayaDasar() + this.biayaKonsultasiDokter + this.biayaObat;
        // PPN 11% dari konstanta global superclass
        double totalPajak = totalKotor * Pasien.TARIF_PAJAK_PPN;
        return totalKotor + totalPajak;
    }

    @Override
    public void cetakKuitansi() {
        double totalKotor = this.getBiayaDasar() + this.biayaKonsultasiDokter + this.biayaObat;
        double totalPajak = totalKotor * Pasien.TARIF_PAJAK_PPN;
        double totalAkhir = hitungTotalBiaya();

        System.out.println("================== KUITANSI RAWAT JALAN =================");
        System.out.println("Instansi            : " + Pasien.NAMA_RUMAH_SAKIT);
        System.out.println("No. Rekam Medis     : " + this.getNoRekamMedis());
        System.out.println("Nama Pasien         : " + this.getNama());
        System.out.println("Poli Layanan        : " + this.namaPoli);
        System.out.println("Biaya Pendaftaran   : Rp " + String.format("%,.2f", this.getBiayaDasar()));
        System.out.println("Biaya Konsultasi    : Rp " + String.format("%,.2f", this.biayaKonsultasiDokter));
        System.out.println("Biaya Obat/Farmasi  : Rp " + String.format("%,.2f", this.biayaObat));
        System.out.println("PPN (11%)           : Rp " + String.format("%,.2f", totalPajak));
        System.out.println("---------------------------------------------------------");
        System.out.println("TOTAL DIBAYARKAN    : Rp " + String.format("%,.2f", totalAkhir));
        System.out.println("Status Pembayaran   : LUNAS");
        System.out.println("=========================================================");
    }

    // =========================================================================
    // PERSYARATAN 3: GETTER DAN SETTER
    // =========================================================================
    public String getNamaPoli() {
        return this.namaPoli;
    }

    public void setNamaPoli(String namaPoli) {
        this.namaPoli = namaPoli;
    }

    public double getBiayaKonsultasiDokter() {
        return this.biayaKonsultasiDokter;
    }

    public void setBiayaKonsultasiDokter(double biayaKonsultasiDokter) {
        this.biayaKonsultasiDokter = biayaKonsultasiDokter;
    }

    public String getResepObat() {
        return this.resepObat;
    }

    public void setResepObat(String resepObat) {
        this.resepObat = resepObat;
    }

    public double getBiayaObat() {
        return this.biayaObat;
    }

    public void setBiayaObat(double biayaObat) {
        this.biayaObat = biayaObat;
    }
}
