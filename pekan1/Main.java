package pekan3;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean isRunning = true;

        ArrayList<Rekening> daftarRekening =
                new ArrayList<>();

        Rekening akunAktif = null;

        System.out.println("=== SISTEM PERBANKAN MINI ===");

        while (isRunning) {

            System.out.println(" Menu Utama : ");
            System.out.println("1. Buka rekening baru");
            System.out.println("2. Setor tunai");
            System.out.println("3. Tarik tunai");
            System.out.println("4. Cek informasi rekening");
            System.out.println("5. Ganti akun");
            System.out.println("6. Cetak Mutasi (Riwayat)");
            System.out.println("0. Keluar");

            System.out.print("Pilih menu : ");
            int pilihan = input.nextInt();
            input.nextLine();

            switch (pilihan) {

                case 1:

                    System.out.print("Masukkan No Rekening : ");
                    String no = input.nextLine();

                    System.out.print("Masukkan Nama Pemilik : ");
                    String nama = input.nextLine();

                    System.out.print("Masukkan Saldo Awal : ");
                    double saldo = input.nextDouble();
                    input.nextLine();

                    String pin;

                    while (true) {

                        System.out.print(
                            "Masukkan PIN Anda (6 digit): "
                        );

                        pin = input.nextLine();

                        if (Rekening.pinValid(pin)) {
                            break;
                        }

                        System.out.println(
                            "PIN tidak valid!"
                        );

                        System.out.println(
                            "PIN harus 6 digit, tidak boleh semua sama "
                            + "atau berurutan."
                        );
                    }

                    akunAktif =
                            new Rekening(no, nama, saldo, pin);

                    daftarRekening.add(akunAktif);

                    break;

                case 2:

                    if (akunAktif == null) {

                        System.out.println(
                            "Error: Anda belum memiliki rekening!"
                        );

                    } else {

                        System.out.print(
                            "Masukkan nominal setor : "
                        );

                        double setor = input.nextDouble();
                        input.nextLine();

                        akunAktif.setorTunai(setor);
                    }

                    break;

                case 3:

                    if (akunAktif == null) {

                        System.out.println(
                            "Error: Anda belum memiliki rekening!"
                        );

                    } else {

                        System.out.print(
                            "Masukkan PIN Anda: "
                        );

                        String pinInput = input.nextLine();

                        if (akunAktif.otentikasi(pinInput)) {

                            System.out.print(
                                "Masukkan nominal yang akan ditarik: "
                            );

                            double tarik =
                                    input.nextDouble();

                            input.nextLine();

                            akunAktif.tarikTunai(tarik);

                        } else {

                            System.out.println(
                                "Akses Ditolak: PIN yang Anda masukkan salah!"
                            );
                        }
                    }

                    break;
               
                case 4:

                    if (akunAktif == null) {

                        System.out.println(
                            "Error: Anda belum membuka rekening!"
                        );

                    } else {

                        akunAktif.cekInformasi();
                    }

                    break;

                case 5:

                    if (daftarRekening.isEmpty()) {

                        System.out.println(
                            "Error: Belum ada rekening yang terdaftar!"
                        );

                    } else {

                        System.out.print(
                            "Masukkan No Rekening yang ingin diaktifkan: "
                        );

                        String cariNo = input.nextLine();

                        Rekening ditemukan = null;

                        for (Rekening r : daftarRekening) {

                            if (r.getNomorRekening()
                                    .equals(cariNo)) {

                                ditemukan = r;
                                break;
                            }
                        }

                        if (ditemukan == null) {

                            System.out.println(
                                "Error: No Rekening " +
                                cariNo +
                                " tidak ditemukan!"
                            );

                        } else {

                            akunAktif = ditemukan;

                            System.out.println(
                                "Berhasil! Akun aktif sekarang: " +
                                akunAktif.getNamaPemilik() +
                                " (" +
                                akunAktif.getNomorRekening() +
                                ")"
                            );
                        }
                    }

                    break;

                case 6:

                    if (akunAktif == null) {

                        System.out.println(
                            "Error: Anda belum memiliki rekening!"
                        );

                    } else {

                        System.out.print(
                            "Masukkan PIN Anda: "
                        );

                        String pinInput = input.nextLine();

                        if (akunAktif.otentikasi(pinInput)) {

                            akunAktif.cetakMutasi();

                        } else {

                            System.out.println(
                                "Akses Ditolak: PIN yang Anda masukkan salah!"
                            );
                        }
                    }

                    break;
                    
                case 0:

                    isRunning = false;

                    System.out.println(
                        "Sistem ditutup. Terima Kasih!"
                    );

                    break;


                default:

                    System.out.println(
                        "Pilihan tidak valid!"
                    );
            }
        }

        input.close();
    }
}