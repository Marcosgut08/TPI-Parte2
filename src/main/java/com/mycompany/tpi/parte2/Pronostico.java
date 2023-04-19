
package com.mycompany.tpi.parte2;


public class Pronostico {
    private int codigoPartido;
    private Equipo equipo;
    private ResultadoEnum resultado;
    
    
    public Pronostico(){
    
    }


    public Pronostico(int codigoPartido, Equipo equipo, int codigo) {
      
        this.codigoPartido = codigoPartido;
        this.equipo = equipo;
        this.resultado = new ResultadoEnum(codigo);
    }
    int puntos = 0;
    public int Puntos(Partido partido) {
        
        while(partido.Resultado(getEquipo()).getCodigo() == this.resultado.getCodigo()) {
            return puntos = puntos + 1;
        }
      return puntos;
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