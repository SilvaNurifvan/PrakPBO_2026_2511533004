package pekan5;

import java.text.NumberFormat;

public class RekeningTabungan extends Rekening {

    private double sukuBunga;

    // Constructor
    public RekeningTabungan(
            String nomor,
            String nama,
            double saldoAwal,
            String pinAwal,
            double sukuBunga) {

        super(nomor, nama, saldoAwal, pinAwal);
        this.sukuBunga = sukuBunga;
    }

    // Getter suku bunga
    public double getSukuBunga() {return sukuBunga;}

    // Menambahkan bunga pada akhir bulan
    public void tambahBungaAkhirBulan() {
        double bunga = saldo * sukuBunga / 100;
        saldo += bunga;

        String idTrx = "TRX-B-" + System.currentTimeMillis();
        Transaksi transaksi = new Transaksi(idTrx, "Bunga", bunga);
        riwayatTransaksi.add(transaksi);

        NumberFormat rupiah = NumberFormat.getCurrencyInstance();
                   

        System.out.println("Bunga akhir bulan: " + rupiah.format(bunga));
        System.out.println("Saldo setelah bunga: " + rupiah.format(saldo));
    }
}