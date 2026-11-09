package com.mycompany.bioskop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Bioskop {

    private ArrayList<Film> daftarFilm;
    private HashMap<String, Pelanggan> daftarPelanggan;

    private FasilitasBioskop fasilitas;
    private TransaksiPemesanan transaksi;

    public Bioskop() {

        daftarFilm = new ArrayList<>();
        daftarPelanggan = new HashMap<>();

        // COMPOSITION
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
            System.out.println("6. Keluar");
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
                        System.out.println("Terima kasih!");
                        break;

                    default:
                        System.out.println("Pilihan tidak tersedia.");
                }

            } catch (InputMismatchException e) {

                System.out.println("Input harus berupa angka!");
                input.nextLine();
            }

        } while (pilihan != 6);

        input.close();
    }

    public static void main(String[] args) {

        // Membuat objek Bioskop
        Bioskop bioskop = new Bioskop();

        // Membuat objek Film di luar kelas Bioskop
        Film film1 = new Film2D(
            "Avengers", 1, "10:00", 1
        );

        Film film2 = new Film3D(
            "Avatar", 2, "13:00", 2
        );

        Film film3 = new FilmPremium(
            "Interstellar", 3, "16:00", 1
        );

        // Menambahkan film ke Bioskop
        bioskop.tambahFilm(film1);
        bioskop.tambahFilm(film2);
        bioskop.tambahFilm(film3);

        // Membuat objek Pelanggan
        Pelanggan pelanggan1 = new Pelanggan(
            "P001", "Budi"
        );

        Pelanggan pelanggan2 = new Pelanggan(
            "P002", "Siti"
        );

        Pelanggan pelanggan3 = new Pelanggan(
            "P003", "Andi"
        );

        // Menambahkan pelanggan ke Bioskop
        bioskop.tambahPelanggan(pelanggan1);
        bioskop.tambahPelanggan(pelanggan2);
        bioskop.tambahPelanggan(pelanggan3);

        // Menjalankan menu
        bioskop.menu();
    }
}