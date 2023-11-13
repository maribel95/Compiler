
package EstructurasDatos;

/*
    DESCRIPCIÓN DE CADA IDENTIFICADOR:
    1. Identificador inequívoco
    2. Tipo de dato/descripción
    3. Ámbito
 */
// TablaDescripcion
public class NodoTabla {
    
    private String id;
    private int ambito;
    private DescripcionTipo tipoDescripcion;
    private int first; // Solo para los parámetros de las funciones
    private int follow;
    private String idConstante;
    
    public NodoTabla(String id, int ambito, DescripcionTipo tipoDescripcion){
        this.id = id;
        this.ambito = ambito;
        this.tipoDescripcion = tipoDescripcion;
        this.first = -1;
        this.follow = -1;
    }
    public NodoTabla(String id, int ambito, DescripcionTipo tipoDescripcion, int first){
        this.id = id;
        this.ambito = ambito;
        this.tipoDescripcion = tipoDescripcion;
        this.first = first;
        this.follow = -1;
    }
    
    public NodoTabla(String id, int ambito, DescripcionTipo tipoDescripcion,String idConstante, int next){
        this.id = id;
        this.ambito = ambito;
        this.tipoDescripcion = tipoDescripcion;
        this.idConstante = idConstante;
        this.first = -1;
        this.follow = next;
    }
    //******************** GETTERS Y SETTERS ***********************************
    public String getIdConstante() {
        return idConstante;
    }
    public void setIdConstante(String idc) {
        this.idConstante = idc;
    }
    public String getId() {
        return id;
    }

    public int getFirst() {
        return first;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmbito(int ambito) {
        this.ambito = ambito;
    }

    public void setTipoDescripcion(DescripcionTipo tipoDescripcion) {
        this.tipoDescripcion = tipoDescripcion;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getAmbito() {
        return ambito;
    }


    public DescripcionTipo getTipoDescripcion() {
        return tipoDescripcion;
    }
    
    public int getFollow() {
        return follow;
    }

    public void setFollow(int next) {
        this.follow = next;
    }

}
