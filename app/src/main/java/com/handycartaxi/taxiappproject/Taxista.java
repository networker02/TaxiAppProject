package com.handycartaxi.taxiappproject;

/**
 * Created by Joan on 04-May-15.
 */
public class Taxista {




    private int taxistaFoto;
    private String taxistaNombre;
    private int taxistaUnidad;
    private String taxistaMatricula;
    private String taxistaVehiculo;
    private int idFoto;


    public Taxista (int taxistaFoto, String taxistaMatricula){
        this.taxistaFoto = taxistaFoto;
        this.taxistaMatricula = taxistaMatricula;
    }

    public Taxista(int taxistaFoto, String taxistaNombre, int taxistaUnidad, String taxistaMatricula, String taxistaVehiculo){

        this.taxistaFoto = taxistaFoto;
        this.taxistaNombre = taxistaNombre;
        this.taxistaUnidad = taxistaUnidad;
        this.taxistaMatricula = taxistaMatricula;
        this.taxistaVehiculo = taxistaVehiculo;
    }

    public int getTaxistaFoto() {
        return taxistaFoto;
    }

    public String getTaxistaMatricula() {
        return taxistaMatricula;
    }

    public void setTaxistaFoto(int taxistaFoto) {
        this.taxistaFoto = taxistaFoto;
    }

    public void setTaxistaNombre(String taxistaNombre) {
        this.taxistaNombre = taxistaNombre;
    }

    public void setTaxistaUnidad(int taxistaUnidad) {
        this.taxistaUnidad = taxistaUnidad;
    }

    public void setTaxistaMatricula(String taxistaMatricula) {
        this.taxistaMatricula = taxistaMatricula;
    }

    public void setTaxistaVehiculo(String taxistaVehiculo) {
        this.taxistaVehiculo = taxistaVehiculo;
    }
}
