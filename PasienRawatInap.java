public class PasienRawatInap extends Pasien implements LayananMedis, Pembayaran {
    private String nomorKamar;
    private int lamaMenginap;
    private double tarifKamar;
    private boolean perawatKhusus;
    private String catatan;

    // Overloaded Constructor 1
    public PasienRawatInap(String noRekamMedis, String nama, int usia, String nomorKamar, int lamaMenginap, double tarifKamar) {
        super(noRekamMedis, nama, usia);
        this.nomorKamar = nomorKamar;
        this.lamaMenginap = lamaMenginap;
        this.tarifKamar = tarifKamar;
        this.perawatKhusus = false;
        this.catatan = "Rawat inap reguler";
    }

    // Overloaded Constructor 2
    public PasienRawatInap(String noRekamMedis, String nama, int usia, String statusPasien, double biayaDasar,
                           String nomorKamar, int lamaMenginap, double tarifKamar, boolean perawatKhusus) {
        super(noRekamMedis, nama, usia, statusPasien, biayaDasar);
        this.nomorKamar = nomorKamar;
        this.lamaMenginap = lamaMenginap;
        this.tarifKamar = tarifKamar;
        this.perawatKhusus = perawatKhusus;
        this.catatan = "Rawat inap intensif";
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Kamar: " + this.nomorKamar);
        System.out.println("Lama Menginap: " + this.lamaMenginap + " hari");
    }

    @Override
    public void prosesPemeriksaan(String keluhan) {
        if (keluhan.equalsIgnoreCase("Kritis")) {
            this.setStatusPasien("ICU");
            this.perawatKhusus = true;
            this.setBiayaDasar(this.getBiayaDasar() + 500000.0);
        } else if (keluhan.equalsIgnoreCase("Sembuh")) {
            this.setStatusPasien("Pulang");
            this.perawatKhusus = false;
        } else {
            this.setStatusPasien("Rawat Reguler");
        }
    }

    @Override
    public void lakukanTindakanMedis(String tindakan) {
        this.catatan = tindakan;
        System.out.println("Tindakan untuk " + this.getNama() + ": " + tindakan);
    }

    @Override
    public String getDetailLayanan() {
        return this.nomorKamar + " (" + this.catatan + ")";
    }

    @Override
    public double hitungTotalBiaya() {
        double totalKamar = this.lamaMenginap * this.tarifKamar;
        double subtotal = this.getBiayaDasar() + totalKamar;
        return subtotal + (subtotal * Pasien.TARIF_PAJAK);
    }

    @Override
    public void cetakKuitansi() {
        System.out.println("Kuitansi Rawat Inap: " + this.getNama() + " - Total: Rp " + hitungTotalBiaya());
    }

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

    public double getTarifKamar() {
        return this.tarifKamar;
    }

    public void setTarifKamar(double tarifKamar) {
        this.tarifKamar = tarifKamar;
    }

    public boolean isPerawatKhusus() {
        return this.perawatKhusus;
    }

    public void setPerawatKhusus(boolean perawatKhusus) {
        this.perawatKhusus = perawatKhusus;
    }

    public String getCatatan() {
        return this.catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
