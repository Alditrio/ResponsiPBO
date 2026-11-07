/**
 * Subclass PasienRawatInap
 * 
 * Merepresentasikan pasien yang menjalani rawat inap (opname) di rumah sakit.
 * Memenuhi Persyaratan:
 * - Persyaratan 1: State dan Behavior
 * - Persyaratan 3: Enkapsulasi & Overloaded Constructor
 * - Persyaratan 4: Inheritance (extends Pasien)
 * - Persyaratan 5: Super Chaining (super(...) dan super.tampilkanInformasi())
 * - Persyaratan 6: Method Overriding (@Override)
 * - Persyaratan 8: Logika State Percabangan (if/else membaca & mengubah state internal)
 * - Persyaratan 10: Multiple Interface (implements LayananMedis, Pembayaran)
 */
public class PasienRawatInap extends Pasien implements LayananMedis, Pembayaran {

    // =========================================================================
    // PERSYARATAN 1 & 3: ENKAPSULASI (Atribut berstatus private)
    // =========================================================================
    private String nomorKamar;
    private int lamaMenginapHari;
    private double tarifKamarPerHari;
    private boolean butuhPerawatKhusus;
    private String catatanLayananMedis;

    // =========================================================================
    // PERSYARATAN 3 & 5: OVERLOADED CONSTRUCTOR 1 & SUPER CHAINING
    // Memanggil super(...) dari superclass Pasien
    // =========================================================================
    public PasienRawatInap(String noRekamMedis, String nama, int usia, String nomorKamar,
                           int lamaMenginapHari, double tarifKamarPerHari) {
        super(noRekamMedis, nama, usia); // Pemanggilan super(...) ke konstruktor Pasien 3 parameter
        this.nomorKamar = nomorKamar;
        this.lamaMenginapHari = lamaMenginapHari;
        this.tarifKamarPerHari = tarifKamarPerHari;
        this.butuhPerawatKhusus = false;
        this.catatanLayananMedis = "Pemasangan infus awal dan rawat inap umum.";
    }

    // =========================================================================
    // PERSYARATAN 3 & 5: OVERLOADED CONSTRUCTOR 2 & SUPER CHAINING
    // Konstruktor dengan parameter lengkap
    // =========================================================================
    public PasienRawatInap(String noRekamMedis, String nama, int usia, String statusPasien,
                           double biayaDasar, String nomorKamar, int lamaMenginapHari,
                           double tarifKamarPerHari, boolean butuhPerawatKhusus) {
        super(noRekamMedis, nama, usia, statusPasien, biayaDasar); // Pemanggilan super(...) lengkap
        this.nomorKamar = nomorKamar;
        this.lamaMenginapHari = lamaMenginapHari;
        this.tarifKamarPerHari = tarifKamarPerHari;
        this.butuhPerawatKhusus = butuhPerawatKhusus;
        this.catatanLayananMedis = "Inisialisasi bangsal intensif.";
    }

