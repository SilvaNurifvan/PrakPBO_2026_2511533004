package pekan1;

public class Rekening {
    String nomorRekening;
    String namaPemilik;
    double saldo;

    public Rekening(String nomor, String nama, double saldoAwal) {
        nomorRekening = nomor;
        namaPemilik = nama;
        saldo = saldoAwal;
        System.out.println("Rekening atas nama " + namaPemilik + " Berhasil dibuat dengan saldo Rp" + saldo);
    }

    public void setorTunai(double nominal) {
        if (nominal > 0) {
            saldo += nominal;
            System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini Rp" + saldo);
        } else {
            System.out.println("Gagal : Nominal setor harus lebih dari 0!");
        }
    }

    public void tarikTunai(double nominal) {
        if (nominal < 10000) {
            System.out.println("Transaksi Gagal: Nominal minimal adalah Rp. 10.000");
        } else if (nominal > saldo) {
            System.out.println("Transaksi Gagal: Nominal penarikan tidak mencukupi saldo Rp. " + saldo);
        } else if (saldo == 10000) {
            System.out.println("Transaksi Gagal: Tidak bisa menarik karena anda sudah di batas saldo");
        } else {
            saldo -= nominal;
            System.out.println("Sukses: Anda menarik saldo Rp " + nominal + " Sisa saldo = " + saldo);
        }
    }

    public void cekInformasi() {
        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir : Rp" + saldo);
        System.out.println("---------------------");
    }
}