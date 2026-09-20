package pekan2;

import java.util.ArrayList;

public class Rekening {

    String nomorRekening;
    String namaPemilik;
    double saldo;

    ArrayList<Transaksi> riwayatTransaksi;

    public Rekening(String nomor, String nama, double saldoAwal) {

        nomorRekening = nomor;
        namaPemilik = nama;
        saldo = saldoAwal;

        riwayatTransaksi = new ArrayList<>();

        System.out.println(
            "Rekening atas nama " + namaPemilik +
            " berhasil dibuat dengan saldo Rp" + saldo
        );
    }

    public void setorTunai(double nominal) {

        if (nominal > 0) {

            saldo += nominal;

            String idTrx = "TRX-S-" + System.currentTimeMillis();
            Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);

            riwayatTransaksi.add(trxBaru);

            System.out.println(
                "Setor tunai Rp" + nominal +
                " berhasil. Saldo saat ini Rp" + saldo
            );

        } else {

            System.out.println(
                "Gagal: Nominal setor harus lebih dari 0!"
            );
        }
    }
    
    public void tarikTunai(double nominal) {

        if (nominal < 10000) {

            System.out.println(
                "Transaksi Gagal: Nominal minimal adalah Rp10.000"
            );

        } else if (nominal > saldo) {

            System.out.println(
                "Transaksi Gagal: Nominal penarikan tidak mencukupi saldo Rp"
                + saldo
            );

        } else if (saldo - nominal < 10000) {

            System.out.println(
                "Transaksi Gagal: Saldo minimal setelah penarikan adalah Rp10.000"
            );

        } else {

            saldo -= nominal;

            String idTrx = "TRX-T-" + System.currentTimeMillis();

            Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
            riwayatTransaksi.add(trxBaru);

            System.out.println(
                "Sukses: Anda menarik saldo Rp" + nominal +
                ". Sisa saldo = Rp" + saldo
            );
        }
    }

    public void cetakMutasi() {

        System.out.println("\n===== MUTASI REKENING =====");

        if (riwayatTransaksi.isEmpty()) {

            System.out.println(
                "Belum ada transaksi pada rekening ini"
            );

        } else {

            for (Transaksi transaksi : riwayatTransaksi) {

                transaksi.cetakDetail();
            }
        }

        System.out.println("===========================");
    }

    public void cekInformasi() {

        System.out.println("--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : Rp" + saldo);
        System.out.println("---------------------");
    }
}