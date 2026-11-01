package com.mycompany.bioskop;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class Bioskop {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // ArrayList untuk menyimpan data film
        ArrayList<Film> daftarFilm = new ArrayList<>();

        // HashMap untuk menyimpan data pelanggan
        HashMap<String, String> dataPelanggan = new HashMap<>();

        // Data awal film
        daftarFilm.add(
                new Film2D("Avengers", 101, "12:00", 1));

        daftarFilm.add(
                new Film3D("Avatar", 102, "15:00", 2));

        daftarFilm.add(
                new FilmPremium("Interstellar", 103, "19:00", 3));

        // Data awal pelanggan
        dataPelanggan.put("P001", "Budi");
        dataPelanggan.put("P002", "Siti");
        dataPelanggan.put("P003", "Andi");

        boolean isRunning = true;

        System.out.println("================================");
        System.out.println("       SISTEM BIOSKOP KITA");
        System.out.println("================================");

        while (isRunning) {

            try {

                System.out.println();
                System.out.println("1. Tambah Jadwal Film");
                System.out.println("2. Jadwal Film");
                System.out.println("3. Cari Film");
                System.out.println("4. Pesan Tiket");
                System.out.println("5. Beri Rating");
                System.out.println("6. Kelola Pelanggan");
                System.out.println("7. Keluar");
                System.out.print("Pilih menu: ");

                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {

                    case 1:

                        System.out.println();
                        System.out.println("===== TAMBAH JADWAL FILM =====");

                        System.out.print("Judul film: ");
                        String judul = scanner.nextLine();

                        System.out.print("Nomor film: ");
                        int nomorFilm = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Jam tayang: ");
                        String jamTayang = scanner.nextLine();

                        System.out.println();
                        System.out.println("Pilih jenis film:");
                        System.out.println("1. Film 2D");
                        System.out.println("2. Film 3D");
                        System.out.println("3. Film Premium");
                        System.out.print("Pilih: ");

                        int jenisFilm = scanner.nextInt();
                        scanner.nextLine();

                        if (jenisFilm >= 1 && jenisFilm <= 3) {

                            System.out.print("Nomor studio: ");
                            int studio = scanner.nextInt();
                            scanner.nextLine();

                            if (jenisFilm == 1) {

                                daftarFilm.add(
                                        new Film2D(
                                                judul,
                                                nomorFilm,
                                                jamTayang,
                                                studio));

                            } else if (jenisFilm == 2) {

                                daftarFilm.add(
                                        new Film3D(
                                                judul,
                                                nomorFilm,
                                                jamTayang,
                                                studio));

                            } else {

                                daftarFilm.add(
                                        new FilmPremium(
                                                judul,
                                                nomorFilm,
                                                jamTayang,
                                                studio));
                            }

                            System.out.println(
                                    "Jadwal film berhasil ditambahkan.");

                        } else {

                            System.out.println(
                                    "Jenis film tidak tersedia.");
                        }

                        break;

                    case 2:

                        System.out.println();
                        System.out.println("===== JADWAL FILM =====");

                        System.out.println(
                                "Jumlah film: " + daftarFilm.size());

                        if (daftarFilm.isEmpty()) {

                            System.out.println(
                                    "Tidak ada jadwal film.");

                        } else {

                            for (int i = 0; i < daftarFilm.size(); i++) {

                                System.out.println();

                                daftarFilm.get(i).tampilkanInfo();
                            }
                        }

                        break;

                    case 3:

                        System.out.println();
                        System.out.println("===== CARI FILM =====");

                        System.out.print(
                                "Masukkan judul atau nomor film: ");

                        String kataKunci = scanner.nextLine();

                        try {

                            cariFilm(daftarFilm, kataKunci);

                        } catch (FilmTidakDitemukanException e) {

                            System.out.println(
                                    "Pesan Error: " + e.getMessage());

                        } finally {

                            System.out.println(
                                    "Proses pencarian film selesai.");
                        }

                        break;

                    case 4:

                        System.out.println();
                        System.out.println("===== PESAN TIKET =====");

                        System.out.print("Masukkan nomor film: ");
                        int nomorPesan = scanner.nextInt();
                        scanner.nextLine();

                        boolean ditemukan = false;

                        for (int i = 0; i < daftarFilm.size(); i++) {

                            if (daftarFilm.get(i).getNomorFilm()
                                    == nomorPesan) {

                                pesanTiket(daftarFilm.get(i));

                                ditemukan = true;
                                break;
                            }
                        }

                        if (!ditemukan) {

                            System.out.println(
                                    "Film tidak ditemukan.");
                        }

                        break;

                    case 5:

                        System.out.println();
                        System.out.println("===== BERI RATING =====");

                        System.out.print("Masukkan nomor film: ");
                        int nomorRating = scanner.nextInt();
                        scanner.nextLine();

                        boolean filmDitemukan = false;

                        for (int i = 0; i < daftarFilm.size(); i++) {

                            if (daftarFilm.get(i).getNomorFilm()
                                    == nomorRating) {

                                filmDitemukan = true;

                                if (daftarFilm.get(i)
                                        instanceof DapatDinilai) {

                                    DapatDinilai filmDinilai =
                                            (DapatDinilai) daftarFilm.get(i);

                                    filmDinilai.beriRating();

                                } else {

                                    System.out.println(
                                            "Film tidak dapat diberi rating.");
                                }

                                break;
                            }
                        }

                        if (!filmDitemukan) {

                            System.out.println(
                                    "Film tidak ditemukan.");
                        }

                        break;

                    case 6:

                        kelolaPelanggan(scanner, dataPelanggan);

                        break;

                    case 7:

                        isRunning = false;

                        System.out.println();
                        System.out.println(
                                "Terima kasih telah menggunakan "
                                + "Sistem Bioskop Kita.");

                        break;

                    default:

                        System.out.println(
                                "Pilihan tidak tersedia.");
                }

            } catch (InputMismatchException e) {

                System.out.println(
                        "Pesan Error: Input harus berupa angka!");

                scanner.nextLine();

            }
        }

        scanner.close();

        System.out.println(
                "Blok finally: Scanner berhasil ditutup.");
    }

    // Runtime Polymorphism dan Dynamic Binding

    public static void pesanTiket(Film film) {

        System.out.println();
        System.out.println("===== TIKET DIPESAN =====");

        film.tampilkanInfo();

        if (film instanceof DapatDipesan) {

            DapatDipesan pemesanan =
                    (DapatDipesan) film;

            pemesanan.prosesPemesanan();

        } else {

            System.out.println(
                    "Film ini tidak dapat dipesan.");
        }
    }

    // Exception Handling: throw dan throws

    public static void cariFilm(
            ArrayList<Film> daftarFilm,
            String kataKunci)
            throws FilmTidakDitemukanException {

        boolean ditemukan = false;

        if (kataKunci.trim().isEmpty()) {

            throw new FilmTidakDitemukanException(
                    "Kata kunci pencarian tidak boleh kosong.");
        }

        for (int i = 0; i < daftarFilm.size(); i++) {

            Film film = daftarFilm.get(i);

            if (film.getJudul().equalsIgnoreCase(kataKunci)
                    || String.valueOf(film.getNomorFilm())
                            .equals(kataKunci)) {

                film.tampilkanInfo();

                ditemukan = true;
            }
        }

        if (!ditemukan) {

            throw new FilmTidakDitemukanException(
                    "Film tidak ditemukan.");
        }
    }

    // Mengelola data pelanggan menggunakan HashMap

    public static void kelolaPelanggan(
            Scanner scanner,
            HashMap<String, String> dataPelanggan) {

        System.out.println();
        System.out.println("===== KELOLA PELANGGAN =====");

        System.out.println("1. Tambah Pelanggan");
        System.out.println("2. Cari Pelanggan");
        System.out.println("3. Tampilkan Semua Pelanggan");
        System.out.print("Pilih: ");

        int pilihan = scanner.nextInt();
        scanner.nextLine();

        if (pilihan == 1) {

            System.out.print("Masukkan ID pelanggan: ");
            String id = scanner.nextLine();

            System.out.print("Masukkan nama pelanggan: ");
            String nama = scanner.nextLine();

            dataPelanggan.put(id, nama);

            System.out.println(
                    "Data pelanggan berhasil ditambahkan.");

        } else if (pilihan == 2) {

            System.out.print("Masukkan ID pelanggan: ");
            String id = scanner.nextLine();

            if (dataPelanggan.containsKey(id)) {

                System.out.println(
                        "Nama pelanggan: " + dataPelanggan.get(id));

            } else {

                System.out.println(
                        "Pelanggan tidak ditemukan.");
            }

        } else if (pilihan == 3) {

            System.out.println();
            System.out.println("===== DAFTAR PELANGGAN =====");

            System.out.println(
                    "Jumlah pelanggan: " + dataPelanggan.size());

            for (String id : dataPelanggan.keySet()) {

                System.out.println(
                        "ID: " + id
                        + " | Nama: " + dataPelanggan.get(id));
            }

        } else {

            System.out.println(
                    "Pilihan tidak tersedia.");
        }
    }
}