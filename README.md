# Parcial 1 - VoltaCali

**Nombre:** Alejandro Lomellin Bedoya
**Código:** 1114002256

## Datos de la ruta individual

- **N** (dos últimos dígitos de la cédula, con ceros a la izquierda si aplica): `56`
- **r = N mod 4:** `0`
- **Ruta asignada:** Ruta 0 → `cargadoresPorConectores(CargadorVE[] flota, int conectores)`

## Tabla de atributos de C6 (calculados a partir de N = 56, d1 = 5, d2 = 6)

| Atributo         | Regla                               | Valor calculado                  |
|------------------|--------------------------------------|-----------------------------------|
| fabricante       | `"USC-" + N`                        | `USC-56`                          |
| anioInstalacion  | `2015 + d2`                         | `2021`                            |
| voltajeNominal   | 220 si N es par; 400 si N es impar  | `220` (N=56 es par)               |
| tipoConector     | `TipoConector.values()[N % 5]`      | `TIPO_2` (56 % 5 = 1)             |
| tipoCargador     | `TipoCargador.values()[N % 6]`      | `RAPIDO_DC` (56 % 6 = 2)          |
| numeroConectores | `d1 % 3 + 1`                        | `3` (5 % 3 + 1)                   |
| puestosParqueo   | `d2 % 4 + 1`                        | `3` (6 % 4 + 1)                   |
| potenciaMaxima   | `20 + N`                            | `76 kW`                           |
| ubicacion        | `Ubicacion.values()[N % 8]`         | `CENTRO_COMERCIAL` (56 % 8 = 0)   |

## Comandos para compilar y ejecutar

Desde la raíz del repositorio:

```bash
mvn clean compile
java -cp target/classes co.edu.usc.voltacali.App
```

## Estructura del proyecto

parcial1-voltacali/
├── pom.xml
├── .gitignore
├── README.md
├── src/
│   ├── main/java/co/edu/usc/voltacali/
│   │   ├── App.java
│   │   └── CargadorVE.java
│   └── test/java/co/edu/usc/voltacali/
│       └── AppTest.java
└── docs/
    ├── Captura1.png
    ├── Captura2.png
    └── Captura3.png