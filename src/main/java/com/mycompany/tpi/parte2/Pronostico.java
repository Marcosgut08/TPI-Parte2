package com.mycompany.tpi.parte2;

public class Pronostico {

    private int codigoPartido;
    private Equipo equipo;
    private ResultadoEnum resultado;
    private String nombrePersona;

    public Pronostico() {

    }

    public Pronostico(String nombrePersona, int codigoPartido, Equipo equipo, int codigo) {
        this.nombrePersona = nombrePersona;
        this.codigoPartido = codigoPartido;
        this.equipo = equipo;
        this.resultado = new ResultadoEnum(codigo);
    }
 
    int puntos = 0;

    public int Puntos(Partido partido) {

        if(partido.Resultado(getEquipo()).getCodigo() == this.resultado.getCodigo()) {
            puntos += 1;
            
        }
        return puntos;
    }

    public String getNombre() {
        return this.nombrePersona;
    }

    public int getCodigoPartido() {
        return codigoPartido;
    }

    public void setCodigoPartido(int codigoPartido) {
        this.codigoPartido = codigoPartido;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public ResultadoEnum getResultado() {
        return resultado;
    }

    public void setResultado(ResultadoEnum resultado) {
        this.resultado = resultado;
    }

}
