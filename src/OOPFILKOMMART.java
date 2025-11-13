import java.util.*;
import java.time.LocalDate;

class Barang {
    private String nama;
    private int harga;
    private int jumlah;

    public Barang(String nama, int jumlah) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.harga = tentukanHarga(nama.toLowerCase());
    }

    private int tentukanHarga(String namaLower) {
        if (namaLower.contains("beras")) return 55000;
        if (namaLower.contains("gula")) return 10000;
        if (namaLower.contains("minyak") || namaLower.contains("goreng")) return 56000;
        if (namaLower.contains("sabun")) return 15000;
        if (namaLower.contains("telur")) return 2500;
        System.out.println("Harga tidak ditemukan untuk '" + nama + "', diset 0.");
        return 0;
    }

    public int getTotal() {
        return harga * jumlah;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public int getJumlah() {
        return jumlah;
    }
}

class Transaksi {
    private ArrayList<Barang> daftarBarang = new ArrayList<>();
    private String kasir, pembeli;
    private int jumlahBayar;

    public void tambahBarang(Barang b) {
        daftarBarang.add(b);
    }

    public void setKasir(String kasir) {
        this.kasir = kasir;
    }

    public void setPembeli(String pembeli) {
        this.pembeli = pembeli;
    }

    public void setJumlahBayar(int jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public int getSubtotal() {
        int subtotal = 0;
        for (Barang b : daftarBarang) subtotal += b.getTotal();
        return subtotal;
    }

    public int getDiskon() {
        int subtotal = getSubtotal();
        if (subtotal >= 500000) return subtotal * 20 / 100;
        if (subtotal >= 100000) return subtotal * 10 / 100;
        if (subtotal >= 50000) return subtotal * 5 / 100;
        return 0;
    }

    public int getTotalBelanja() {
        return getSubtotal() - getDiskon();
    }

    public void cetakStruk() {
        Random rand = new Random();
        System.out.println("\n==========================================");
        System.out.println("        Ananda Satrio Rashendrya");
        System.out.println("            255150407111019");
        System.out.println("          TELP. 0878-7646-9257");
        System.out.println("==========================================");
        System.out.printf("Tanggal       : %26s\n", LocalDate.now());
        System.out.printf("Order ID      : %26s\n", "FM" + (rand.nextInt(900) + 100));
        System.out.printf("Kasir         : %26s\n", kasir);
        System.out.println("------------------------------------------");
        System.out.printf("%-14s %24s\n", "Collected by", kasir);
        System.out.printf("%-14s %24s\n", "", pembeli);
        System.out.println("------------------------------------------");

        for (Barang b : daftarBarang) {
            System.out.println(b.getNama());
            System.out.printf("%-20s %21d\n", b.getJumlah() + " x @ " + b.getHarga(), b.getTotal());
        }

        System.out.println("------------------------------------------");
        System.out.printf("%-30s Rp. %7d\n", "Subtotal", getSubtotal());
        System.out.printf("%-30s Rp. %7d\n", "Diskon", getDiskon());
        System.out.println("------------------------------------------");
        System.out.printf("%-30s Rp. %7d\n", "Total Belanja", getTotalBelanja());
        System.out.printf("%-30s Rp. %7d\n", "Jumlah Bayar", jumlahBayar);
        System.out.println("------------------------------------------");
        System.out.printf("%-29s Rp. %7d\n", "Kembalian", jumlahBayar - getTotalBelanja());
        System.out.println("------------------------------------------");
        System.out.printf("%37s\n", "Terima Kasih Telah Berbelanja di");
        System.out.printf("%27s\n", "Ananda Satrio");
    }
}

public class OOPFILKOMMART {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Transaksi trx = new Transaksi();

        System.out.println("Jumlah barang yang dibeli ? ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Nama Barang " + (i + 1) + " : ");
            String nama = sc.nextLine();
            System.out.print("Jumlah Barang : ");
            int jumlah = sc.nextInt();
            sc.nextLine();
            trx.tambahBarang(new Barang(nama, jumlah));
        }

        System.out.print("Nama Kasir : ");
        trx.setKasir(sc.nextLine());
        System.out.print("Nama Pembeli : ");
        trx.setPembeli(sc.nextLine());
        System.out.print("Jumlah Bayar : ");
        trx.setJumlahBayar(sc.nextInt());

        trx.cetakStruk();
    }
}
