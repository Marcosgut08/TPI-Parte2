package com.mycompany.tpi.parte2;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdeDeportivo2 {

    public static void main(String[] args) {
        List<Partido> partidos = new ArrayList<>();
        List<Pronostico> pronostico = new ArrayList<>();
        ResultadoEnum resultado = null;

        // Obtener las rutas de los archivos de partidos y resultados
        String archivoPronostico = "src\\main\\java\\com\\mycompany\\tpi\\parte2\\pronostico.csv";
        String archivoResultados = "src\\main\\java\\com\\mycompany\\tpi\\parte2\\resultados.csv";
        

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoResultados))) {
            String linea;
            lector.readLine();
            int numeroPartido = 1;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                Equipo equipoLocal = new Equipo(campos[1], "equipo local");
                Equipo equipoVisitante = new Equipo(campos[4], "equipo visitante");
                int golesEquipo1 = Integer.parseInt(campos[2]);
                int golesEquipo2 = Integer.parseInt(campos[3]);
                partidos.add(new Partido(equipoLocal, equipoVisitante, golesEquipo1, golesEquipo2));
               
                System.out.println("Partido numero " + numeroPartido);
                numeroPartido += 1;
                System.out.println("Nombre del Local: " + equipoLocal);
                System.out.println("Nombre del Visitante: " + equipoVisitante);
                System.out.println("Goles del equipo local: " + golesEquipo1);
                System.out.println("Goles del equipo visitante: " + golesEquipo2);
                System.out.println("");
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo partidos: " + e.getMessage());
            return;
        }
        System.out.println("....................................");

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoPronostico))) {
            String linea;
            lector.readLine();
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                int codigoPartido = Integer.parseInt(campos[0]);
                Equipo equipo = new Equipo(campos[1], "equipo local");
                int codigo = Integer.parseInt(campos[2]);
                pronostico.add(new Pronostico(codigoPartido, equipo, codigo));
                resultado = new ResultadoEnum(codigo);
            }
        } catch (IOException e) {
            System.err.println("Error al leer archivo partidos: " + e.getMessage());
            return;
        }
        int totalPuntos = 0;

        for (Pronostico p : pronostico) {
            for (Partido partido : partidos) {

                while (p.getCodigoPartido() == resultado.getCodigo()) {
                    totalPuntos = p.Puntos(partido);
                    break; // Salimos del bucle interno
                }
            }
        }
        System.out.println("Total de puntos: " + totalPuntos);
    }
}
