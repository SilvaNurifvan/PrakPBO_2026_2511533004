package pekan5;

public class RekeningGiro extends Rekening {
		private double batasOverdraft;
		
		public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, 
				double batasOverdraft) {
			//memanggil inisialisasi dasar superclass
			super(nomor, nama, saldoAwal, pinAwal);
			this.batasOverdraft = batasOverdraft;
		}
		
		// IMPLEMENTASI POLIMORFISME (OVERRIDING)
		@Override
		public boolean tarikTunai(double nominal) {
			if (nominal <= 0) {
				System.out.println("Gagal: Nominal tarik Giro harus lebih dari 0!");
				return false;
			}
			// Logika Khusus Giro: Boleh ditarik asalkan nominal tidak melebihi(saldo + batasOverdraft)
			else if (nominal > (saldo + batasOverdraft)) {
				System.out.println("Transaksi Gagal: Melebihi batas overdraft! Maksimal penarikan: Rp" + (saldo + batasOverdraft));
				return false;
			}
			else {
				saldo -= nominal; //Saldo bisa bernilai negatif di sini (memakan overdraft)
				
				String idTrx = "Trx-G-" + System.currentTimeMillis();
				riwayatTransaksi.add(new Transaksi(idTrx, "Debit Giro", nominal));
				
				System.out.println("Tarik tunai Giro Rp" + nominal + " berhasil. Sisa saldo: Rp" + saldo);
				return true;
			}
		}
		//Getter khusus Giro
		public double getBatasOverdraft() {
			return batasOverdraft;
		}
	}
 