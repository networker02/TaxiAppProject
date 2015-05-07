package com.handycartaxi.taxiappproject;

import java.io.Serializable;

/**
 * Created by Joan on 12-Apr-15.
 */
public class Compania implements Serializable {


    private int logoId;
    private int id_compania;
    private String nombre_compania;


    public Compania(String nombre_compania, int logoId, int id_compania){
        this.logoId=logoId;
        this.nombre_compania = nombre_compania;
        this.id_compania = id_compania;
    }

    public int getLogoId(){
        return logoId;
    }

    public String getNombre_compania(){
        return nombre_compania;
    }

    public int id_compania(){
        return id_compania;
    }



}
