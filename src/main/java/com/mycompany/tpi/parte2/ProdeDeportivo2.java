package com.mycompany.tpi.parte2;

import com.mysql.cj.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProdeDeportivo2 {

    public static void main(String[] args) {


        List<Partido> partidos = new ArrayList<>();
        List<Pronostico> list_pronostico = new ArrayList<>();
       // List<Integer> resultados =new ArrayList<>();
        //ResultadoEnum resultado = null;
        List<Persona> personas = new ArrayList<>();

        // Obtener las rutas de los archivos de partidos y resultados

        String archivoResultados = "src\\main\\java\\com\\mycompany\\tpi\\parte2\\resultados.csv";

        try (BufferedReader lector = new BufferedReader(new FileReader(archivoResultados))) {
            String linea;
            lector.readLine();
            int numeroPartido = 1;
            while ((linea = lector.readLine()) != null) {
                String[] campos = linea.split(",");
                int codigoPartido = Integer.parseInt(campos[0]);
                Equipo equipoLocal = new Equipo(campos[1], "equipo local");
                Equipo equipoVisitante = new Equipo(campos[4], "equipo visitante");
                int golesEquipo1 = Integer.parseInt(campos[2]);
                int golesEquipo2 = Integer.parseInt(campos[3]);
                partidos.add(new Partido(equipoLocal, equipoVisitante, golesEquipo1, golesEquipo2, codigoPartido));
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

        //conexion a la DB
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/prode";
            String user = "root";
            String password = "MySQLpassword1";
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from pronostico");
            while (rs.next()) {

                String nombrePersona = rs.getString(2);
                int codigoPartido = rs.getInt(3);
                Equipo equipo = new Equipo(rs.getString(4), "Equipo local");
                int codigo = rs.getInt(5);
                list_pronostico.add(new Pronostico(nombrePersona, codigoPartido, equipo, codigo));


            }

            connection.close();
        } catch (Exception e) {
            System.out.println(e);

        }


        Persona persona1 = new Persona();
        Persona persona2 = new Persona();
        String nombreInicial = list_pronostico.get(0).getNombre();
        for (Pronostico p : list_pronostico) {
            if (p.getNombre().equals(nombreInicial)) {
                persona1.CargarPronostico(p);
            } else {
                persona2.CargarPronostico(p);
            }
            int puntosConfig =2;//configuración de puntos asignados por pronostico acertado
            persona1.SetNombre("Mariana");
            persona2.SetNombre("Pedro");
            for (int i = 0; i < partidos.size(); i++) {
                for (Pronostico p1 : persona1.GetPronosticos()) {
                    if (partidos.get(i).codigoPartido == p1.getCodigoPartido()) {
                        if (p1.Puntos(partidos.get(i)) == 1) {
                            persona1.pronosticosAcertados += 1;
                        }
                        persona1.puntos = persona1.pronosticosAcertados*puntosConfig;

                    }
                }
                for (Pronostico p2 : persona2.GetPronosticos()) {
                    if (partidos.get(i).codigoPartido == p2.getCodigoPartido()) {
                        if (p2.Puntos(partidos.get(i)) == 1) {
                            persona2.pronosticosAcertados += 1;
                        }
                        persona2.puntos = persona2.pronosticosAcertados*puntosConfig;

                    }
                }
            }
        }

        System.out.println("La cantidad de pronosticos acertados por " + persona1.GetNombre() + " fue de: " + persona1.pronosticosAcertados + ".\n Sumando un total de " + persona1.puntos + " puntos.");
        System.out.println("La cantidad de pronosticos acertados por " + persona2.GetNombre() + " fue de: " + persona2.pronosticosAcertados + ".\n Sumando un total de " + persona2.puntos + " puntos.");

    }}
