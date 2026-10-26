package pekan3;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Rekening {

    // Enkapsulasi dengan private
    private String nomorRekening;
    private String namaPemilik;
    private double saldo;
    private String pin;

    private ArrayList<Transaksi> riwayatTransaksi;

    // Constructor
    public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {

        this.nomorRekening = nomor;
        this.namaPemilik = nama;
        this.saldo = saldoAwal;

        // Validasi PIN
        if (pinValid(pinAwal)) {
            this.pin = pinAwal;
        } else {
            System.out.println(
                "Peringatan: PIN tidak valid. Menggunakan PIN default 583041"
            );
            this.pin = "583041";
        }

        this.riwayatTransaksi = new ArrayList<>();

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

        System.out.println(
            "Rekening atas " + namaPemilik +
            " berhasil dibuat dengan saldo " +
            rupiah.format(saldo)
        );
    }

    // Getter
    public String getNomorRekening() {
        return nomorRekening;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    // Validasi PIN
    public static boolean pinValid(String pin) {

        // PIN harus 6 digit
        if (pin == null || !pin.matches("\\d{6}")) {
            return false;
        }

        // Tidak boleh semua angka sama
        boolean semuaSama = true;

        for (int i = 1; i < pin.length(); i++) {
            if (pin.charAt(i) != pin.charAt(0)) {
                semuaSama = false;
                break;
            }
        }

        if (semuaSama) {
            return false;
        }

        // Tidak boleh berurutan naik atau turun
        boolean naik = true;
        boolean turun = true;

        for (int i = 1; i < pin.length(); i++) {

            int sebelumnya = pin.charAt(i - 1) - '0';
            int sekarang = pin.charAt(i) - '0';

            if (sekarang != sebelumnya + 1) {
                naik = false;
            }

            if (sekarang != sebelumnya - 1) {
                turun = false;
            }
        }

        if (naik || turun) {
            return false;
        }

        return true;
    }

    // Otentikasi PIN
    public boolean otentikasi(String inputPin) {
        return this.pin.equals(inputPin);
    }

    // Setor tunai
    public void setorTunai(double nominal) {

        if (nominal <= 0) {

            System.out.println(
                "Transaksi Gagal: Nominal setor harus lebih dari 0!"
            );

        } else {

            saldo += nominal;

            String idTrx =
                    "TRX-S-" + System.currentTimeMillis();

            Transaksi transaksi =
                    new Transaksi(idTrx, "Kredit", nominal);

            riwayatTransaksi.add(transaksi);

            NumberFormat rupiah =
                    NumberFormat.getCurrencyInstance(
                        new Locale("id", "ID")
                    );

            System.out.println(
                "Setor tunai " + rupiah.format(nominal) +
                " berhasil."
            );

            System.out.println(
                "Saldo saat ini : " + rupiah.format(saldo)
            );
        }
    }

    // Tarik tunai
    public void tarikTunai(double nominal) {

        if (nominal < 10000) {

            System.out.println(
                "Transaksi Gagal: Minimal nominal penarikan Rp10.000!"
            );

        } else if (nominal > saldo) {

            System.out.println(
                "Transaksi Gagal: Saldo tidak mencukupi."
            );

        } else if (saldo - nominal < 10000) {

            System.out.println(
                "Transaksi Gagal: Saldo minimal setelah penarikan Rp10.000!"
            );

        } else {

            saldo -= nominal;

            String idTrx =
                    "TRX-T-" + System.currentTimeMillis();

            Transaksi transaksi =
                    new Transaksi(idTrx, "Debit", nominal);

            riwayatTransaksi.add(transaksi);

            NumberFormat rupiah =
                    NumberFormat.getCurrencyInstance(
                        new Locale("id", "ID")
                    );

            System.out.println(
                "Tarik tunai " + rupiah.format(nominal) +
                " berhasil."
            );

            System.out.println(
                "Saldo saat ini : " + rupiah.format(saldo)
            );
        }
    }

    // Cek informasi rekening
    public void cekInformasi() {

        NumberFormat rupiah =
                NumberFormat.getCurrencyInstance(
                    new Locale("id", "ID")
                );

        System.out.println("\n--- INFO REKENING ---");
        System.out.println("No. Rekening : " + nomorRekening);
        System.out.println("Nama Pemilik : " + namaPemilik);
        System.out.println("Saldo Akhir  : " + rupiah.format(saldo));
        System.out.println("---------------------");
    }

    // Cetak riwayat transaksi
    public void cetakMutasi() {

        if (riwayatTransaksi.isEmpty()) {

            System.out.println(
                "Belum ada transaksi pada rekening ini."
            );

        } else {

            System.out.println("\n--- MUTASI REKENING ---");

            for (Transaksi trx : riwayatTransaksi) {
                trx.cetakDetail();
            }

            System.out.println("-----------------------");
        }
    }
}