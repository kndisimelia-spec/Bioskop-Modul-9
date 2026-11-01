package com.mycompany.bioskop;

public class FilmPremium extends Film
        implements DapatDinilai, DapatDipesan {

    private int studio;

    public FilmPremium(
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

        System.out.println("===== FILM PREMIUM =====");

        super.tampilkanInfo();

        System.out.println("Studio     : " + studio);
    }

    @Override
    public void tampilkanJenisFilm() {

        System.out.println("Jenis Film : Premium");
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
                "Pemesanan tiket film Premium berhasil diproses.");
    }
}