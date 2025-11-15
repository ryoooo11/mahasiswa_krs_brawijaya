import java.util.ArrayList;
import java.util.Scanner;

class MataKuliah {
    private String kode;
    private String nama;
    private int sks;

    public MataKuliah(String kode, String nama, int sks) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public int getSks() { return sks; }
}

class Mahasiswa {
    private String nim;
    private String nama;
    private ArrayList<MataKuliah> krs = new ArrayList<>();
    private int totalSks = 0;

    public Mahasiswa(String nim, String nama) {
        this.nim = nim;
        this.nama = nama;
    }

    public boolean tambahMataKuliah(MataKuliah mk) {
        if (totalSks + mk.getSks() > 24) return false;
        krs.add(mk);
        totalSks += mk.getSks();
        return true;
    }

    public void cetakKRS(String namaPDA) {

        System.out.println("====================================================\n");
        System.out.println("NIM   : " + nim);
        System.out.println("Nama  : " + nama);
        System.out.println("Daftar Mata Kuliah:\n");

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-4s %-12s %-45s %4s\n",
                "No", "Kode", "Nama Mata Kuliah", "SKS");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < krs.size(); i++) {
            MataKuliah mk = krs.get(i);

            System.out.printf("%-4d %-12s %-45s %4d\n",
                    (i + 1),
                    mk.getKode(),
                    mk.getNama(),
                    mk.getSks()
            );
        }

        System.out.println("--------------------------------------------------------------------------\n");
        System.out.println("Total SKS: " + totalSks + "\n");

        // TTD tengah
        System.out.println("                      Tanda Tangan Dosen Penasehat Akademik\n");
        System.out.println("                              " + namaPDA);
        System.out.println("====================================================");
    }
}

public class KRSMAHASISWA {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan NIM: ");
        String nim = sc.nextLine();

        System.out.print("Masukkan Nama: ");
        String nama = sc.nextLine();

        Mahasiswa mhs = new Mahasiswa(nim, nama);

        while (true) {
            System.out.print("\nMasukkan kode mata kuliah (ketik 0 untuk berhenti): ");
            String kode = sc.nextLine();

            if (kode.equals("0")) break;

            System.out.print("Masukkan nama mata kuliah: ");
            String namaMK = sc.nextLine();

            System.out.print("Masukkan jumlah SKS: ");
            int sks = Integer.parseInt(sc.nextLine());

            MataKuliah mk = new MataKuliah(kode, namaMK, sks);

            if (mhs.tambahMataKuliah(mk)) {
                System.out.println(namaMK + " berhasil ditambahkan.");
            } else {
                System.out.println("Gagal! Total SKS melebihi 24.");
            }
        }

        System.out.print("\nMasukkan nama DPA: ");
        String namaDPA = sc.nextLine();

        mhs.cetakKRS(namaDPA);
    }
}
