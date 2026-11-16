package com.mycompany.bioskop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class Bioskop {

    private ArrayList<Film> daftarFilm;
    private HashMap<String, Pelanggan> daftarPelanggan;

    private FasilitasBioskop fasilitas;
    private TransaksiPemesanan transaksi;

    public Bioskop() {

        daftarFilm = new ArrayList<>();
        daftarPelanggan = new HashMap<>();

        fasilitas = new FasilitasBioskop();

        transaksi = new TransaksiPemesanan();
    }

    // AGGREGATION
    public void tambahFilm(Film film) {
        daftarFilm.add(film);
    }

    public void tambahPelanggan(Pelanggan pelanggan) {
        daftarPelanggan.put(
                pelanggan.getIdPelanggan(), pelanggan
        );
    }

    public void tampilkanFilm() {

        System.out.println("===== DAFTAR FILM =====");

        for (Film film : daftarFilm) {
            film.tampilkanInfo();
            film.tampilkanJenisFilm();
            System.out.println("----------------------");
        }
    }

    public void tampilkanPelanggan() {

        System.out.println("===== DAFTAR PELANGGAN =====");

        for (Pelanggan pelanggan : daftarPelanggan.values()) {
            pelanggan.tampilkanInfo();
            System.out.println("----------------------");
        }
    }

    public Film cariFilm(int nomorFilm)
            throws FilmTidakDitemukanException {

        for (Film film : daftarFilm) {
            if (film.getNomorFilm() == nomorFilm) {
                return film;
            }
        }

        throw new FilmTidakDitemukanException(
                "Film tidak ditemukan."
        );
    }

    public Pelanggan cariPelanggan(String idPelanggan) {
        return daftarPelanggan.get(idPelanggan);
    }

    // ASSOCIATION
    public void pesanTiket(Pelanggan pelanggan, Film film) {
        transaksi.prosesTransaksi(pelanggan, film);
    }

    public void tampilkanFasilitas() {
        fasilitas.tampilkanFasilitas();
    }

    // SERIALIZATION
    public void simpanDataKeFile() {

        try {

            FileOutputStream fileKeluar =
                    new FileOutputStream("data_bioskop.dat");

            ObjectOutputStream mesinPembeku =
                    new ObjectOutputStream(fileKeluar);

            mesinPembeku.writeObject(daftarFilm);
            mesinPembeku.writeObject(daftarPelanggan);

            mesinPembeku.close();

            System.out.println("Data bioskop berhasil disimpan!");

        } catch (Exception e) {

            System.out.println("Gagal menyimpan data: "
                    + e.getMessage());
        }
    }

    // DESERIALIZATION
    public boolean muatDataDariFile() {

        File file = new File("data_bioskop.dat");

        if (!file.exists()) {
            System.out.println("File data belum tersedia.");
            return false;
        }

        try {

            FileInputStream fileMasuk =
                    new FileInputStream("data_bioskop.dat");

            ObjectInputStream mesinPembangkit =
                    new ObjectInputStream(fileMasuk);

            daftarFilm = (ArrayList<Film>)
                    mesinPembangkit.readObject();

            daftarPelanggan = (HashMap<String, Pelanggan>)
                    mesinPembangkit.readObject();

            mesinPembangkit.close();

            System.out.println("Data bioskop berhasil dimuat!");

            return true;

        } catch (Exception e) {

            System.out.println("Gagal memuat data: "
                    + e.getMessage());

            return false;
        }
    }

    // MEMBACA FILE LOG
    public void bacaLogTransaksi() {

        try {

            File fileYangDibaca =
                    new File("log_transaksi.txt");

            Scanner mesinBaca =
                    new Scanner(fileYangDibaca);

            System.out.println("===== LOG TRANSAKSI =====");

            while (mesinBaca.hasNextLine()) {

                String barisTeks = mesinBaca.nextLine();

                System.out.println(barisTeks);
            }

            mesinBaca.close();

        } catch (FileNotFoundException e) {

            System.out.println("File log transaksi belum ditemukan.");
        }
    }

    // MENU PROGRAM
    public void menu() {

        Scanner input = new Scanner(System.in);

        int pilihan = 0;

        do {

            System.out.println("\n===== SISTEM BIOSKOP KITA =====");
            System.out.println("1. Lihat Daftar Film");
            System.out.println("2. Cari Film");
            System.out.println("3. Lihat Daftar Pelanggan");
            System.out.println("4. Pesan Tiket");
            System.out.println("5. Lihat Fasilitas Bioskop");
            System.out.println("6. Simpan Data");
            System.out.println("7. Muat Data");
            System.out.println("8. Lihat Log Transaksi");
            System.out.println("9. Keluar");

            System.out.print("Pilih menu: ");

            try {

                pilihan = input.nextInt();

                switch (pilihan) {

                    case 1:
                        tampilkanFilm();
                        break;

                    case 2:

                        System.out.print("Masukkan Nomor Film: ");
                        int nomor = input.nextInt();

                        try {

                            Film film = cariFilm(nomor);

                            System.out.println("Film ditemukan!");
                            film.tampilkanInfo();
                            film.tampilkanJenisFilm();

                        } catch (FilmTidakDitemukanException e) {

                            System.out.println(e.getMessage());
                        }

                        break;

                    case 3:
                        tampilkanPelanggan();
                        break;

                    case 4:

                        input.nextLine();

                        System.out.print("Masukkan ID Pelanggan: ");
                        String id = input.nextLine();

                        Pelanggan pelanggan = cariPelanggan(id);

                        if (pelanggan == null) {

                            System.out.println("Pelanggan tidak ditemukan!");

                        } else {

                            System.out.print("Masukkan Nomor Film: ");
                            int nomorFilm = input.nextInt();

                            try {

                                Film film = cariFilm(nomorFilm);

                                pesanTiket(pelanggan, film);

                            } catch (FilmTidakDitemukanException e) {

                                System.out.println(e.getMessage());
                            }
                        }

                        break;

                    case 5:
                        tampilkanFasilitas();
                        break;

                    case 6:
                        simpanDataKeFile();
                        break;

                    case 7:
                        muatDataDariFile();
                        break;

                    case 8:
                        bacaLogTransaksi();
                        break;

                    case 9:

                        simpanDataKeFile();

                        System.out.println("Terima kasih!");

                        break;

                    default:
                        System.out.println("Pilihan tidak tersedia.");
                }

            } catch (InputMismatchException e) {

                System.out.println("Input harus berupa angka!");

                input.nextLine();
            }

        } while (pilihan != 9);

        input.close();
    }

    public static void main(String[] args) {

        Bioskop bioskop = new Bioskop();

        // Memuat data saat program pertama dijalankan
        boolean dataTersedia = bioskop.muatDataDariFile();

        // Jika belum ada file, gunakan data awal
        if (!dataTersedia) {

            Film film1 = new Film2D(
                    "Avengers", 1, "10:00", 1
            );

            Film film2 = new Film3D(
                    "Avatar", 2, "13:00", 2
            );

            Film film3 = new FilmPremium(
                    "Interstellar", 3, "16:00", 1
            );

            bioskop.tambahFilm(film1);
            bioskop.tambahFilm(film2);
            bioskop.tambahFilm(film3);

            Pelanggan pelanggan1 = new Pelanggan(
                    "P001", "Budi"
            );

            Pelanggan pelanggan2 = new Pelanggan(
                    "P002", "Siti"
            );

            Pelanggan pelanggan3 = new Pelanggan(
                    "P003", "Andi"
            );

            bioskop.tambahPelanggan(pelanggan1);
            bioskop.tambahPelanggan(pelanggan2);
            bioskop.tambahPelanggan(pelanggan3);
        }

        bioskop.menu();
    }
}