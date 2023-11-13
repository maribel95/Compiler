
package Simbolos;

/*

 */
public class SimboloOperadores extends Base{      
    // Valor del tipo, puede ser booleano o entero
    // valor resultante de alguna de las operaciones que se realicen
    private Object valorTipo; 
    // Solo para los booleanos--> si es un true o un false
    // En caso de no ser simple, entonces ya generaríamos la etiqueta 
    private boolean esSimple; 
     // Si el valor es constante
    private boolean esConstante;
    // Identificador de la varibale
    private String idVariable; 
    // Solo para los booleanos, ya que en las operaciones donde puedan haber saltos
    // según la condición, se generará la etiqueta de salto correspondiente
    private String idEtiqueta;

    public String getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(String idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public boolean esSimple() {
        return esSimple;
    }

    public void setEsSimple(boolean esSimple) {
        this.esSimple = esSimple;
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

    public String getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }
    
    public SimboloOperadores() {
        super("SimboloOperadores", 0);
        this.esConstante = false;
    }
        public SimboloOperadores(String idVariable) {
        super("SimboloOperadores", 0);
        this.idVariable = idVariable;
    }
    
    public SimboloOperadores(String idVariable , Object valorTipo) {
        super("SimboloOperadores", 0);
        this.valorTipo = valorTipo;
        this.esConstante = true;
        this.idVariable = idVariable;
    }
    
    public SimboloOperadores(String idVariable, boolean esConstante, Object valorTipo){
        super("SimboloOperadores", 0);
        this.idVariable = idVariable;
        this.esConstante = esConstante;
        this.valorTipo = valorTipo;
    }
    public SimboloOperadores(String idVariable, boolean esConstante){
        super("SimboloOperadores", 0);
        this.idVariable = idVariable;
        this.esConstante = esConstante;
    }
    public SimboloOperadores(String etiqueta, String idVariable, boolean esSimple){
        super("SimboloOperadores", 0);
        this.idEtiqueta = etiqueta;
        this.idVariable = idVariable;
        this.esSimple = esSimple;
    }
    
    public SimboloOperadores(boolean esConstante,Object valorTipo,  boolean esSimple,String idEtiqueta, String idVariable){
        super("SimboloOperadores", 0);
        this.esConstante = esConstante;
        this.valorTipo = valorTipo;
        this.esSimple = esSimple;
        this.idEtiqueta = idEtiqueta;
        this.idVariable = idVariable;
    }
    public SimboloOperadores(boolean esSimple,String idEtiqueta, String idVariable){
        super("SimboloOperadores", 0);
        this.esSimple = esSimple;
        this.idEtiqueta = idEtiqueta;
        this.idVariable = idVariable;
    }
        public SimboloOperadores(boolean esConstante,Object valorTipo, String idVariable){
        super("SimboloOperadores", 0);
        this.esConstante = esConstante;
        this.valorTipo = valorTipo;
        this.idVariable = idVariable;
    }
}
