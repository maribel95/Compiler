/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CodigoIntermedioTablas;

import Enumerados.TipoSubjacenteBasico;
import java.util.LinkedList;

/**
 *
 * @author maribelcrespivalero
 */
public class TablaVariables {
    private final LinkedList<DescripcionVariable> TV; // Tabla variables
    private int nv;

    public int getNv() {
        return nv;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }
    
    public TablaVariables(){
        this.TV = new LinkedList<>();
        this.nv  = -1;
    }
    
        public void nuevaVariable(String id, int idSubprograma, int ocupacion, int desplazamiento, TipoSubjacenteBasico tipoSubjacenteBasico) {      
        // nv = nv + 1
        this.nv++;
        // Creamos la descripción a através de la información proporcionada en los parámetros
        DescripcionVariable dv = new DescripcionVariable(tipoSubjacenteBasico, id, idSubprograma, desplazamiento, ocupacion, nv);
        // TV[nv] = ...;
        this.TV.add(dv);
    }
        
            
    public DescripcionVariable getVariable(int idVariable) {
        return this.TV.get(idVariable);
    }
    public LinkedList<DescripcionVariable> getTV() {
        return TV;
    }
    
}
