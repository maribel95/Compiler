package CodigoIntermedioTablas;

import Enumerados.TipoSubjacenteBasico;
import java.util.LinkedList;

/**
 *
 * @author maribelcrespivalero
 */
public class TablaSubprogramas {

    private final LinkedList<DescripcionSubprograma> TP; // Tabla subprogramas
    private int np;

    public int getNp() {
        return np;
    }

    public void setNp(int np) {
        this.np = np;
    }

    public TablaSubprogramas() {
        this.TP = new LinkedList<>();
        this.np = -1;
    }
        
        public void nuevoSubprograma(String id, int profundidad, int nParametros, int ocupacion, TipoSubjacenteBasico tipoSubjacenteBasico){
        // np = np + 1
        this.np++;
        // Creamos nuevo subprograma a través de la información proporcionada en los parámetros
        DescripcionSubprograma ds = new DescripcionSubprograma(profundidad, id, nParametros, ocupacion, tipoSubjacenteBasico);
        //TP[np] = ...;
        this.TP.add(ds); 
    }

    public DescripcionSubprograma getSubprograma(int idProcedure) {
        return this.TP.get(idProcedure);
    }

    public void actualizarNumeroParametros(int IdSubprograma, int numParam) {
        this.TP.get(IdSubprograma).setNumeroParametros(numParam);
    }

    public int subprogramaActual() {
        return this.np;
    }

    public LinkedList<DescripcionSubprograma> getTP() {
        return TP;
    }
}
