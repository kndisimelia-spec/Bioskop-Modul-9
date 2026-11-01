package com.mycompany.bioskop;

public class Film2D extends Film
        implements DapatDinilai, DapatDipesan {

    private int studio;

    public Film2D(
            String judul,
            int nomorFilm,
            String jamTayang,
            int studio) {

        super(judul, nomorFilm, jamTayang);

        this.studio = studio;
    }

    public int getStudio() {
        return studio;
    }

    public void setStudio(int studio) {
        this.studio = studio;
    }

    @Override
    public void tampilkanInfo() {

        System.out.println("===== FILM 2D =====");

        super.tampilkanInfo();

        System.out.println("Studio     : " + studio);
    }

    @Override
    public void tampilkanJenisFilm() {

        System.out.println("Jenis Film : 2D");
    }

    @Override
    public void beriRating() {

        System.out.println(
                "Film " + getJudul()
                + " dapat diberi rating.");
    }

    @Override
    public void prosesPemesanan() {

        System.out.println(
                "Pemesanan tiket film 2D berhasil diproses.");
    }
}