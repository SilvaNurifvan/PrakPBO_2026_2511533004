package pekan3;

import java.text.NumberFormat;
import java.util.Locale;

public class Transaksi {

    private String idTransaksi;
    private String jenis;
    private double nominal;

    // Constructor
    public Transaksi(String id, String jenis, double nominal) {
        this.idTransaksi = id;
        this.jenis = jenis;
        this.nominal = nominal;
    }

    // Getter (Read-Only)
    public String getIdTransaksi() {return idTransaksi;}
    public String getJenis() {return jenis;}
    public double getNominal() {return nominal;}

    // Menampilkan detail transaksi
    public void cetakDetail() {
        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        System.out.println(
                "ID : " + idTransaksi +
                " | Jenis : " + jenis +
                " | Nominal : " + rupiah.format(nominal)
        );
    }
}