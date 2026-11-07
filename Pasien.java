public abstract class Pasien {
    public static final String NAMA_RUMAH_SAKIT = "Rumah Sakit Aldi";
    public static final double TARIF_PAJAK = 0.11;

    private final String noRekamMedis;
    private String nama;
    private int usia;
    private String statusPasien;
    private double biayaDasar;

    // Overloaded Constructor 1
    public Pasien(String noRekamMedis, String nama, int usia) {
        this(noRekamMedis, nama, usia, "Pendaftaran Baru", 50000.0);
    }

    // Overloaded Constructor 2
    public Pasien(String noRekamMedis, String nama, int usia, String statusPasien, double biayaDasar) {
        this.noRekamMedis = noRekamMedis;
        this.nama = nama;
        this.usia = usia;
        this.statusPasien = statusPasien;
        this.biayaDasar = biayaDasar;
    }

    public void tampilkanInformasi() {
        System.out.println("Rumah Sakit: " + NAMA_RUMAH_SAKIT);
        System.out.println("No RM: " + this.noRekamMedis);
        System.out.println("Nama: " + this.nama);
        System.out.println("Usia: " + this.usia + " tahun");
        System.out.println("Status: " + this.statusPasien);
    }

    public String getRingkasanPasien() {
        return this.noRekamMedis + " - " + this.nama;
    }

    public abstract void prosesPemeriksaan(String keluhan);
    public abstract double hitungTotalBiaya();

    public String getNoRekamMedis() {
        return this.noRekamMedis;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUsia() {
        return this.usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getStatusPasien() {
        return this.statusPasien;
    }

    public void setStatusPasien(String statusPasien) {
        this.statusPasien = statusPasien;
    }

    public double getBiayaDasar() {
        return this.biayaDasar;
    }

    public void setBiayaDasar(double biayaDasar) {
        this.biayaDasar = biayaDasar;
    }
}
