package pekan4;

import java.text.NumberFormat;

public class RekeningGiro extends Rekening {

    private double batasOverdraft;
    // Constructor
    public RekeningGiro(
            String nomor,
            String nama,
            double saldoAwal,
            String pinAwal,
            double batasOverdraft) {

        super(nomor, nama, saldoAwal, pinAwal); this.batasOverdraft = batasOverdraft;}

    // Getter batas overdraft
    public double getBatasOverdraft() {return batasOverdraft;}

    // Override method tarikTunai
    public void tarikTunai(double nominal) { if (nominal < 10000) {

            System.out.println ("Transaksi Gagal: Minimal nominal penarikan Rp10.000!");
        } else if (nominal > saldo + batasOverdraft) {
            System.out.println("Transaksi Gagal: Melebihi batas overdraft.");
        } else {saldo -= nominal;
        
            String idTrx = "TRX-T-" + System.currentTimeMillis();
            Transaksi transaksi = new Transaksi(idTrx, "Debit", nominal);
            riwayatTransaksi.add(transaksi);
            NumberFormat rupiah = NumberFormat.getCurrencyInstance();
            
            System.out.println("Tarik tunai " + rupiah.format(nominal) + " berhasil.");
            System.out.println("Saldo saat ini : " + rupiah.format(saldo));
        }
    }
}