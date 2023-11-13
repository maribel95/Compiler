
package Ensamblador;

import Codigo3Direcciones.Codigo3D;
import CodigoIntermedioTablas.TablaSubprogramas;
import CodigoIntermedioTablas.TablaVariables;
import Optimizacion.Optimizacion;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author maribelcrespivalero
 */
public class GeneradorCodigoEnsamblador {
    private TraductorCodigo traductorCodigo;
    private final Optimizacion optimizacion;
    private ArrayList<Codigo3D> listaCodigo3D;
    private final TablaVariables TV;
    private final TablaSubprogramas TP;
    private final LinkedList<String> tablaEtiquetas;
    
    public GeneradorCodigoEnsamblador(ArrayList<Codigo3D> listaCodigo3D, TablaVariables TV, TablaSubprogramas TP, LinkedList<String> tablaEtiquetas){
        this.listaCodigo3D = listaCodigo3D;
        this.optimizacion = new Optimizacion();
        this.TV = TV;
        this.TP = TP;
        this.tablaEtiquetas = tablaEtiquetas;
    }
    public void crearCodigoEnsambladorSinOptimizar() throws Exception {
        this.traductorCodigo = new TraductorCodigo("Ensamblador/EnsambladorSinOptimizar.X68", this.listaCodigo3D, tablaEtiquetas, this.TP, this.TV);
        this.traductorCodigo.generarCodigoEnsamblador();
    }
    
    public void crearCodigoEnsambladorOptimizado() throws Exception {
        this.optimizacion.setListaCodigo3D(this.listaCodigo3D);
        this.listaCodigo3D = this.optimizacion.optimizarC3D();
        this.traductorCodigo = new TraductorCodigo("Ensamblador/EnsambladorOptimizado.X68", this.listaCodigo3D, tablaEtiquetas, this.TP, this.TV);
        this.traductorCodigo.generarCodigoEnsamblador();
    }
}
