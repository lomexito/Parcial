package co.edu.usc.voltacali;

public class CargadorVE {

    enum TipoConector { TIPO_1, TIPO_2, CCS2, CHADEMO, GBT }
    enum TipoCargador { MURAL, PEDESTAL, RAPIDO_DC, ULTRARRAPIDO, PORTATIL, BIDIRECCIONAL_V2G }
    enum Ubicacion { CENTRO_COMERCIAL, UNIVERSIDAD, ESTACION_SERVICIO, PARQUEADERO_PUBLICO, RESIDENCIAL, HOTEL, TERMINAL, FLOTA_CORPORATIVA }
    private String fabricante;
    private int anioInstalacion;
    private int voltajeNominal;
    private TipoConector tipoConector;
    private TipoCargador tipoCargador;
    private int numeroConectores;
    private int puestosParqueo;
    private double potenciaMaxima;
    private Ubicacion ubicacion;
    private double potenciaActual;

    public CargadorVE(String fabricante, int anioInstalacion, int voltajeNominal, TipoConector tipoConector, TipoCargador tipoCargador, int numeroConectores, int puestosParqueo, double potenciaMaxima, Ubicacion ubicacion) {
        this.fabricante = fabricante;
        this.anioInstalacion = anioInstalacion;
        this.voltajeNominal = voltajeNominal;
        this.tipoConector = tipoConector;
        this.tipoCargador = tipoCargador;
        this.numeroConectores = numeroConectores;
        this.puestosParqueo = puestosParqueo;
        this.potenciaMaxima = potenciaMaxima;
        this.ubicacion = ubicacion;
        this.potenciaActual = 0;
    }

    public String getFabricante() { 
        return fabricante; }
    public void setFabricante(String fabricante) { 
        this.fabricante = fabricante; }

    public int getAnioInstalacion() { 
        return anioInstalacion; }
    public void setAnioInstalacion(int anioInstalacion) { 
        this.anioInstalacion = anioInstalacion; }

    public int getVoltajeNominal() { 
        return voltajeNominal; }
    public void setVoltajeNominal(int voltajeNominal) { 
        this.voltajeNominal = voltajeNominal; }

    public TipoConector getTipoConector() { 
        return tipoConector; }
    public void setTipoConector(TipoConector tipoConector) { 
        this.tipoConector = tipoConector; }

    public TipoCargador getTipoCargador() { 
        return tipoCargador; }
    public void setTipoCargador(TipoCargador tipoCargador) { 
        this.tipoCargador = tipoCargador; }

    public int getNumeroConectores() { 
        return numeroConectores; }
    public void setNumeroConectores(int numeroConectores) { 
        this.numeroConectores = numeroConectores; }

    public int getPuestosParqueo() { 
        return puestosParqueo; }
    public void setPuestosParqueo(int puestosParqueo) { 
        this.puestosParqueo = puestosParqueo; }

    public double getPotenciaMaxima() { 
        return potenciaMaxima; }
    public void setPotenciaMaxima(double potenciaMaxima) { 
        this.potenciaMaxima = potenciaMaxima; }

    public Ubicacion getUbicacion() { 
        return ubicacion; }
    public void setUbicacion(Ubicacion ubicacion) { 
        this.ubicacion = ubicacion; }

    public double getPotenciaActual() { 
        return potenciaActual; }

    public void setPotenciaActual(double potenciaActual) {
        if (potenciaActual < 0 || potenciaActual > potenciaMaxima) {
            System.out.println("[ERROR] setPotenciaActual: valor fuera de rango [0, " + potenciaMaxima + "] kW.");
            return;
        }
        this.potenciaActual = potenciaActual;
    }
    
    }

    


    
