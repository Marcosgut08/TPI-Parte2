package com.mycompany.tpi.parte2;

import java.util.ArrayList;
import java.util.List;

public class Persona {

    private String nombre;
    private List<Pronostico> pronosticos = new ArrayList<>();
    public int puntos = 0;
    public int pronosticosAcertados = 0;

    public Persona() {
        this.nombre = nombre;
        this.pronosticos = pronosticos;
    }

    public String GetNombre() {
        return nombre;
    }

    public void SetNombre(String nombre) {
        this.nombre = nombre;
    }

    public void CargarPronostico(Pronostico pronostico) {
        
        this.pronosticos.add(pronostico);
    }

    public List<Pronostico> GetPronosticos() {
        return this.pronosticos;
    }
}
