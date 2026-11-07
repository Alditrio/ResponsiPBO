/**
 * Class Main
 * 
 * Program pengujian utama untuk membuktikan seluruh 10 Persyaratan Teknis Kode
 * Responsi Akhir Praktikum Pemrograman Berorientasi Objek.
 * 
 * Tema Sistem: Rumah Sakit (NIM Akhiran 7)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("       PENGUJIAN SISTEM MANAJEMEN RUMAH SAKIT - RESPONSI AKHIR PBO             ");
        System.out.println("================================================================================");
        System.out.println("Tema Sistem       : Rumah Sakit (Digit Terakhir NIM: 7)");
        System.out.println("Nama Rumah Sakit  : " + Pasien.NAMA_RUMAH_SAKIT);
        System.out.println("================================================================================\n");

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 2: IMMUTABILITY
        // - Konstanta global (public static final)
        // - Atribut instans kekal (private final)
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 2: Immutability]");
        System.out.println("1. Konstanta Global (public static final):");
        System.out.println("   - Pasien.NAMA_RUMAH_SAKIT = \"" + Pasien.NAMA_RUMAH_SAKIT + "\"");
        System.out.println("   - Pasien.TARIF_PAJAK_PPN   = " + (Pasien.TARIF_PAJAK_PPN * 100) + "%");
        System.out.println("2. Atribut Instans Kekal (private final):");
        System.out.println("   - noRekamMedis diinisialisasi melalui konstruktor dan tidak dapat diubah (hanya getter).");
        System.out.println();

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 3: ENKAPSULASI DAN OVERLOADING
        // - Atribut berstatus private diakses melalui getter dan setter
        // - Menggunakan keyword 'this'
        // - Overloaded constructor (minimal 2 variasi konstruktor)
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 3: Enkapsulasi dan Overloading Constructor]");
        System.out.println("Menginstansiasi objek PasienRawatInap dengan Konstruktor Variasi 1 (Ringkas):");
        PasienRawatInap pasienInap1 = new PasienRawatInap(
                "RM-2026-001", "Budi Santoso", 45, "Anggrek-01", 3, 400000.0
        );
        System.out.println("-> Pasien 1 dibuat: " + pasienInap1.getRingkasanPasien());

        System.out.println("\nMenginstansiasi objek PasienRawatInap dengan Konstruktor Variasi 2 (Lengkap):");
        PasienRawatInap pasienInap2 = new PasienRawatInap(
                "RM-2026-002", "Siti Aminah", 60, "Rujukan Puskesmas", 75000.0,
                "ICU-03", 5, 850000.0, true
        );
        System.out.println("-> Pasien 2 dibuat: " + pasienInap2.getRingkasanPasien());

        System.out.println("\nUji Setter & Getter (Enkapsulasi) pada Pasien 1:");
        System.out.println("Nama sebelum diubah: " + pasienInap1.getNama());
        pasienInap1.setNama("Budi Santoso, S.Kom.");
        System.out.println("Nama setelah setter: " + pasienInap1.getNama());
        System.out.println();

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 4: INHERITANCE
        // - Superclass Pasien dan dua subclass: PasienRawatInap dan PasienRawatJalan
        // - Class Main dipisahkan dari class lainnya
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 4: Inheritance]");
        System.out.println("Menginstansiasi subclass kedua: PasienRawatJalan (extends Pasien)");
        PasienRawatJalan pasienJalan1 = new PasienRawatJalan(
                "RM-2026-003", "Ahmad Fauzi", 28, "Poli Jantung"
        );
        System.out.println("Objek PasienRawatInap  adalah instance of Pasien: " + (pasienInap1 instanceof Pasien));
        System.out.println("Objek PasienRawatJalan adalah instance of Pasien: " + (pasienJalan1 instanceof Pasien));
        System.out.println();

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 5 & 6: SUPER CHAINING & METHOD OVERRIDING
        // - super(...) pada konstruktor subclass telah tereksekusi pada pembuatan objek
        // - super.tampilkanInformasi() dipanggil di dalam overridden tampilkanInformasi()
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 5 & 6: Super Chaining & Method Overriding (@Override)]");
        System.out.println("Memanggil tampilkanInformasi() pada PasienRawatInap (memanggil super.tampilkanInformasi()):");
        pasienInap1.tampilkanInformasi();

        System.out.println("Memanggil tampilkanInformasi() pada PasienRawatJalan (memanggil super.tampilkanInformasi()):");
        pasienJalan1.tampilkanInformasi();
        System.out.println();

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 1, 7 & 8: ABSTRACT CLASS & LOGIKA STATE PERCABANGAN
        // - Superclass Pasien berstatus abstract
        // - Abstract method: prosesPemeriksaan(String keluhanAtauKondisi)
        // - Implementasi memuat logika if/else yang membaca dan mengubah state internal
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 7 & 8: Abstract Method & Logika State Percabangan]");
        System.out.println("Mengeksekusi prosesPemeriksaan(\"Kritis\") pada Pasien Rawat Inap:");
        System.out.println("State awal: Status=" + pasienInap1.getStatusPasien() + ", ButuhPerawatKhusus=" + pasienInap1.isButuhPerawatKhusus());
        pasienInap1.prosesPemeriksaan("Kritis");
        System.out.println("State akhir: Status=" + pasienInap1.getStatusPasien() + ", ButuhPerawatKhusus=" + pasienInap1.isButuhPerawatKhusus());

        System.out.println("\nMengeksekusi prosesPemeriksaan(\"Perlu Obat\") pada Pasien Rawat Jalan:");
        System.out.println("State awal: Status=" + pasienJalan1.getStatusPasien() + ", Resep=" + pasienJalan1.getResepObat());
        pasienJalan1.prosesPemeriksaan("Perlu Obat");
        System.out.println("State akhir: Status=" + pasienJalan1.getStatusPasien() + ", Resep=" + pasienJalan1.getResepObat());
        System.out.println();

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 1: STATE DAN BEHAVIOR
        // - Method Prosedur (void): cetakKuitansi()
        // - Method Fungsi (return value): hitungTotalBiaya(), getRingkasanPasien()
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 1: State dan Behavior]");
        System.out.println("1. Pemanggilan Method Fungsi (Return Value):");
        double totalBiayaInap = pasienInap1.hitungTotalBiaya();
        double totalBiayaJalan = pasienJalan1.hitungTotalBiaya();
        System.out.println("   - Return hitungTotalBiaya() Pasien Inap : Rp " + String.format("%,.2f", totalBiayaInap));
        System.out.println("   - Return hitungTotalBiaya() Pasien Jalan: Rp " + String.format("%,.2f", totalBiayaJalan));
        System.out.println("\n2. Pemanggilan Method Prosedur (void):");
        pasienInap1.cetakKuitansi();
        System.out.println();
        pasienJalan1.cetakKuitansi();
        System.out.println();

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 9: POLIMORFISME INTERFACE
        // - Pemanggilan method melalui variabel referensi bertipe interface:
        //   NamaInterface obj = new Subclass();
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 9: Polimorfisme Interface]");
        System.out.println("Variabel referensi bertipe Interface LayananMedis:");
        LayananMedis layananMedis = new PasienRawatInap(
                "RM-2026-004", "Dewi Lestari", 34, "Melati-05", 2, 350000.0
        );
        layananMedis.lakukanTindakanMedis("Injeksi Vitamin & Fisioterapi Pasca Operasi");
        System.out.println("Hasil getDetailLayanan() via interface: " + layananMedis.getDetailLayanan());

        System.out.println("\nVariabel referensi bertipe Interface Pembayaran:");
        Pembayaran refPembayaran = new PasienRawatJalan(
                "RM-2026-005", "Bambang Pamungkas", 42, "Poli Mata"
        );
        System.out.println("Total Biaya dihitung via referensi Pembayaran: Rp " +
                String.format("%,.2f", refPembayaran.hitungTotalBiaya()));
        System.out.println();

        // ---------------------------------------------------------------------
        // BUKTI PERSYARATAN 10: MULTIPLE INTERFACE
        // - Minimal satu subclass mengimplementasikan minimal dua interface sekaligus:
        //   implements LayananMedis, Pembayaran
        // ---------------------------------------------------------------------
        System.out.println(">>> [BUKTI PERSYARATAN 10: Multiple Interface]");
        System.out.println("Subclass 'PasienRawatInap' mengimplementasikan 'LayananMedis' dan 'Pembayaran':");
        System.out.println("-> pasienInap1 instanceof LayananMedis : " + (pasienInap1 instanceof LayananMedis));
        System.out.println("-> pasienInap1 instanceof Pembayaran   : " + (pasienInap1 instanceof Pembayaran));
        System.out.println("\nDemonstrasi pemanggilan kedua interface pada objek yang sama:");
        ((LayananMedis) pasienInap1).lakukanTindakanMedis("Pemberian Terapi Nebulizer dan Bronkodilator");
        System.out.println("Detail Layanan: " + ((LayananMedis) pasienInap1).getDetailLayanan());
        System.out.println("Total Tagihan : Rp " + String.format("%,.2f", ((Pembayaran) pasienInap1).hitungTotalBiaya()));
        System.out.println();

        System.out.println("================================================================================");
        System.out.println("           SELURUH 10 PERSYARATAN TEKNIS BERHASIL DIVERIFIKASI!                 ");
        System.out.println("================================================================================");
    }
}
