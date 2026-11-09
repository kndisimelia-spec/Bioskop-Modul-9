package com.mycompany.bioskop;

public class Pelanggan {

    private String idPelanggan;
    private String nama;

    public Pelanggan(String idPelanggan, String nama) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void tampilkanInfo() {
        System.out.println("ID Pelanggan : " + idPelanggan);
        System.out.println("Nama        : " + nama);
    }
}