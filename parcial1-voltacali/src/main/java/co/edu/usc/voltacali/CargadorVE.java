package co.edu.usc.voltacali;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

    private final Vector<RegistroSesion> bitacora = new Vector<RegistroSesion>();

    private static int totalCargadores = 0;
    private static int contadorRegistros = 0;

    public static final double LIMITE_RED = 50.0;
    public static final double INCREMENTO_DEFECTO = 5.0;


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

    public CargadorVE(String fabricante, int anioInstalacion, double potenciaMaxima) {
        this(fabricante, anioInstalacion, 220, TipoConector.TIPO_2, TipoCargador.PEDESTAL, 1, 1, potenciaMaxima, Ubicacion.PARQUEADERO_PUBLICO); }
        
    public CargadorVE(CargadorVE otro) {
        this.fabricante = otro.fabricante;
        this.anioInstalacion = otro.anioInstalacion;
        this.voltajeNominal = otro.voltajeNominal;
        this.tipoConector = otro.tipoConector;
        this.tipoCargador = otro.tipoCargador;
        this.numeroConectores = otro.numeroConectores;
        this.puestosParqueo = otro.puestosParqueo;
        this.potenciaMaxima = otro.potenciaMaxima;
        this.ubicacion = otro.ubicacion;
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
    public boolean aumentarPotencia(double incremento) {
        double nueva = potenciaActual + incremento;
        if (nueva < 0 || nueva > potenciaMaxima) {
            System.out.println("[ERROR] aumentarPotencia: resultado " + nueva
                    + " kW fuera de rango [0, " + potenciaMaxima + "] kW.");
            return false;
        }
        potenciaActual = nueva;
        return true;
    }

    public boolean aumentarPotencia() {
        return aumentarPotencia(INCREMENTO_DEFECTO);
    }

    public void aumentarPotencia(double incremento, int veces) {
        for (int i = 0; i < veces; i++) {
            boolean ok = aumentarPotencia(incremento);
            if (!ok) {
                break;
            }
        }
    }

    public boolean reducirPotencia(double cantidad) {
        double nueva = potenciaActual - cantidad;
        if (nueva < 0 || nueva > potenciaMaxima) {
            System.out.println("[ERROR] reducirPotencia: resultado " + nueva
                    + " kW fuera de rango [0, " + potenciaMaxima + "] kW.");
            return false;
        }
        potenciaActual = nueva;
        return true;
    }

    public void cortarCarga() {
        potenciaActual = 0;
    }

    public double tiempoEstimadoCarga(double energiaKwh) {
        if (potenciaActual == 0) {
            System.out.println("[ERROR] tiempoEstimadoCarga: la potencia actual es 0 kW.");
            return -1;
        }
        return energiaKwh / potenciaActual;
    }

    public double tiempoEstimadoCarga(double energiaKwh, double potenciaProgramada) {
        if (potenciaProgramada == 0) {
            System.out.println("[ERROR] tiempoEstimadoCarga: la potencia programada es 0 kW.");
            return -1;
        }
        return energiaKwh / potenciaProgramada;
    }

    public double tiempoEstimadoCarga(double energiaKwh, int pausas, double minutosPorPausa) {
        if (potenciaActual == 0) {
            System.out.println("[ERROR] tiempoEstimadoCarga: la potencia actual es 0 kW.");
            return -1;
        }
        double base = energiaKwh / potenciaActual;
        double horasPausas = (pausas * minutosPorPausa) / 60.0;
        return base + horasPausas;
    }

    public void mostrar() {
        System.out.println("  Fabricante        : " + fabricante);
        System.out.println("  Anio instalacion  : " + anioInstalacion);
        System.out.println("  Voltaje nominal   : " + voltajeNominal + " V");
        System.out.println("  Tipo conector     : " + tipoConector);
        System.out.println("  Tipo cargador     : " + tipoCargador);
        System.out.println("  Numero conectores : " + numeroConectores);
        System.out.println("  Puestos parqueo   : " + puestosParqueo);
        System.out.printf("  Potencia maxima   : %.2f kW%n", potenciaMaxima);
        System.out.println("  Ubicacion         : " + ubicacion);
        System.out.printf("  Potencia actual   : %.2f kW%n", potenciaActual);
    }

    public void mostrar(boolean detallado) {
        mostrar();
        if (detallado) {
            System.out.println("  Bitacora completa (" + bitacora.size() + " registro(s)):");
            for (RegistroSesion r : bitacora) {
                System.out.println("    " + r.describir());
            }
        }    
    }

public Vector<RegistroSesion> getBitacora() {
        return bitacora;
    }


    public static CargadorVE[] filtrar(CargadorVE[] flota, TipoConector conector) {
        List<CargadorVE> resultado = new ArrayList<CargadorVE>();
        if (flota != null) {
            for (CargadorVE c : flota) {
                if (c != null && c.tipoConector == conector) {
                    resultado.add(c);
                }
            }
        }
        return resultado.toArray(new CargadorVE[0]);
    }

    public static CargadorVE[] filtrar(CargadorVE[] flota, TipoCargador tipo) {
        List<CargadorVE> resultado = new ArrayList<CargadorVE>();
        if (flota != null) {
            for (CargadorVE c : flota) {
                if (c != null && c.tipoCargador == tipo) {
                    resultado.add(c);
                }
            }
        }
        return resultado.toArray(new CargadorVE[0]);
    }

    public static CargadorVE[] filtrar(CargadorVE[] flota, Ubicacion ubicacion) {
        List<CargadorVE> resultado = new ArrayList<CargadorVE>();
        if (flota != null) {
            for (CargadorVE c : flota) {
                if (c != null && c.ubicacion == ubicacion) {
                    resultado.add(c);
                }
            }
        }
        return resultado.toArray(new CargadorVE[0]);
    }
    public static int[] contarPorTipo(CargadorVE[] flota) {
        int[] conteo = new int[TipoCargador.values().length];
        if (flota == null) {
            return conteo;
        }
        for (CargadorVE c : flota) {
            if (c != null && c.tipoCargador != null) {
                conteo[c.tipoCargador.ordinal()]++;
            }
        }
        return conteo;
    }

    public static int getTotalCargadores() {
        return totalCargadores;
    }

    public static int getContadorRegistros() {
        return contadorRegistros;
    }

    public static CargadorVE mayorPotencia(CargadorVE[] flota) {
        CargadorVE mayor = null;
        if (flota != null) {
            for (CargadorVE c : flota) {
                if (c == null) {
                    continue;
                }
                if (mayor == null || c.potenciaActual > mayor.potenciaActual) {
                    mayor = c;
                }
            }
        }
        return mayor;
    }

    public static double promedioPotencia(CargadorVE[] flota) {
        if (flota == null) {
            return 0;
        }
        double suma = 0;
        int cantidad = 0;
        for (CargadorVE c : flota) {
            if (c != null) {
                suma += c.potenciaActual;
                cantidad++;
            }
        }
        return cantidad == 0 ? 0 : suma / cantidad;
    }

    public static int excesosDePotenciaContratada(CargadorVE[] flota) {
        int contador = 0;
        if (flota == null) {
            return 0;
        }
        for (CargadorVE c : flota) {
            if (c == null) {
                continue;
            }
            for (RegistroSesion r : c.bitacora) {
                if (r.isValido() && r.getPotenciaActual() > LIMITE_RED) {
                    contador++;
                }
            }
        }
        return contador;
    }

    public class RegistroSesion {

        private final int numero;
        private final String fabricante;
        private final int anioInstalacion;
        private final double potenciaActual;
        private final String evento;
        private final boolean valido;

        public RegistroSesion(String evento, boolean valido) {
            this.fabricante = CargadorVE.this.fabricante;
            this.anioInstalacion = CargadorVE.this.anioInstalacion;
            this.potenciaActual = CargadorVE.this.potenciaActual;
            this.evento = evento;
            this.valido = valido;
            contadorRegistros++;
            this.numero = contadorRegistros;
        }

        public boolean isValido() { return valido; }
        public double getPotenciaActual() { return potenciaActual; }
        public String getEvento() { return evento; }
        public int getNumero() { return numero; }

        public String describir() {
            return "#" + numero + " [" + (valido ? "VALIDO" : "INVALIDO") + "] "
                    + fabricante + " (" + anioInstalacion + ") -> " + evento
                    + " | Potencia en ese momento: " + String.format("%.2f", potenciaActual) + " kW";
        }
    }

}

    


    
