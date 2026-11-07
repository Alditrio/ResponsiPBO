/**
 * Interface LayananMedis
 * 
 * Mendefinisikan kontrak untuk tindakan medis yang dapat diberikan kepada pasien.
 * Memenuhi:
 * - Persyaratan 9: Polimorfisme Interface
 * - Persyaratan 10: Multiple Interface
 */
public interface LayananMedis {

    /**
     * Memberikan tindakan medis tertentu kepada pasien.
     * @param tindakan deskripsi tindakan medis yang dilakukan
     */
    void lakukanTindakanMedis(String tindakan);

    /**
     * Mengembalikan detail histori atau status layanan medis terkini.
     * @return string berisi rincian layanan
     */
    String getDetailLayanan();
}
