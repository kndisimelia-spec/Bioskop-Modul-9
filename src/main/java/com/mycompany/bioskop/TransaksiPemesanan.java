package com.mycompany.bioskop;

import java.io.FileWriter;
import java.io.IOException;

public class TransaksiPemesanan {

    public void prosesTransaksi(Pelanggan pelanggan, Film film) {

        System.out.println("===== TRANSAKSI PEMESANAN =====");

        System.out.println("Nama Pelanggan : " + pelanggan.getNama());
        System.out.println("Film           : " + film.getJudul());
        System.out.println("Jam Tayang     : " + film.getJamTayang());

        System.out.println("Pemesanan tiket berhasil!");

        // Menulis riwayat transaksi ke file teks
        try {

            FileWriter pipaTulis = new FileWriter(
                    "log_transaksi.txt", true
            );

            pipaTulis.write("===== RIWAYAT PEMESANAN =====\n");
            pipaTulis.write("Nama Pelanggan : "
                    + pelanggan.getNama() + "\n");
            pipaTulis.write("Film           : "
                    + film.getJudul() + "\n");
            pipaTulis.write("Jam Tayang     : "
                    + film.getJamTayang() + "\n");
            pipaTulis.write("Status         : Berhasil\n");
            pipaTulis.write("-----------------------------\n");

            pipaTulis.close();

            System.out.println("Riwayat transaksi berhasil disimpan.");

        } catch (IOException e) {

            System.out.println("Gagal menulis log: " + e.getMessage());
        }
    }
}