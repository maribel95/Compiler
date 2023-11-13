
package Simbolos;

import Enumerados.DescripcionValor;
import Enumerados.TipoSubjacenteBasico;

public class SimboloValores extends Base{
    // Indicamos la descripcion del valor, para saber si nuestro objeto es
    // una variable, subprograma, parámetro...
    private DescripcionValor descripcionValor; 
    // Indicamos si tiene signo en caso de ser un entero
    private boolean tieneSigno; 
    // En caso de ser un valor entero y tener signo, para saber si es negativo o no,
    // ya que a la hora de generar el valor del código de 3 direcciones necesitaremos
    // saber si el valor es positivo o negativo
    private boolean esNegativo; 
    
    public boolean tieneSigno() {
        return tieneSigno;
    }

    public void setTieneSigno(boolean tieneSigno) {
        this.tieneSigno = tieneSigno;
    }

    public boolean esNegativo() {
        return esNegativo;
    }

    public void setEsNegativo(boolean esPositivo) {
        this.esNegativo = esPositivo;
    }

    public DescripcionValor getDescripcionValor() {
        return descripcionValor;
    }

    public void setDescripcionValor(DescripcionValor descripcionValor) {
        this.descripcionValor = descripcionValor;
    }

    public TipoSubjacenteBasico getTipoSubjacenteBasico() {
        return tipoSubjacenteBasico;
    }

    public void setTipoSubjacenteBasico(TipoSubjacenteBasico tipoSubjacenteBasico) {
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
    }

    public String getNombreTipo() {
        return nombreVariable;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreVariable = nombreTipo;
    }

    public Object getValorTipo() {
        return valorTipo;
    }

    public void setValorTipo(Object valorTipo) {
        this.valorTipo = valorTipo;
    }

    public boolean esConstante() {
        return esConstante;
    }

    public void setEsConstante(boolean esConstante) {
        this.esConstante = esConstante;
    }

    public boolean esString() {
        return esString;
    }

    public void setEsString(boolean esString) {
        this.esString = esString;
    }

    public String getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }

    public int getOcupacionString() {
        return ocupacionString;
    }

    public void setOcupacionString(int ocupacionString) {
        this.ocupacionString = ocupacionString;
    }
    // Indicamos el tipo subjacente básico, útil a la hora de detectar errores de asignación o declaración incorrecta
    private TipoSubjacenteBasico tipoSubjacenteBasico;    
    // Nombre de la variable, por si fuera necesario hacer consultas a la tabla de símbolos
    private String nombreVariable;                            
    // Valor, que puede ser tanto booleano, cadena o entero
    private Object valorTipo;                             
    // Indicamos si es un valor constante o no, útil para saber si podremos hacer una asignación o no
    private boolean esConstante;                 
    // Si es string(inicialmente a false), ya que en caso de serlo, nos interesará saber cuánto
    // ocupa exactamente.
    private boolean esString = false;                    
    // Identificador de la variable, asignaremos su id respecto al valor siguiente de la tabla de variables
    // Nos servirá para gestiones de la generación del código de 3 direcciones
    private String idVariable; 
    // Esta es la variable que guarda cuánto ocupa el string, ya que hay que saber cuánto ocupa cada variable en el codigo
    // de 3 direcciones
    private int ocupacionString = 0;                     
    

 
    public SimboloValores(TipoSubjacenteBasico tipoSubjacenteBasico, Object valorTipo, String idVariable) {
        super("SimboloValores", 0);
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
        this.valorTipo = valorTipo;
        this.esConstante = true;
        this.descripcionValor = DescripcionValor.dvNull;
        this.idVariable = idVariable;

    }

    public SimboloValores(TipoSubjacenteBasico tipoSubjacenteBasico, Object valorTipo) {
        super("SimboloValores", 0);
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
        this.valorTipo = valorTipo;
        this.esConstante = true;
        this.descripcionValor = DescripcionValor.dvNull;

    }

    public SimboloValores(TipoSubjacenteBasico tipoSubjacenteBasico) {
        super("SimboloValores", 0);
        this.descripcionValor = DescripcionValor.dvNull;
        this.esConstante = false;
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;

    }

    public SimboloValores(DescripcionValor descripcionValor, String nombreTipo) {
        super("SimboloValores", 0);
        this.descripcionValor = descripcionValor;
        this.esConstante = false;
        this.nombreVariable = nombreTipo;
    }
    public SimboloValores(DescripcionValor descripcionValor, String nombreTipo, Object valorTipo, boolean esConstante, String idVariable) {
        super("SimboloValores", 0);
        this.descripcionValor = descripcionValor;
        this.esConstante = false;
        this.nombreVariable = nombreTipo;
        this.valorTipo = valorTipo;
        this.esConstante = esConstante;
        this.idVariable = idVariable;
    }
        public SimboloValores(DescripcionValor descripcionValor, String nombreTipo, String idVariable) {
        super("SimboloValores", 0);
        this.descripcionValor = descripcionValor;
        this.esConstante = false;
        this.nombreVariable = nombreTipo;
        this.idVariable = idVariable;
    }

    public SimboloValores() {
        super("SimboloValores", 0);
        this.tieneSigno = false;
    }

    public SimboloValores(boolean esPositivo) {
        super("SimboloValores", 0);
        this.esNegativo = esPositivo;
        this.tieneSigno = true;
    }

    public SimboloValores(TipoSubjacenteBasico tipoSubjacenteBasico, boolean esConstante, String idVariable) {
        super("SimboloValores", 0);
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
        this.esConstante = esConstante;
        this.idVariable = idVariable;
    }

    public SimboloValores(TipoSubjacenteBasico tipoSubjacenteBasico, boolean esConstante, String idVariable, Object valorTipo) {
        super("SimboloValores", 0);
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
        this.esConstante = esConstante;
        this.idVariable = idVariable;
        this.valorTipo = valorTipo;
    }

    public SimboloValores(TipoSubjacenteBasico tipoSubjacenteBasico, Object valorTipo, boolean esString, String idVariable, int ocupacionString) {
        super("SimboloValores", 0);
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
        this.valorTipo = valorTipo;
        this.esString = esString;
        this.idVariable = idVariable;
        this.ocupacionString = ocupacionString;
    }


}
