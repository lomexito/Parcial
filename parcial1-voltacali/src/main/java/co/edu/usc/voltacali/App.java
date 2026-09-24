package co.edu.usc.voltacali;

import java.util.ArrayList;
import java.util.List;

import co.edu.usc.voltacali.CargadorVE.TipoCargador;
import co.edu.usc.voltacali.CargadorVE.TipoConector;
import co.edu.usc.voltacali.CargadorVE.Ubicacion;

public class App {

    public static void main(String[] args) {

        CargadorVE c1 = new CargadorVE("ABB", 2023, 400, TipoConector.CCS2,
                TipoCargador.RAPIDO_DC, 2, 2, 60, Ubicacion.UNIVERSIDAD);
        CargadorVE c2 = new CargadorVE("Siemens", 2022, 220, TipoConector.TIPO_2,
                TipoCargador.MURAL, 1, 1, 22, Ubicacion.CENTRO_COMERCIAL);
        CargadorVE c3 = new CargadorVE("Delta", 2024, 800, TipoConector.CCS2,
                TipoCargador.ULTRARRAPIDO, 2, 2, 150, Ubicacion.ESTACION_SERVICIO);
        CargadorVE c4 = new CargadorVE("Wallbox", 2021, 220, TipoConector.TIPO_2,
                TipoCargador.MURAL, 1, 1, 11, Ubicacion.RESIDENCIAL);
        CargadorVE c5 = new CargadorVE("Enel X", 2025, 22);

        CargadorVE[] flota = { c1, c2, c3, c4, c5 };

        System.out.println("=== Paso 2: sesion de carga sobre C1 ===");

        c1.setPotenciaActual(40);
        System.out.printf("[P01] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        c1.aumentarPotencia(15);
        System.out.printf("[P02] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        double p03 = c1.tiempoEstimadoCarga(66);
        System.out.printf("[P03] Tiempo estimado C1: %.2f h%n", p03);

        c1.aumentarPotencia(10);
        System.out.printf("[P04] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        c1.reducirPotencia(30);
        System.out.printf("[P05] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        double p06 = c1.tiempoEstimadoCarga(50, 2, 15);
        System.out.printf("[P06] Tiempo estimado C1 (con pausas): %.2f h%n", p06);

        double p07 = c1.tiempoEstimadoCarga(50, 40.0);
        System.out.printf("[P07] Tiempo estimado C1 (potencia programada): %.2f h%n", p07);

        c1.aumentarPotencia();
        System.out.printf("[P08] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        c1.aumentarPotencia(5, 3);
        System.out.printf("[P09] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        c1.reducirPotencia(50);
        System.out.printf("[P10] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        c1.cortarCarga();
        System.out.printf("[P11] Potencia C1: %.1f kW%n", c1.getPotenciaActual());

        double p12 = c1.tiempoEstimadoCarga(10);
        System.out.printf("[P12] Tiempo estimado C1 (potencia en cero): %.2f h%n", p12);

        System.out.println("=== Paso 3: operaciones sobre el resto de la flota ===");

        c2.setPotenciaActual(22);
        c3.setPotenciaActual(120);
        c4.aumentarPotencia(7.4);
        c5.aumentarPotencia(30);

        System.out.printf("[P13] Potencia final C2: %.1f kW%n", c2.getPotenciaActual());
        System.out.printf("[P13] Potencia final C3: %.1f kW%n", c3.getPotenciaActual());
        System.out.printf("[P13] Potencia final C4: %.1f kW%n", c4.getPotenciaActual());
        System.out.printf("[P13] Potencia final C5: %.1f kW%n", c5.getPotenciaActual());

        System.out.println("=== Paso 4: estadisticas y validaciones ===");

        int[] conteoPorTipo = CargadorVE.contarPorTipo(flota);
        System.out.println("[P14] Cantidad de cargadores por tipo:");
        for (TipoCargador t : TipoCargador.values()) {
            System.out.println("       " + t + ": " + conteoPorTipo[t.ordinal()]);
        }

        double promedio = CargadorVE.promedioPotencia(flota);
        System.out.printf("[P15] Promedio de potencia de la flota: %.2f kW%n", promedio);

        CargadorVE mayor = CargadorVE.mayorPotencia(flota);
        if (mayor != null) {
            System.out.printf("[P16] Mayor potencia: %s con %.2f kW%n",
                    mayor.getFabricante(), mayor.getPotenciaActual());
        } else {
            System.out.println("[P16] La flota no tiene cargadores validos.");
        }

        int excesos = CargadorVE.excesosDePotenciaContratada(flota);
        System.out.println("[P17] Excesos de potencia contratada (> LIMITE_RED): " + excesos);

        CargadorVE[] porConector = CargadorVE.filtrar(flota, TipoConector.TIPO_2);
        System.out.println("[P18] filtrar por TipoConector.TIPO_2 -> cantidad: " + porConector.length);
        for (CargadorVE c : porConector) {
            System.out.println("       fabricante: " + c.getFabricante());
        }

        CargadorVE[] porTipo = CargadorVE.filtrar(flota, TipoCargador.MURAL);
        System.out.println("[P18] filtrar por TipoCargador.MURAL -> cantidad: " + porTipo.length);
        for (CargadorVE c : porTipo) {
            System.out.println("       fabricante: " + c.getFabricante());
        }

        CargadorVE[] porUbicacion = CargadorVE.filtrar(flota, Ubicacion.UNIVERSIDAD);
        System.out.println("[P18] filtrar por Ubicacion.UNIVERSIDAD -> cantidad: " + porUbicacion.length);
        for (CargadorVE c : porUbicacion) {
            System.out.println("       fabricante: " + c.getFabricante());
        }

        System.out.println("[P19] c5.mostrar(false):");
        c5.mostrar(false);

        CargadorVE copia = new CargadorVE(c3);
        System.out.println("[P20] Copia de C3:");
        System.out.println("       fabricante: " + copia.getFabricante());
        System.out.printf("       potencia actual: %.2f kW%n", copia.getPotenciaActual());
        System.out.println("       tamano bitacora copia: " + copia.getBitacora().size());
        System.out.println("       getTotalCargadores(): " + CargadorVE.getTotalCargadores());

        System.out.println("[P21] c1.mostrar(true):");
        c1.mostrar(true);

        System.out.println("[P22] Valor de contadorRegistros: " + CargadorVE.getContadorRegistros());

        double p23a = CargadorVE.promedioPotencia(new CargadorVE[] { c1, null, c3 });
        int[] p23b = CargadorVE.contarPorTipo(null);
        System.out.printf("[P23] promedioPotencia({c1,null,c3}) = %.2f kW (sin excepcion)%n", p23a);
        System.out.println("[P23] contarPorTipo(null) -> arreglo de tamano " + p23b.length + " (sin excepcion)");

        int n = 56;
        int r = n % 4;
        System.out.println("=== Ruta individual (N = " + n + ", r = " + r + ") ===");

        int conectoresBuscados = n % 3 + 1;
        CargadorVE[] resultadoRuta = cargadoresPorConectores(flota, conectoresBuscados);
        System.out.println("[R] N = " + n + ", r = " + r);
        System.out.println("[R] cargadoresPorConectores(flota, " + conectoresBuscados + ") -> cantidad: "
                + resultadoRuta.length);
        if (resultadoRuta.length == 0) {
            System.out.println("[R] No hay cargadores con " + conectoresBuscados + " conector(es).");
        } else {
            for (CargadorVE c : resultadoRuta) {
                System.out.println("       fabricante: " + c.getFabricante()
                        + " (numeroConectores=" + c.getNumeroConectores() + ")");
            }
        }

        System.out.println("=== Parte F: extension personalizada (C6) ===");

        int d1 = 5;
        int d2 = 6;

        String fabricanteC6 = "USC-" + n;
        int anioInstalacionC6 = 2015 + d2;
        int voltajeNominalC6 = (n % 2 == 0) ? 220 : 400;
        TipoConector tipoConectorC6 = TipoConector.values()[n % 5];
        TipoCargador tipoCargadorC6 = TipoCargador.values()[n % 6];
        int numeroConectoresC6 = d1 % 3 + 1;
        int puestosParqueoC6 = d2 % 4 + 1;
        double potenciaMaximaC6 = 20 + n;
        Ubicacion ubicacionC6 = Ubicacion.values()[n % 8];

        CargadorVE c6 = new CargadorVE(fabricanteC6, anioInstalacionC6, voltajeNominalC6,
                tipoConectorC6, tipoCargadorC6, numeroConectoresC6, puestosParqueoC6,
                potenciaMaximaC6, ubicacionC6);

        System.out.println("[X01] N = " + n + ", d1 = " + d1 + ", d2 = " + d2);
        System.out.println("[X01] c6.mostrar(false):");
        c6.mostrar(false);

        c6.setPotenciaActual(c6.getPotenciaMaxima() / 2);
        c6.aumentarPotencia(d2 + 5, d1 + 1);
        boolean huboRechazo = false;
        for (CargadorVE.RegistroSesion reg : c6.getBitacora()) {
            if (!reg.isValido() && reg.getEvento().startsWith("aumentarPotencia(" + (d2 + 5))) {
                huboRechazo = true;
            }
        }
        System.out.printf("[X02] Potencia final C6: %.2f kW%n", c6.getPotenciaActual());
        System.out.println("[X02] Algun paso fue rechazado: " + (huboRechazo ? "SI" : "NO"));

        double x03 = c6.tiempoEstimadoCarga(n + 10);
        System.out.printf("[X03] tiempoEstimadoCarga(%d) para C6: %.2f h%n", (n + 10), x03);

        CargadorVE[] flotaExtendida = new CargadorVE[flota.length + 1];
        for (int i = 0; i < flota.length; i++) {
            flotaExtendida[i] = flota[i];
        }
        flotaExtendida[flota.length] = c6;
        System.out.println("[X04] flotaExtendida creada con " + flotaExtendida.length + " cargadores.");

        int[] conteoExt = CargadorVE.contarPorTipo(flotaExtendida);
        System.out.println("[X05] contarPorTipo(flotaExtendida):");
        for (TipoCargador t : TipoCargador.values()) {
            System.out.println("       " + t + ": " + conteoExt[t.ordinal()]);
        }
        double promedioExt = CargadorVE.promedioPotencia(flotaExtendida);
        System.out.printf("[X05] promedioPotencia(flotaExtendida): %.2f kW%n", promedioExt);
        CargadorVE mayorExt = CargadorVE.mayorPotencia(flotaExtendida);
        if (mayorExt != null) {
            System.out.printf("[X05] mayorPotencia(flotaExtendida): %s con %.2f kW%n",
                    mayorExt.getFabricante(), mayorExt.getPotenciaActual());
        }
        int excesosExt = CargadorVE.excesosDePotenciaContratada(flotaExtendida);
        System.out.println("[X05] excesosDePotenciaContratada(flotaExtendida): " + excesosExt);

        System.out.println("[X06] getTotalCargadores(): " + CargadorVE.getTotalCargadores());
        System.out.println("[X06] contadorRegistros: " + CargadorVE.getContadorRegistros());
        System.out.println("[X06] c6.mostrar(true):");
        c6.mostrar(true);
    }

    public static CargadorVE[] cargadoresPorConectores(CargadorVE[] flota, int conectores) {
        List<CargadorVE> resultado = new ArrayList<CargadorVE>();
        if (flota != null) {
            for (CargadorVE c : flota) {
                if (c != null && c.getNumeroConectores() == conectores) {
                    resultado.add(c);
                }
            }
        }
        return resultado.toArray(new CargadorVE[0]);
    }
}

