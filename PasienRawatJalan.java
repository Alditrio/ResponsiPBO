public class PasienRawatJalan extends Pasien implements Pembayaran {
    private String namaPoli;
    private double biayaKonsultasi;
    private String resepObat;
    private double biayaObat;

    // Overloaded Constructor 1
    public PasienRawatJalan(String noRekamMedis, String nama, int usia, String namaPoli) {
        super(noRekamMedis, nama, usia);
        this.namaPoli = namaPoli;
        this.biayaKonsultasi = 80000.0;
        this.resepObat = "-";
        this.biayaObat = 0.0;
    }

    // Overloaded Constructor 2
    public PasienRawatJalan(String noRekamMedis, String nama, int usia, String statusPasien, double biayaDasar,
                            String namaPoli, double biayaKonsultasi) {
        super(noRekamMedis, nama, usia, statusPasien, biayaDasar);
        this.namaPoli = namaPoli;
        this.biayaKonsultasi = biayaKonsultasi;
        this.resepObat = "-";
        this.biayaObat = 0.0;
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Tipe Layanan      : Rawat Jalan");
        System.out.println("Poli              : " + this.namaPoli);
        System.out.println("Biaya Konsultasi  : Rp " + this.biayaKonsultasi);
        System.out.println("Resep Obat        : " + this.resepObat);
        System.out.println("Biaya Obat        : Rp " + this.biayaObat);
        System.out.println("======================================");
    }

    @Override
    public void prosesPemeriksaan(String keluhan) {
        // Logika percabangan membaca dan mengubah state internal objek
        if (keluhan.equalsIgnoreCase("Parah")) {
            this.setStatusPasien("Rujuk IGD");
            this.biayaKonsultasi += 100000.0;
            this.resepObat = "Injeksi Obat Darurat";
            this.biayaObat = 150000.0;
        } else if (keluhan.equalsIgnoreCase("Flu")) {
            this.setStatusPasien("Selesai Pemeriksaan");
            this.resepObat = "Paracetamol & Vitamin";
            this.biayaObat = 50000.0;
        } else {
            this.setStatusPasien("Selesai Pemeriksaan");
            this.resepObat = "Vitamin C";
            this.biayaObat = 25000.0;
        }
    }

    @Override
    public double hitungTotalBiaya() {
        double subtotal = this.getBiayaDasar() + this.biayaKonsultasi + this.biayaObat;
        return subtotal + (subtotal * Pasien.TARIF_PAJAK);
    }

    @Override
    public void cetakKuitansi() {
        System.out.println("---------- KUITANSI RAWAT JALAN ---------");
        System.out.println("Pasien            : " + this.getNama() + " (" + this.getNoRekamMedis() + ")");
        System.out.println("Poli              : " + this.namaPoli);
        System.out.println("Total Tagihan     : Rp " + hitungTotalBiaya());
        System.out.println("Status            : LUNAS");
        System.out.println("-----------------------------------------");
    }

    // Getter dan Setter
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
