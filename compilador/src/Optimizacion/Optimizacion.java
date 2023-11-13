/*
    Gestión de todas las optimizaciones de código
 */
package Optimizacion;


import Codigo3Direcciones.Codigo3D;
import Enumerados.Operacion3Direcciones;
import Enumerados.CategoriaOperador;
import java.util.ArrayList;

public class Optimizacion {    
    private ArrayList<Codigo3D> listaCodigo3D;
    private boolean hayCambios = true;
    
    public void setListaCodigo3D(ArrayList<Codigo3D> listaCodigo3D) {
        this.listaCodigo3D = listaCodigo3D;
    }
    
    /*
        Función principal en la que se recorren todas las instruccion de nuestra lista de código de 3 direcciones
        La idea es que se recorrera la lista las veces que haga falta, siempre que haya cambios pendientes por realizar
        Dentro del for, se recorren todas las instrucciones de la lista, una por una, para ver cuál de las dos optimizaciones
        se puede realizar.
    */
    public ArrayList<Codigo3D> optimizarC3D() {
        while(hayCambios){
            hayCambios = false;
            for (int i = 0; i < this.listaCodigo3D.size(); i++) {
                switch (this.listaCodigo3D.get(i).getCodigo()) {
                    case saltoIncondicional:
                        saltosSubjacentes(this.listaCodigo3D.get(i), i);
                        break; 
                    case copia:
                        optimizarAsignacionesDiferidas(this.listaCodigo3D.get(i), i);
                }
            }
        }
        return this.listaCodigo3D;
    }
    /* 
    Código para optimizar las asignaciones diferidas:
    ANTES:
        t1 = 1
        i = t1
    AHORA:
        i = 1
    */
    private void optimizarAsignacionesDiferidas(Codigo3D instruccion, int indice) {
        this.hayCambios = false;  // Booleano que indica si la instruccion se puede eliminar o no
        Codigo3D siguienteInstr = this.listaCodigo3D.get(indice + 1);  // Tenemos en cuenta la instrucción siguiente        
        for(int contador = 1; siguienteInstr.getCodigo() == Operacion3Direcciones.copia && this.listaCodigo3D.size() != indice + contador; contador++){ // Hacemos un recorrido de las instrucciones que sean asignaciones
            // En este if comprobamos si el valor de la instruccion actual coincide con el de la siguiente instrucción
            if (instruccion.getDestino().getValor().equals(siguienteInstr.getOperando1().getValor()) && (CategoriaOperador.valorAritmetico == instruccion.getOperando1().getCategoriaOperador()|| CategoriaOperador.valorBooleano == instruccion.getOperando1().getCategoriaOperador()|| CategoriaOperador.valorString == instruccion.getOperando1().getCategoriaOperador())) {
                this.hayCambios = true; // Si es que coinciden, se puede eliminar
                // Actualizamos lo valores de la siguiente instrucción
                siguienteInstr.getOperando1().setCategoriaOperador(instruccion.getOperando1().getCategoriaOperador());
                siguienteInstr.getOperando1().setValor(instruccion.getOperando1().getValor());               
            }            
            siguienteInstr = this.listaCodigo3D.get(indice + contador+1); // Miramos la siguiente instrucción
        }
        // Si se puede eliminar la quitamos de la lista
        if (this.hayCambios) this.listaCodigo3D.remove(indice);
    }

    /*
     En las instrucciones if, nos ahorramos una instrucción de salto
    
      ANTES: 
      if a > b goto e1
      goto e2
 e1:  skip
    
      AHORA:
      if a < b goto e2      
     */
    private void saltosSubjacentes(Codigo3D instruccion, int indice) {
        // Valor de la instrucción pasada por parámetro
        String etiquetaNuevoSalto = instruccion.getDestino().getValor(); 
        indice++;
        // Recorremos toda la lista del código a partir del índice que nos han pasado por parámetro + 1
        // para no tener en cuenta la instrucción actual, ya que sería redundante
        while(indice < this.listaCodigo3D.size() && (indice + 1) != this.listaCodigo3D.size()) { 
            // Si la instrucción que estamos comprobando ahora es un skip,
            // y el lugar del salto coincide con la instrucción pasada por parámetro
            // y la siguiente instrucción es un salto, entonces actualizamos el valor de la etiqueta
            // y indicamos la nueva etiqueta a donde tiene que saltar
            if (this.listaCodigo3D.get(indice).getCodigo() == Operacion3Direcciones.etiquetaPasar && this.listaCodigo3D.get(indice).getDestino().getValor().equals(etiquetaNuevoSalto) && this.listaCodigo3D.get(indice + 1).getCodigo() == Operacion3Direcciones.saltoIncondicional) etiquetaNuevoSalto=this.listaCodigo3D.get(indice + 1).getDestino().getValor();            
            indice++;
        }
        // Finalmente, actualizamos la instrucción que nos pasaron como parámetro, con la nueva etiqueta de salto
        instruccion.getDestino().setValor(etiquetaNuevoSalto);
    }
    
    
}
