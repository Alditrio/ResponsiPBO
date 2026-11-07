public class Main {
    public static void main(String[] args) {
        // Inisialisasi objek (Overloaded Constructor & Inheritance)
        PasienRawatInap pasien1 = new PasienRawatInap("RM001", "Budi Santoso", 45, "Melati-01", 3, 300000.0);
        PasienRawatInap pasien2 = new PasienRawatInap("RM002", "Siti Aminah", 50, "Rujukan", 60000.0, "ICU-02", 4, 700000.0, true);
        PasienRawatJalan pasien3 = new PasienRawatJalan("RM003", "Ahmad Fauzi", 25, "Poli Umum");

        // Menampilkan informasi pasien (Super Chaining & Overriding)
        pasien1.tampilkanInformasi();
        pasien3.tampilkanInformasi();

        // Proses pemeriksaan (Logika State Percabangan if/else)
        pasien1.prosesPemeriksaan("Kritis");
        pasien3.prosesPemeriksaan("Flu");

        System.out.println("\nStatus Pasien 1 setelah diperiksa: " + pasien1.getStatusPasien());
        System.out.println("Status Pasien 3 setelah diperiksa: " + pasien3.getStatusPasien());

        // Cetak kuitansi (Method Prosedur void & Fungsi Return Value)
        System.out.println();
        pasien1.cetakKuitansi();
        pasien3.cetakKuitansi();

        // Polimorfisme Interface
        System.out.println();
        LayananMedis layanan = new PasienRawatInap("RM004", "Dewi Lestari", 30, "Mawar-03", 2, 250000.0);
        layanan.lakukanTindakanMedis("Pemberian Terapi Injeksi & Infus");
        System.out.println("Detail: " + layanan.getDetailLayanan());

        Pembayaran bayar = new PasienRawatJalan("RM005", "Rian Hidayat", 28, "Poli Gigi");
        System.out.println("Total Bayar via Interface: Rp " + bayar.hitungTotalBiaya());
    }
}
