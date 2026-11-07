public class Main {
    public static void main(String[] args) {
        PasienRawatInap p1 = new PasienRawatInap("RM01", "Budi", 45, "Melati", 3, 200000.0);
        p1.tampilkanInformasi();
        p1.prosesPemeriksaan("Kritis");
        p1.cetakKuitansi();

        // Polimorfisme interface
        LayananMedis layanan = new PasienRawatInap("RM02", "Andi", 30, "Mawar", 2, 150000.0);
        layanan.lakukanTindakanMedis("Pemberian Obat");

        Pembayaran bayar = new PasienRawatJalan("RM03", "Siti", 25, "Poli Umum");
        bayar.cetakKuitansi();
    }
}
