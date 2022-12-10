package com.example.aplicacionpelis.modelo;

public class Pelicula {
    private String nombrePelicula;
    private String director;
    private String sinopsis;
    private String paisOrigen;
    private int anioLanzamiento;
    private Genero tipoGenero;
    public Pelicula() {
        tipoGenero=Genero.ACCION;
    }
    public String getNombrePelicula() {
        return nombrePelicula;
    }
    public void setNombrePelicula(String nombrePelicula) {
        this.nombrePelicula = nombrePelicula;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public String getPaisOrigen() {
        return paisOrigen;
    }
    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }
    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }
    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }
    public Genero getTipoGenero() {
        return tipoGenero;
    }
    public void setTipoGenero(Genero tipoGenero) {
        this.tipoGenero = tipoGenero;
    }
    @Override
    public String toString() {
        return "Pelicula{" +
                "nombrePelicula='" + nombrePelicula + '\'' +
                ", director='" + director + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", anioLanzamiento=" + anioLanzamiento +
                ", tipoGenero=" + tipoGenero +
                '}';
    }

}
