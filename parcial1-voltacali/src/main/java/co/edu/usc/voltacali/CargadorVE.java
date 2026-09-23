package co.edu.usc.voltacali;

import java.util.Vector;
import java.util.ArrayList;

public class CargadorVE {

    enum TipoConector { TIPO_1, TIPO_2, CCS2, CHADEMO, GBT }
    enum TipoCargador { MURAL, PEDESTAL, RAPIDO_DC, ULTRARRAPIDO, PORTATIL, BIDIRECCIONAL_V2G }
    enum Ubicacion { CENTRO_COMERCIAL, UNIVERSIDAD, ESTACION_SERVICIO, PARQUEADERO_PUBLICO, RESIDENCIAL, HOTEL, TERMINAL, FLOTA_CORPORATIVA }
    

    private String fabricante;
    private int anioInstalacion;
    private int voltajeNominal;
    private TipoConector Tipoconector;
    private TipoCargador Tipocargador;
    private int numeroConectores;
    private int puestosParqueo;
    private double potenciaMaxima;
    private Ubicacion ubicacion;
    private double setPotenciaActual;

public String getFabricante() {
    return fabricante;
}

public void setFabricante(String fabricante) {
    this.fabricante = fabricante;
}

public int getAnioInstalacion() {
    return anioInstalacion;
}

public void setAnioInstalacion(int anioInstalacion) {
    this.anioInstalacion = anioInstalacion;
}

public int getVoltajeNominal() {
    return voltajeNominal;
}

public void setVoltajeNominal(int voltajeNominal) {
    this.voltajeNominal = voltajeNominal;
}

public TipoConector getTipoconector() {
    return Tipoconector;
}

public void setTipoconector(TipoConector Tipoconector) {
    this.Tipoconector = Tipoconector;
}

public TipoCargador getTipocargador() {
    return Tipocargador;
}

public void setTipocargador(TipoCargador Tipocargador) {
    this.Tipocargador = Tipocargador;
}

public int getNumeroConectores() {
    return numeroConectores;
}

public void setNumeroConectores(int numeroConectores) {
    this.numeroConectores = numeroConectores;
}

public int getPuestosParqueo() {
    return puestosParqueo;
}

public void setPuestosParqueo(int puestosParqueo) {
    this.puestosParqueo = puestosParqueo;
}

public double getPotenciaMaxima() {
    return potenciaMaxima;
}

public void setPotenciaMaxima(double potenciaMaxima) {
    this.potenciaMaxima = potenciaMaxima;
}

public Ubicacion getUbicacion() {
    return ubicacion;
}

public void setUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
}

public double getSetPotenciaActual() {
    return setPotenciaActual;
}

public void setSetPotenciaActual(double setPotenciaActual) {
    this.setPotenciaActual = setPotenciaActual;
}


    
}