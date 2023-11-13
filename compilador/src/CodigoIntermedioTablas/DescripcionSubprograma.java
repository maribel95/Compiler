/*
    Clase que recoge les características y descripción de los subprogramas
 */
package CodigoIntermedioTablas;

import Enumerados.TipoSubjacenteBasico;

/*
    Se tiene que tener en cuenta:
    1. El nivell de profunditat de la declaració
    2. L’etiqueta inicial per poder accedir-hi en el moment d’executar el codi.
    3. L’ocupació que tenen les variables locals
    4. L'ocupació dels paràmetres
    5. El nombre de paràmetres que té
*/
public class DescripcionSubprograma {
    private int profundidad;
    private String etiqueta;  
    private int ocupacionVariablesLocales;

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }


    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public int getOcupacionVariablesLocales() {
        return ocupacionVariablesLocales;
    }

    public void setOcupacionVariablesLocales(int ocupacionVariablesLocales) {
        this.ocupacionVariablesLocales = ocupacionVariablesLocales;
    }

    public int getOcupacionParametros() {
        return ocupacionParametros;
    }

    public void setOcupacionParametros(int ocupacionParametros) {
        this.ocupacionParametros = ocupacionParametros;
    }

    public int getNumeroParametros() {
        return numeroParametros;
    }

    public void setNumeroParametros(int numeroParametros) {
        this.numeroParametros = numeroParametros;
    }

    public int getOcupacionTotal() {
        return ocupacionTotal;
    }

    public void setOcupacionTotal(int ocupacionTotal) {
        this.ocupacionTotal = ocupacionTotal;
    }

    public TipoSubjacenteBasico getTipoSubjacenteBasico() {
        return tipoSubjacenteBasico;
    }

    public void setTipoSubjacenteBasico(TipoSubjacenteBasico tipoSubjacenteBasico) {
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;
    }
    private int ocupacionParametros;   
    private int numeroParametros;
    private int ocupacionTotal;
    private TipoSubjacenteBasico tipoSubjacenteBasico;

    public DescripcionSubprograma(int profundidad, String etiqueta, int nParametros, int ocupacion, TipoSubjacenteBasico tipoSubjacenteBasico){
        this.profundidad = profundidad;
        this.etiqueta = etiqueta;
        this.numeroParametros = nParametros;
        this.ocupacionTotal = ocupacion;
        this.tipoSubjacenteBasico = tipoSubjacenteBasico;  
        this.ocupacionParametros = 0;
        this.ocupacionVariablesLocales = 0;
    }

}
