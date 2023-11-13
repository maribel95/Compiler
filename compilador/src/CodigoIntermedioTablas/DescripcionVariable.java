/*
    Clase que recoge las características de cada variable del programa
*/
package CodigoIntermedioTablas;

import Enumerados.TipoSubjacenteBasico;
/*
    Per a cada variable la informació necessària és, com a mínim, la següent
    1. El codi identificador del subprograma del que la variable és local
    2. Un indicador de si es tracta d’un paràmetre o d’una variable del subprograma
    3. Quina ocupació té
    4. Desplaçament(posició dins l’espai de variables locals)
    5. Tipo subjacente básico
 */
public class DescripcionVariable {
    private String nombre;
    private int idSubprograma;
    private int numeroVariable;

    public int getNumeroVariable() {
        return numeroVariable;
    }

    public void setNumeroVariable(int numeroVariable) {
        this.numeroVariable = numeroVariable;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdSubprograma() {
        return idSubprograma;
    }

    public void setIdSubprograma(int idSubprograma) {
        this.idSubprograma = idSubprograma;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(int ocupacion) {
        this.ocupacion = ocupacion;
    }

    public int getDesplazamiento() {
        return desplazamiento;
    }

    public void setDesplazamiento(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public TipoSubjacenteBasico getTipoSubjacenteBasico() {
        return tipoSubjacenteBasico;
    }

    public void setTipoSubjacenteBasico(TipoSubjacenteBasico tipoSubjacenteBasico) {
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
    }
    private int ocupacion;
    private int desplazamiento; // Parámetre: positiu, Variable: negatiu i Constant valor en lloc de desplaçament
    private TipoSubjacenteBasico tipoSubjacenteBasico;
    
    
    public DescripcionVariable(TipoSubjacenteBasico tipoSubjacenteBasico, String nombre, int idSubprograma, int desplazamiento, int ocupacion, int numeroVariable){
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
        this.nombre = nombre;
        this.idSubprograma = idSubprograma;
        this.desplazamiento = desplazamiento;
        this.ocupacion = ocupacion;
        this.numeroVariable = numeroVariable;        
    }
}
