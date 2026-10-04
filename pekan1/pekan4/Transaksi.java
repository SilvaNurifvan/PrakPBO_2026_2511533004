package pekan4;

import java.text.NumberFormat;

public class Transaksi {

    private String idTransaksi;
    private String jenis;
    private double nominal;

    // Constructor
    public Transaksi(String id, String jenis, double nominal) {
        this.idTransaksi = id;
        this.jenis = jenis;
        this.nominal = nominal;}

    // Getter (Read-Only)
    public String getIdTransaksi() {return idTransaksi;}
    public String getJenis() {return jenis;}
    public double getNominal() {return nominal;}

    // Menampilkan detail transaksi
    public void cetakDetail() {

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance();
               
        System.out.println( "ID : " + idTransaksi + " | Jenis : " + jenis + " | Nominal : " + rupiah.format(nominal));
    }
}