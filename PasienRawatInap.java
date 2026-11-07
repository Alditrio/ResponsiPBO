public class PasienRawatInap extends Pasien implements LayananMedis, Pembayaran {
    private String nomorKamar;
    private int lamaMenginap;
    private double tarifKamarPerHari;
    private boolean butuhPerawatKhusus;
    private String catatanLayanan;

    // Overloaded Constructor 1
    public PasienRawatInap(String noRekamMedis, String nama, int usia, String nomorKamar, int lamaMenginap, double tarifKamarPerHari) {
        super(noRekamMedis, nama, usia);
        this.nomorKamar = nomorKamar;
        this.lamaMenginap = lamaMenginap;
        this.tarifKamarPerHari = tarifKamarPerHari;
        this.butuhPerawatKhusus = false;
        this.catatanLayanan = "Rawat inap reguler";
    }

    // Overloaded Constructor 2
    public PasienRawatInap(String noRekamMedis, String nama, int usia, String statusPasien, double biayaDasar,
                           String nomorKamar, int lamaMenginap, double tarifKamarPerHari, boolean butuhPerawatKhusus) {
        super(noRekamMedis, nama, usia, statusPasien, biayaDasar);
        this.nomorKamar = nomorKamar;
        this.lamaMenginap = lamaMenginap;
        this.tarifKamarPerHari = tarifKamarPerHari;
        this.butuhPerawatKhusus = butuhPerawatKhusus;
        this.catatanLayanan = "Rawat inap intensif";
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Tipe Layanan      : Rawat Inap");
        System.out.println("Kamar             : " + this.nomorKamar);
        System.out.println("Lama Menginap     : " + this.lamaMenginap + " hari");
        System.out.println("Tarif Kamar/Hari  : Rp " + this.tarifKamarPerHari);
        System.out.println("Perawat Khusus    : " + (this.butuhPerawatKhusus ? "Ya" : "Tidak"));
        System.out.println("Catatan           : " + this.catatanLayanan);
        System.out.println("======================================");
    }

    @Override
    public void prosesPemeriksaan(String keluhan) {
        // Logika percabangan membaca dan mengubah state internal objek
        if (keluhan.equalsIgnoreCase("Kritis")) {
            this.setStatusPasien("ICU");
            this.setButuhPerawatKhusus(true);
            this.setBiayaDasar(this.getBiayaDasar() + 1000000.0);
            this.catatanLayanan = "Dipindahkan ke ICU";
        } else if (keluhan.equalsIgnoreCase("Sembuh")) {
            this.setStatusPasien("Siap Pulang");
            this.setButuhPerawatKhusus(false);
            this.catatanLayanan = "Izin pulang diberikan";
        } else {
            this.setStatusPasien("Pemulihan");
            this.catatanLayanan = "Perawatan rutin di kamar";
        }
    }

    @Override
    public void lakukanTindakanMedis(String tindakan) {
        this.catatanLayanan = tindakan;
        System.out.println("Tindakan medis untuk " + this.getNama() + ": " + tindakan);
    }

    @Override
    public String getDetailLayanan() {
        return "Rawat Inap " + this.nomorKamar + " - " + this.catatanLayanan;
    }

    @Override
    public double hitungTotalBiaya() {
        double biayaKamar = this.lamaMenginap * this.tarifKamarPerHari;
        double biayaPerawat = this.butuhPerawatKhusus ? (200000.0 * this.lamaMenginap) : 0.0;
        double subtotal = this.getBiayaDasar() + biayaKamar + biayaPerawat;
        return subtotal + (subtotal * Pasien.TARIF_PAJAK);
    }

    @Override
    public void cetakKuitansi() {
        System.out.println("---------- KUITANSI RAWAT INAP ----------");
        System.out.println("Pasien            : " + this.getNama() + " (" + this.getNoRekamMedis() + ")");
        System.out.println("Total Tagihan     : Rp " + hitungTotalBiaya());
        System.out.println("Status            : LUNAS");
        System.out.println("-----------------------------------------");
    }

    // Getter dan Setter
    public String getNomorKamar() {
        return this.nomorKamar;
    }

    public void setNomorKamar(String nomorKamar) {
        this.nomorKamar = nomorKamar;
    }

    public int getLamaMenginap() {
        return this.lamaMenginap;
    }

    public void setLamaMenginap(int lamaMenginap) {
        this.lamaMenginap = lamaMenginap;
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

    public String getCatatanLayanan() {
        return this.catatanLayanan;
    }

    public void setCatatanLayanan(String catatanLayanan) {
        this.catatanLayanan = catatanLayanan;
    }
}
