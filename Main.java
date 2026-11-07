public class Main {
    public static void main(String[] args) {
        // Objek Rawat Inap dan Rawat Jalan
        PasienRawatInap pasien1 = new PasienRawatInap("RM01", "Budi", 45, "Melati", 3, 200000.0);
        PasienRawatJalan pasien2 = new PasienRawatJalan("RM02", "Siti", 25, "Poli Umum");

        // Tampilkan informasi pasien
        pasien1.tampilkanInformasi();
        pasien2.tampilkanInformasi();

        // Proses pemeriksaan (mengubah status internal objek)
        pasien1.prosesPemeriksaan("Kritis");
        pasien2.prosesPemeriksaan("Ringan");
        System.out.println("Status Budi: " + pasien1.getStatusPasien());
        System.out.println("Status Siti: " + pasien2.getStatusPasien());

        // Cetak kuitansi pembayaran
        pasien1.cetakKuitansi();
        pasien2.cetakKuitansi();

        // Polimorfisme Interface
        LayananMedis layanan = new PasienRawatInap("RM03", "Andi", 30, "Mawar", 2, 150000.0);
        layanan.lakukanTindakanMedis("Pemberian Obat");
        System.out.println("Layanan: " + layanan.getDetailLayanan());

        Pembayaran bayar = new PasienRawatJalan("RM04", "Dewi", 20, "Poli Gigi");
        System.out.println("Total Bayar: Rp " + bayar.hitungTotalBiaya());
    }
}