    // =========================================================================
    // PERSYARATAN 5 & 6: SUPER CHAINING & METHOD OVERRIDING
    // Penimpaan method superclass dengan pemanggilan super.namaMethod()
    // =========================================================================
    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi(); // PERSYARATAN 5: Pemanggilan super.namaMethod()
        System.out.println("Tipe Pasien         : Rawat Inap");
        System.out.println("Nomor Kamar         : " + this.nomorKamar);
        System.out.println("Lama Menginap       : " + this.lamaMenginapHari + " hari");
        System.out.println("Tarif Kamar/Hari    : Rp " + String.format("%,.2f", this.tarifKamarPerHari));
        System.out.println("Perawat Khusus      : " + (this.butuhPerawatKhusus ? "Ya (Intensif 24 Jam)" : "Tidak (Reguler)"));
        System.out.println("Catatan Medis       : " + this.catatanLayananMedis);
        System.out.println("--------------------------------------------------");
    }

    // =========================================================================
    // PERSYARATAN 6, 7 & 8: IMPLEMENTASI ABSTRACT METHOD & LOGIKA STATE PERCABANGAN
    // Membaca dan mengubah state internal objek melalui if/else
    // =========================================================================
    @Override
    public void prosesPemeriksaan(String keluhanAtauKondisi) {
        System.out.println("\n[Proses Visite Dokter & Evaluasi Kondisi]");
        System.out.println("Kondisi Pasien Masuk: " + keluhanAtauKondisi);

        // Membaca state internal objek sebelum percabangan
        String statusAwal = this.getStatusPasien();
        System.out.println("Status internal awal: " + statusAwal);

        // Logika percabangan if/else yang MEMBACA dan MENGUBAH state internal objek
        if (keluhanAtauKondisi.equalsIgnoreCase("Kritis") || keluhanAtauKondisi.equalsIgnoreCase("Gawat")) {
            // Mengubah state internal: status pasien, pendampingan khusus, dan penyesuaian biaya dasar
            this.setStatusPasien("Perawatan Intensif (ICU)");
            this.setButuhPerawatKhusus(true);
            this.setBiayaDasar(this.getBiayaDasar() + 1500000.0);
            this.catatanLayananMedis = "Pasien dipindahkan ke ICU, monitoring tanda vital setiap 15 menit.";
            System.out.println("-> PERUBAHAN STATE: Status beralih ke ICU! Pendampingan perawat khusus diaktifkan.");
        } else if (keluhanAtauKondisi.equalsIgnoreCase("Stabil") || keluhanAtauKondisi.equalsIgnoreCase("Membaik")) {
            // Mengubah state internal: status menjadi observasi, perawat khusus dilepas jika ada
            this.setStatusPasien("Observasi Stabil");
            this.setButuhPerawatKhusus(false);
            this.catatanLayananMedis = "Kondisi stabil, terapi oral dilanjutkan di bangsal reguler.";
            System.out.println("-> PERUBAHAN STATE: Status pasien stabil, tidak memerlukan perawat khusus.");
        } else if (keluhanAtauKondisi.equalsIgnoreCase("Sembuh") || keluhanAtauKondisi.equalsIgnoreCase("Pulang")) {
            // Mengubah state internal: status menjadi siap pulang
            this.setStatusPasien("Siap Pulang");
            this.setButuhPerawatKhusus(false);
            this.catatanLayananMedis = "Pasien diizinkan pulang, jadwal kontrol 7 hari ke depan.";
            System.out.println("-> PERUBAHAN STATE: Pasien telah sembuh dan administrasi kepulangan diproses.");
        } else {
            // Kondisi default/umum
            this.setStatusPasien("Rawat Inap Standar");
            this.catatanLayananMedis = "Menjalani perawatan rutin di ruang rawat inap.";
            System.out.println("-> PERUBAHAN STATE: Perawatan reguler tetap dilanjutkan.");
        }
    }

    // =========================================================================
    // PERSYARATAN 9 & 10: IMPLEMENTASI INTERFACE LayananMedis
    // Prosedur void dan fungsi return value
    // =========================================================================
    @Override
    public void lakukanTindakanMedis(String tindakan) {
        this.catatanLayananMedis = tindakan;
        System.out.println("[LayananMedis] Tindakan diberikan kepada " + this.getNama() + ": " + tindakan);
    }

    @Override
    public String getDetailLayanan() {
        return "Layanan Rawat Inap Kamar " + this.nomorKamar + " (" + this.catatanLayananMedis + ")";
    }

    // =========================================================================
    // PERSYARATAN 9 & 10: IMPLEMENTASI INTERFACE Pembayaran
    // Method fungsi (return value) dan prosedur (void)
    // =========================================================================
    @Override
    public double hitungTotalBiaya() {
        // Biaya kamar = lama menginap * tarif kamar per hari
        double subtotalKamar = this.lamaMenginapHari * this.tarifKamarPerHari;
        // Biaya tambahan perawat khusus jika dibutuhkan
        double biayaPerawat = this.butuhPerawatKhusus ? (350000.0 * this.lamaMenginapHari) : 0.0;
        // Total kotor
        double totalKotor = this.getBiayaDasar() + subtotalKamar + biayaPerawat;
        // Ditambah PPN 11% (menggunakan konstanta global dari superclass)
        double totalPajak = totalKotor * Pasien.TARIF_PAJAK_PPN;
        return totalKotor + totalPajak;
    }

    @Override
    public void cetakKuitansi() {
        double subtotalKamar = this.lamaMenginapHari * this.tarifKamarPerHari;
        double biayaPerawat = this.butuhPerawatKhusus ? (350000.0 * this.lamaMenginapHari) : 0.0;
        double totalKotor = this.getBiayaDasar() + subtotalKamar + biayaPerawat;
        double totalPajak = totalKotor * Pasien.TARIF_PAJAK_PPN;
        double totalAkhir = hitungTotalBiaya();

        System.out.println("================== KUITANSI RAWAT INAP ==================");
        System.out.println("Instansi            : " + Pasien.NAMA_RUMAH_SAKIT);
        System.out.println("No. Rekam Medis     : " + this.getNoRekamMedis());
        System.out.println("Nama Pasien         : " + this.getNama());
        System.out.println("Kamar / Durasi      : " + this.nomorKamar + " (" + this.lamaMenginapHari + " hari)");
        System.out.println("Biaya Pendaftaran   : Rp " + String.format("%,.2f", this.getBiayaDasar()));
        System.out.println("Biaya Kamar         : Rp " + String.format("%,.2f", subtotalKamar));
        System.out.println("Biaya Perawat Khusus: Rp " + String.format("%,.2f", biayaPerawat));
        System.out.println("PPN (11%)           : Rp " + String.format("%,.2f", totalPajak));
        System.out.println("---------------------------------------------------------");
        System.out.println("TOTAL DIBAYARKAN    : Rp " + String.format("%,.2f", totalAkhir));
        System.out.println("Status Pembayaran   : LUNAS");
        System.out.println("=========================================================");
    }

    // =========================================================================
    // PERSYARATAN 3: GETTER DAN SETTER
    // =========================================================================
    public String getNomorKamar() {
        return this.nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public int getLamaMenginapHari() {
        return this.lamaMenginapHari;
    }

    public void setLamaMenginapHari(int lamaMenginapHari) {
        this.lamaMenginapHari = lamaMenginapHari;
    }

    public double getTarifKamarPerHari() {
        return this.tarifKamarPerHari;
    }

    public void setTarifKamarPerHari(double tarifKamarPerHari) {
        this.tarifKamarPerHari = tarifKamarPerHari;
    }

    public boolean isButuhPerawatKhusus() {
        return this.butuhPerawatKhusus;
    }

    public void setButuhPerawatKhusus(boolean butuhPerawatKhusus) {
        this.butuhPerawatKhusus = butuhPerawatKhusus;
    }

    public String getCatatanLayananMedis() {
        return this.catatanLayananMedis;
    }

    public void setCatatanLayananMedis(String catatanLayananMedis) {
        this.catatanLayananMedis = catatanLayananMedis;
    }
}
