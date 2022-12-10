package com.example.aplicacionpelis.modelo;

import com.example.aplicacionpelis.R;

public enum Genero {
    TERROR ("Terror", R.drawable.terror),
    ACCION("Accion", R.drawable.accion),
    COMEDIA ("Comedia", R.drawable.comedia),
    CIENCIA_FICCION ("Ciencia Ficción", R.drawable.cienciaficcion),
    AVENTURAS ("Aventura", R.drawable.aventuras);
    private final String texto;
    private final int imagen;
    Genero(String texto, int recurso) {
        this.texto = texto;
        this.imagen = recurso;
    }
    public String getTexto() {
        return texto;
    }
    public int getImagen() {
        return imagen;
    }
    public static String[] getNombres() {
        String[] resultado = new String[Genero.values().length];
        for (Genero tipo : Genero.values()) {
            resultado[tipo.ordinal()] = tipo.texto;
        }
        return resultado;
    }

}
