public class PasienRawatJalan extends Pasien implements Pembayaran {
    private String namaPoli;
    private double biayaKonsultasi;

    // Overloaded Constructor 1
    public PasienRawatJalan(String noRekamMedis, String nama, int usia, String namaPoli) {
        super(noRekamMedis, nama, usia);
        this.namaPoli = namaPoli;
        this.biayaKonsultasi = 50000.0;
    }

    // Overloaded Constructor 2
    public PasienRawatJalan(String noRekamMedis, String nama, int usia, String statusPasien, double biayaDasar,
                            String namaPoli, double biayaKonsultasi) {
        super(noRekamMedis, nama, usia, statusPasien, biayaDasar);
        this.namaPoli = namaPoli;
        this.biayaKonsultasi = biayaKonsultasi;
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Poli: " + this.namaPoli);
        System.out.println("Biaya Konsultasi: Rp " + this.biayaKonsultasi);
    }

    @Override
    public void prosesPemeriksaan(String keluhan) {
        if (keluhan.equalsIgnoreCase("Parah")) {
            this.setStatusPasien("Rujuk IGD");
            this.biayaKonsultasi += 50000.0;
        } else {
            this.setStatusPasien("Selesai Periksa");
        }
    }

    @Override
    public double hitungTotalBiaya() {
        double subtotal = this.getBiayaDasar() + this.biayaKonsultasi;
        return subtotal + (subtotal * Pasien.TARIF_PAJAK);
    }

    @Override
    public void cetakKuitansi() {
        System.out.println("Kuitansi Rawat Jalan: " + this.getNama() + " - Total: Rp " + hitungTotalBiaya());
    }

    public String getNamaPoli() {
        return this.namaPoli;
    }

    public void setNamaPoli(String namaPoli) {
        this.namaPoli = namaPoli;
    }

    public double getBiayaKonsultasi() {
        return this.biayaKonsultasi;
    }

    public void setBiayaKonsultasi(double biayaKonsultasi) {
        this.biayaKonsultasi = biayaKonsultasi;
    }
}
