
package Simbolos;

import Enumerados.DescripcionValor;
import EstructurasDatos.DescripcionTipo;
import java.util.LinkedList;

public class SimboloParametros extends Base{
    
    // Contador de cuántos parámetros tiene la función, ya que será necesario comprobar
    // en la llamada del subprograma si el número coincide
    private int contador;  
    // Identificador de la instrucción del código de 3 direcciones
    private int idCodigo3D; 
    // identificador del nombre del subprogrma
    private String idSubprograma;
    // Lista de los parámetros del subprograma, que posteriormente añadiremos a la tabla de expansión
    private LinkedList<DescripcionTipo> listaTipoDescripcion = new LinkedList<>(); 
    // Aqui guardamos las variables del subprograma
    private LinkedList<String> listaVariables = new LinkedList<>(); 

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public int getIdCodigo3D() {
        return idCodigo3D;
    }

    public void setIdCodigo3D(int idCodigo3D) {
        this.idCodigo3D = idCodigo3D;
    }

    public String getIdSubprograma() {
        return idSubprograma;
    }

    public void setIdSubprograma(String idSubprograma) {
        this.idSubprograma = idSubprograma;
    }
        public LinkedList<DescripcionTipo> getListaTipoDescripcion() {
        return listaTipoDescripcion;
    }

    public void setListaTipoDescripcion(LinkedList<DescripcionTipo> listaTipoDescripcion) {
        this.listaTipoDescripcion = listaTipoDescripcion;
    }

    public LinkedList<String> getListaVariables() {
        return listaVariables;
    }

    public void setListaVariables(LinkedList<String> listaVariables) {
        this.listaVariables = listaVariables;
    }

           
    public SimboloParametros(String idSubprograma, int contador, int idCodigo3D) {
        super("SimboloParametros", 0);
        this.idSubprograma = idSubprograma; 
        this.idCodigo3D = idCodigo3D;
        this.contador = contador;
    }
    public SimboloParametros() {
        super("SimboloParametros", 0);
    }
    
    public SimboloParametros(LinkedList<String> listaVariables, LinkedList<DescripcionTipo> listaTipoDescripcion) {
        super("SimboloParametros", 0);
        this.listaVariables = listaVariables;
        this.listaTipoDescripcion = listaTipoDescripcion;
    }
    
    public SimboloParametros(String simboloIdVariable, String simboloTipoVariable ){
        super("SimboloParametros", 0);
        listaTipoDescripcion.add(new DescripcionTipo(simboloTipoVariable,null, DescripcionValor.dvArgumento));
        listaVariables.add(simboloIdVariable);
    }
    
}