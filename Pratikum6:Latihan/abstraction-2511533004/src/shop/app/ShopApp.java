
package shop.app;

import java.util.List;

import shop.model.Address;
import shop.model.Order;
import shop.model.Product;
import shop.model.PhysicalProduct;
import shop.model.DigitalProduct;
import shop.payment.PaymentMethod;
import shop.payment.EWalletPayment;
import shop.payment.BankTransferPayment;
import shop.payment.CreditCardPayment;
import shop.service.CheckoutService;
import shop.shipping.ShippingService;
import shop.shipping.RegularShipping;
import shop.shipping.ExpressShipping;

public class ShopApp {

    public static void main(String[] args) {

        Product coffee = new PhysicalProduct(
                "KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300);

        Product rendang = new PhysicalProduct(
                "RDG-002", "Rendang Kemasan 250g", 120_000, 400);

        Product ebook = new DigitalProduct(
                "EBK-003", "E-book Resep Kue Nusantara", 50_000,
                "https://example.com/ebook");

        List<Product> cart = List.of(coffee, rendang, ebook);

        System.out.println("== Katalog ==");
        for (Product product : cart) {
            System.out.println(product.getInfo());
        }

        CheckoutService checkout = new CheckoutService();
        ShippingService regular = new RegularShipping();
        ShippingService express = new ExpressShipping();

        System.out.println("\n== Ringkasan ==");
        System.out.println("Subtotal      : Rp" + checkout.subtotal(cart));
        System.out.println("Berat kiriman : " + checkout.totalWeight(cart) + " g");
        System.out.println("Total Reguler : Rp" + checkout.total(cart, regular));
        System.out.println("Total Ekspres : Rp" + checkout.total(cart, express));

        EWalletPayment wallet = new EWalletPayment("DompetKu", 100_000);
        BankTransferPayment transfer = new BankTransferPayment("Bank Nagari");
        CreditCardPayment card = new CreditCardPayment("Siti Rahma", 1_000_000);

        System.out.println("\n== Pembayaran ==");
        System.out.println("E-Wallet DompetKu berhasil? "
                + wallet.pay(60_000));
        System.out.println("Sisa saldo: Rp" + wallet.getBalance());

        System.out.println("Transfer Bank Nagari berhasil? "
                + transfer.pay(100_000));

        System.out.println("Kartu Kredit (Siti Rahma) berhasil? "
                + card.pay(200_000));

        System.out.println("Cicilan 3 bulan: Rp"
                + card.monthlyInstallment(100_000, 3));

        System.out.println("\n== Refund e-book ==");
        System.out.println("Refund e-wallet berhasil? "
                + wallet.refund(50_000));
        System.out.println("Saldo setelah refund: Rp" + wallet.getBalance());

        Address address = new Address(
                "Jl. Moh Hatta No. 10", "Padang", "25121");

        Order order = new Order("ORD-0001", address);
        order.addLine(coffee, 2);
        order.addLine(ebook, 1);

        Order.Receipt receipt = order.createReceipt();
        System.out.println("\n" + receipt.toText());

        PaymentMethod voucher = new PaymentMethod() {
            private long remaining = 50_000;

            @Override
            public String getName() {
                return "Voucher Promo Hari Ini";
            }

            @Override
            public boolean pay(long amount) {
                if (amount <= 0 || amount > remaining) {
                    return false;
                }

                remaining -= amount;
                return true;
            }
        };

        System.out.println("\n" + voucher.getName()
                + " untuk e-book berhasil? "
                + voucher.pay(ebook.getFinalPrice()));
    }
}
