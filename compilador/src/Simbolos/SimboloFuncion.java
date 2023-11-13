
package Simbolos;

import Enumerados.TipoSubjacenteBasico;
/*
    Clase que ayuda a gestionar la información que necesitamos para las producciones relacionadas
    con la creación y llamada de funciones en nuestro programa.
*/
public class SimboloFuncion extends Base{
    private int nSubprograma; // Numero de subprograma, según la tabla de procedimientos
    private String tipoSubprograma; // El tipo del subprograma
    private TipoSubjacenteBasico tipoSubjacenteBasico; // tipoSubjacente basico para gestionar el valor de retorno
    private String idCodigo3D; // Id de la variable de retorno para generar la instruccion el codigo de 3 direcciones
    private String nombreSubprograma; // Nombre del subprograma

    //**************  Métodos Getters y Setters ********************************
    public String getNombreSubprograma() {
        return nombreSubprograma;
    }
    
    public TipoSubjacenteBasico getTipoSubjacenteBasico() {
        return tipoSubjacenteBasico;
    }

    public void setTipoSubjacenteBasico(TipoSubjacenteBasico tipoSubjacenteBasico) {
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
    }

    public String getIdCodigo3D() {
        return idCodigo3D;
    }

    public void setIdCodigo3D(String idCodigo3D) {
        this.idCodigo3D = idCodigo3D;
    }
    public int getnSubprograma() {
        return nSubprograma;
    }

    public void setnSubprograma(int nSubprograma) {
        this.nSubprograma = nSubprograma;
    }

    public String getSubprograma() {
        return tipoSubprograma;
    }

    public void setSubprograma(String tipoSubprograma) {
        this.tipoSubprograma = tipoSubprograma;
    }
    
    //**************************************************************************
    
    
    public SimboloFuncion(String subprograma, int nSubprograma, String nombreSubprograma) {
        super("SimboloInicioFuncion", 0);
        this.nSubprograma = nSubprograma;
        this.tipoSubprograma = subprograma;   
        this.nombreSubprograma = nombreSubprograma;
    }
    public SimboloFuncion(String subprograma, int nSubprograma) {
        super("SimboloInicioFuncion", 0);
        this.nSubprograma = nSubprograma;
        this.tipoSubprograma = subprograma;   

    }
    public SimboloFuncion() {
        super("SimboloFuncion", 0);
    }
    public SimboloFuncion(TipoSubjacenteBasico tipoSubjacenteBasico, String idCodigo3D) {
        super("SimboloFuncion", 0);
        this.idCodigo3D = idCodigo3D;
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;        
    }
    
}
