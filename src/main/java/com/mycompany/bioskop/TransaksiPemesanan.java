package com.mycompany.bioskop;

public class TransaksiPemesanan {

    public void prosesTransaksi(Pelanggan pelanggan, Film film) {

        System.out.println("===== TRANSAKSI PEMESANAN =====");

        System.out.println("Nama Pelanggan : " + pelanggan.getNama());
        System.out.println("Film           : " + film.getJudul());
        System.out.println("Jam Tayang     : " + film.getJamTayang());

        System.out.println("Pemesanan tiket berhasil!");
    }
}