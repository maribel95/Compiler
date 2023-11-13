/*
    La descripción de una variable lo que nos indica es el nombre de la variable,
    sus límites, si es constante o no y el tamaño que ocupa en memoria.
 */
package EstructurasDatos;

import Enumerados.TipoSubjacenteBasico;
import Enumerados.DescripcionValor;

/*
    Per a la DESCRIPCIÓ DEL TIPUS necessitam:
    1. Tipus Subjacent Bàsic del tipus
    2. Ocupació -> l'espai en bytes que requereix un valor d'aquest tipus 
    Adicionalmente, en función del tipo subjacente básico podemos añadir
    3. Limite inferior
    4. Limite superior
 */
public class DescripcionTipo {
 
    private DescripcionValor descripcionValor;
    private TipoSubjacenteBasico tipoSubjacenteBasico;
    private int ocupacion;
    private int limiteInf;
    private int limiteSup;
    private Object valorConstante;       // Para gestionar las constantes
    private String nombreId;             // Para los id de las variables, programas o argumentos
    private int idCodigoIntermedio;
    private int idTipo;


    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    //******************** GETTERS Y SETTERS ***********************************
    
    public DescripcionValor getDescripcionValor() {
        return descripcionValor;
    }

    public TipoSubjacenteBasico getTipoSubjacenteBasico() {
        return tipoSubjacenteBasico;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public int getLimiteInf() {
        return limiteInf;
    }
    
    private boolean codigoIntermedio;
    
    public int getLimiteSup() {
        return limiteSup;
    }

    public Object getValorConstante() {
        return valorConstante;
    }

    public String getNombreId() {
        return nombreId;
    }

    public int getIdCodigoIntermedio() {
        return idCodigoIntermedio;
    }

    public void setIdCodigoIntermedio(int idCodigoIntermedio) {
        this.idCodigoIntermedio = idCodigoIntermedio;
    }
    //**************************************************************************

    // Descripción de tipo de Integer, Booleanos y Strings
    public DescripcionTipo(TipoSubjacenteBasico tipoSubjacenteBasico, int tamanyo, int limiteInf, int limiteSup) {
        this.descripcionValor = DescripcionValor.dvTipo;
        this.limiteSup = limiteSup;
        this.limiteInf = limiteInf;
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
        this.ocupacion = tamanyo;
        this.idCodigoIntermedio = -1;
    }
    // Descripción de las constantes
    public DescripcionTipo(String tipoNombre, Object valorConstante, DescripcionValor descripcionVal) {
        this.descripcionValor = descripcionVal;
        this.nombreId = tipoNombre;
        this.valorConstante = valorConstante;
        this.idCodigoIntermedio = -1;

    }
    // Descripción funciones
    public DescripcionTipo(String tipoNombre, Object valorConstante, DescripcionValor descripcionVal, int idCodigoIntermedio) {
        this.descripcionValor = descripcionVal;
        this.nombreId = tipoNombre;
        this.valorConstante = valorConstante;
        this.idCodigoIntermedio = idCodigoIntermedio;    
    }
}
