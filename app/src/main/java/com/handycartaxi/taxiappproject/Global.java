package com.handycartaxi.taxiappproject;

/**
 * Created by Joan on 05-May-15.
 */
public class Global {

    public static final String IP = "192.168.137.1";
    public static int PEDIDO;
    public static int ID_ASIGNADO=0;
    public static String TAXI_NAME;
    public static int UNIDAD;
    public static String COLOR_VEHICULO;
    public static int ID_FOTO_TAXISTA;
    public static int TIEMPO;


    public static void setPEDIDO(int PEDIDO) {
        Global.PEDIDO = PEDIDO;
    }

    public static void setID_ASIGNADO(int ID_ASIGNADO) {
        Global.ID_ASIGNADO = ID_ASIGNADO;
    }

    public static void setTAXI_NAME(String TAXI_NAME) {
        Global.TAXI_NAME = TAXI_NAME;
    }

    public static void setUNIDAD(int UNIDAD) {
        Global.UNIDAD = UNIDAD;
    }

    public static void setCOLOR_VEHICULO(String COLOR_VEHICULO) {
        Global.COLOR_VEHICULO = COLOR_VEHICULO;
    }

    public static void setID_FOTO_TAXISTA(int ID_FOTO_TAXISTA) {
        Global.ID_FOTO_TAXISTA = ID_FOTO_TAXISTA;
    }

    public static void setTIEMPO(int TIEMPO) {
        Global.TIEMPO = TIEMPO;
    }
}
