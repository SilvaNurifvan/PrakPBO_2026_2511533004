package pekan4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null; //null karna objek belum di inisialisasi
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		
		while (isRunning) {
			System.out.println("\nMenu Utama:");
			System.out.println("1.Buka Rekening Baru");
			System.out.println("2.Setor Tunai");
			System.out.println("3.Tarik tunai");
			System.out.println("4.Cek Informasi Rekening");
			System.out.println("5.Ganti Akun");
			System.out.println("6.Cetak Mutasi (Riwayat)");
			System.out.println("7.Simulasi Akhir Bulan (Khusus Tabungan)");
			System.out.println("0.Keluar");
			System.out.println("Pilih Menu: ");
			
			int pilihan = input.nextInt();
			input.nextLine(); //Membersihkan buffer enter
			
			switch(pilihan) {
			case 1:
				System.out.print("Masukan No Rekening: ");
				String no = input.nextLine();
				System.out.print("Masukan Nama Pemilik: ");
				String nama = input.nextLine();
				System.out.print("Masukan Saldo Awal: ");
				double saldo = input.nextDouble();
				input.nextLine();
				
				// minta input PIN
				System.out.print("Masukan PIN(6 digit):");
				String pin = input.nextLine();
				
				//Opsi Produk
				System.out.println("\nPiih Produk:");
				System.out.println("1.Tabungan Umum");
				System.out.println("2.Giro Bisnis");
				System.out.print("Piih Produk: ");
				int produk = input.nextInt();
				input.nextLine(); 
				Rekening rekeningBaru = null;
				if (produk == 1) {
					System.out.print("Masukan Suku Bunga (%): ");
					double sukuBunga = input.nextDouble();
					input.nextLine();
					
					rekeningBaru = new RekeningTabungan(no, nama, saldo, pin, sukuBunga);
					System.out.println("Rekening Tabungan Berhasil dibuat.");
				} else if (produk == 2) {
					System.out.println("Masukan Batas Overdraft: ");
					double batasOverdraft = input.nextDouble();
					input.nextLine();
					
					rekeningBaru = new RekeningGiro(no, nama, saldo, pin, batasOverdraft);
					System.out.println("Rekening Giro Berhasil dibuat.");
				} else {
					System.out.println("Pilihan produk tidak valid.");
					
				break;
				}
				
				daftarRekening.add(rekeningBaru);
				akunAktif = rekeningBaru;
			break;
			
			case 2:
				if (akunAktif == null) {
					System.out.println("Error: Mohon Maaf, Anda belum memiliki nomor rekening!");
				} else {
					System.out.print("Masukan nominal setor: ");
					double setor = input.nextDouble();
					input.nextLine();
					akunAktif.setorTunai(setor); //Memanggil Method
				}
				break;
		
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Mohon Maaf, Anda belum memiliki nomor rekening!");
				} else {
					
					//minta pin
					System.out.print("Masukan PIN: ");
					String pinTarik = input.nextLine();
					//Cek pin
					if (akunAktif.otentikasi(pinTarik)) {
						System.out.print("Masukan nominal tarik: ");
					double tarik = input.nextDouble();
					input.nextLine();
					akunAktif.tarikTunai(tarik); 
					}else {
						System.out.print("Akses Ditolak: PIN yang Anda masukkan salah!");
				}
				}
				break; 
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
				System.out.print("Masukan No Rekening: ");
				String cariNo = input.nextLine();
				boolean ditemukan = false;
				
				for (Rekening rekening : daftarRekening) {
					if (rekening.getNomorRekening().equals(cariNo)) {
						akunAktif = rekening;
						ditemukan = true;
						System.out.println("Akun Berhasil Diganti.");
						break;
					}
				}
				
				if (!ditemukan) {
					System.out.println("Rekening tidak ditemukan.");
				}
				break;
				
			case 6:
				if(akunAktif == null) {
					System.out.println("Error : Anda belum membuka rekening!");
				}else {
					 
					//Masukan PIN
					System.out.print("Masukan PIN: ");
					String pinMutasi = input.nextLine();
					//Cek PIN
					if (akunAktif.otentikasi(pinMutasi)) {
						akunAktif.cetakMutasi();
					}else {
						System.out.print("Akses Ditolak: PIN yang Anda masukkan salah!");
				}
				}
				break; 
			    
			case 7:
				if(akunAktif == null) {
					System.out.println("Error : Anda belum membuka rekening!");
				} else if (akunAktif instanceof RekeningTabungan) {

                    // DOWNCASTING
                    RekeningTabungan tabungan =
                            (RekeningTabungan) akunAktif;

                    tabungan.tambahBungaAkhirBulan();

                } else {

                    System.out.println(
                            "Gagal: Fitur bunga akhir bulan "
                            + "hanya berlaku untuk Rekening Tabungan."
                    );
                }

                break;
					 
				
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
				
			default:
				System.out.println("Pilihan tidak valid!");
			}
		}
		input.close();
	}

}