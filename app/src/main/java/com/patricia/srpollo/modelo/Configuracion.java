package com.patricia.srpollo.modelo;

/**
 * Created by SPF1 on 3/27/2018.
 */

public class Configuracion {

    private int id;
    private String soda_whatsaap;
    private String lista_whatsaap;
    private String numero_columna;

    public Configuracion(String soda_whatsaap, String lista_whatsaap) {
        this.soda_whatsaap = soda_whatsaap;
        this.lista_whatsaap = lista_whatsaap;
    }

    public Configuracion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoda_whatsaap() {
        return soda_whatsaap;
    }

    public void setSoda_whatsaap(String soda_whatsaap) {
        this.soda_whatsaap = soda_whatsaap;
    }

    public String getLista_whatsaap() {
        return lista_whatsaap;
    }

    public void setLista_whatsaap(String lista_whatsaap) {
        this.lista_whatsaap = lista_whatsaap;
    }

    public String getNumero_columna() {
        return numero_columna;
    }

    public void setNumero_columna(String numero_columna) {
        this.numero_columna = numero_columna;
    }
}
