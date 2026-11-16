package com.mycompany.bioskop;

import java.io.Serializable;

public class Pelanggan implements Serializable {

    private static final long serialVersionUID = 1L;

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