/**
 * Interface Pembayaran
 * 
 * Mendefinisikan kontrak untuk kalkulasi tarif dan pencetakan kuitansi rumah sakit.
 * Memenuhi:
 * - Persyaratan 9: Polimorfisme Interface
 * - Persyaratan 10: Multiple Interface
 */
public interface Pembayaran {

    /**
     * Menghitung total biaya pengobatan/perawatan termasuk pajak dan komponen lainnya.
     * @return nilai nominal total biaya (return value)
     */
    double hitungTotalBiaya();

    /**
     * Mencetak kuitansi pembayaran resmi rumah sakit ke konsol (void procedure).
     */
    void cetakKuitansi();
}
