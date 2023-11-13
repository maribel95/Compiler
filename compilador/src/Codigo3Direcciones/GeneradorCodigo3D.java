/*
    Clase que gestionará todo el código intermedio
*/
package Codigo3Direcciones;

import CodigoIntermedioTablas.TablaSubprogramas;
import CodigoIntermedioTablas.TablaVariables;
import Enumerados.Operacion3Direcciones;
import Ficheros.GestorFicheros;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;


public class GeneradorCodigo3D {

    private ArrayList<Codigo3D> listaCodigo3D;
    private final GestorFicheros gestorFicheros;
    private LinkedList<String> tablaEtiquetas;
    private int numeroEtiqueta;
    private TablaVariables TV;
    private TablaSubprogramas TP;
    private Stack<String> pilaFalse;
    private Stack<String> pilaTrue;

    public Stack<String> getPilaFalse() {
        return pilaFalse;
    }

    public void setPilaFalse(Stack<String> pilaFalse) {
        this.pilaFalse = pilaFalse;
    }

    public Stack<String> getPilaTrue() {
        return pilaTrue;
    }

    public void setPilaTrue(Stack<String> pilaTrue) {
        this.pilaTrue = pilaTrue;
    }

    public ArrayList<Codigo3D> getListaCodigo3D() {
        return listaCodigo3D;
    }

    public void setListaCodigo3D(ArrayList<Codigo3D> listaCodigo3D) {
        this.listaCodigo3D = listaCodigo3D;
    }

    public TablaVariables getTV() {
        return TV;
    }

    public void setTV(TablaVariables TV) {
        this.TV = TV;
    }

    public TablaSubprogramas getTP() {
        return TP;
    }

    public void setTP(TablaSubprogramas TP) {
        this.TP = TP;
    }
    

    public int getNumeroEtiqueta() {
        return numeroEtiqueta;
    }

    public LinkedList<String> getTablaEtiquetas() {
        return tablaEtiquetas;
    }

    public void setTablaEtiquetas(LinkedList<String> tablaEtiquetas) {
        this.tablaEtiquetas = tablaEtiquetas;
    }
    public void sumarEtiqueta(){
        this.numeroEtiqueta++;
    }
    public GeneradorCodigo3D() {
        this.listaCodigo3D = new ArrayList<>();
        this.gestorFicheros = new GestorFicheros();
        this.tablaEtiquetas = new LinkedList<>();
        this.numeroEtiqueta = 0;
        this.TV = new TablaVariables();
        this.TP = new TablaSubprogramas();
        this.pilaTrue = new Stack<>();
        this.pilaFalse = new Stack<>();
        
    }
     
    public void generarCodigo3Direcciones(Operacion3Direcciones codigo, Operador operando1, Operador operando2, Operador destino) {
        listaCodigo3D.add(new Codigo3D(codigo, operando1, operando2, destino));        
    }
    
    public void generarCodigo3DireccionesPrincipal(Operacion3Direcciones codigo, Operador operando1, Operador operando2, Operador destino) {
        listaCodigo3D.add(0, new Codigo3D(codigo, operando1, operando2, destino));     
    }
    
    public int extensionLista3D() {
        return this.listaCodigo3D.size();
    }
    
    public void archivoRegistroTablas() throws IOException {
        this.gestorFicheros.guardarTablasCodigo3D("Tablas/TablasCodigoIntermedio.txt", this.TP, this.TV, tablaEtiquetas);
    }
    
    // El següent algorisme permet actualitzar els valors que falten   
    public void actualizarDesplazamientos() {
        int ocupacionVar;
        int idSubprograma;
        int aux;
        for (int i = 0; i < this.TV.getTV().size(); i++) {
            ocupacionVar = this.TV.getTV().get(i).getOcupacion();
            idSubprograma = this.TV.getTV().get(i).getIdSubprograma();   
            
            if (this.TV.getTV().get(i).getDesplazamiento() != -1) {
                aux = this.TP.getTP().get(idSubprograma).getOcupacionVariablesLocales();
                aux = aux - ocupacionVar;              
                this.TP.getTP().get(idSubprograma).setOcupacionVariablesLocales(aux);            
                this.TV.getTV().get(i).setDesplazamiento(this.TP.getTP().get(idSubprograma).getOcupacionVariablesLocales());
            } else {
                // Parámetros
                aux = this.TP.getTP().get(idSubprograma).getOcupacionParametros();
                aux = aux + ocupacionVar; // variables parametros
                this.TP.getTP().get(idSubprograma).setOcupacionParametros(aux);              
                this.TV.getTV().get(i).setDesplazamiento(this.TP.getTP().get(idSubprograma).getOcupacionParametros()); // variables parametros
            }
        }   
    }
    
    
}
