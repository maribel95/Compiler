package EstructurasDatos;


import Enumerados.DescripcionValor;
import Ficheros.GestorFicheros;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import Sintactico.Parser;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Stack;

/*
    En esta clase gestionaremos todo el tema de la tabla de símbolos:
    Tiene que ser una estructura formada por:
    1. Indicador del ámbito actual                                               --> int ambitoActual
    2. Tabla de ámbitos                                                          --> ArrayList<Integer> tablaAmbitos
    3. Tabla de expansión(contiene las declaraciones ocultas del ámbito actual)  --> ArrayList<NodoTabla> tablaExpansion
    4. Tabla de descripción(contiene las descripciones de los identificadores)   --> Hashtable<String, NodoTabla> tablaDescripcion
 */

 /*
    OPERACIONES NECESARIAS:
    1. Vaciar : eliminar todo el contenido de las tablas y poner el ámbito actual a 1
    2. Poner : hay que comprobar si el símbolo está o no en la tabla 
    3. Consultar: Dado un símbolo, retornar su descripción y sino está es un error de un identificador sin declarar
    4. Entrar en bloque: incrementa el valor del ámbito actual
    5. Salir del bloque: decrementa el valor del ámbito actual
    6. S’ha de definir un mètode per posar un paràmetre d’un procediment a la taula de símbols
 */
public class TablaSimbolos {

    private int ambitoActual;
    private final Stack<Integer> tablaAmbitos;
    private Hashtable<String, NodoTabla> tablaDescripcion;
    private final LinkedList<NodoTabla> tablaExpansion;

    public int getAmbitoActual() {
        return ambitoActual;
    }

    public Stack<Integer> getTablaAmbitos() {
        return tablaAmbitos;
    }

    public LinkedList<NodoTabla> getTablaExpansion() {
        return tablaExpansion;
    }

    public Hashtable<String, NodoTabla> getTablaDescripcion() {
        return tablaDescripcion;
    }

    private final GestorFicheros gf;

    public TablaSimbolos() {
        gf = new GestorFicheros();
        gf.abrirFicheroTabla();     
        this.tablaDescripcion = new Hashtable<>();
        this.tablaAmbitos = new Stack();
        this.ambitoActual = 0;
        this.tablaExpansion = new LinkedList();
        this.tablaAmbitos.add(this.ambitoActual, 0);
    }

    private boolean existeNodo(String idVar){
        return this.tablaDescripcion.get(idVar) != null;
    }
    // PONER
    // Si no está, añadimos la descripción a la taula de descripción
    // Si ya está, comprobar si la declaración de la variable coincide con la del ámbito actual
    public void poner(String idVar, DescripcionTipo TD) throws Exception{
        if (existeNodo(idVar)) {
            // ERROR REDEFINICIÓN VARIABLE
            if (this.tablaDescripcion.get(idVar).getAmbito() == this.ambitoActual) {
                String mensajeError = "Excepción en la tabla de símbolos de tipo fuera de rango en el método \"PONER\": Ya hay una variable '" +idVar + "'  declarada en este ámbito.";
                gf.escribirError("src/Salidas/Errores/ErroresTablaSimbolos.txt", mensajeError);
                throw new Exception(mensajeError);
            }else{
                // Creamos la tabla de expansión al habernos cercionado de que la descripción anterior no es null
                // MOVER LA DEFINICIÓN ACTUAL A LA TABLA DE EXPANSIÓN
                int idxe = this.tablaAmbitos.get(this.ambitoActual);// idxe = ta[n]
                idxe++;                                             // idxe = idxe + 1
                this.tablaAmbitos.set(this.ambitoActual, idxe);     // ta[n] = idxe
                this.tablaExpansion.add(idxe, new NodoTabla(this.tablaDescripcion.get(idVar).getId(), this.tablaDescripcion.get(idVar).getAmbito(), this.tablaDescripcion.get(idVar).getTipoDescripcion()));           // te[idxe] = (id, td[id].np, td[id].d) 
            }

        }
        // SI NO ESTABA LA VARIABLE, AÑÁDIMOS LA DESCRIPCIÓN            
        this.tablaDescripcion.put(idVar, new NodoTabla(idVar, this.ambitoActual, TD)); // td[id].np = n  y  td[id].d = d
        // Actualizamos fichero
        gf.actualizarFicheroTabla(this,"Poner", "Añadir variable nueva: '" + idVar + "'");
    }

    // CONSULTAR
    // Si existe el identificador, devolvemos su descripción
    // Sino, primero comprobamos que no sea un argumento de la tabla de expansión
    // y sino pues entonces es un error de que no se ha declarado el identificador
    public DescripcionTipo consultar(String idVar) {
        try {
            if (existeNodo(idVar)) return this.tablaDescripcion.get(idVar).getTipoDescripcion(); // retorna td[id].d
            //  COMPROBAMOS SI ES UN ARGUMENTO DE LA TABLA DE EXPANSIÓN
            for(int i = 0; i < this.tablaExpansion.size(); i++){
                if (idVar.equals(this.tablaExpansion.get(i).getIdConstante()))return this.tablaExpansion.get(i).getTipoDescripcion();               
            }
            // Error de identificador no declarado
            String mensajeError = "Excepción en la tabla de símbolos de tipo no existe, en el método CONSULTAR; Error al obtener id:" + idVar;
            gf.escribirError("src/Salidas/Errores/ErroresTablaSimbolos.txt", mensajeError);
            throw new Exception(mensajeError);
        } catch (Exception ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // ENTRAR EN BLOQUE 
    // Incrementamos el valor del nivel del ámbito
    // En la tabla de ámbitos, la entrada actual tiene que valer lo de la entrada anterior
    public void entrarBloque() {
        this.ambitoActual++; // n = n+ 1
        this.tablaAmbitos.add(this.ambitoActual, tablaAmbitos.get(this.ambitoActual - 1)); // ta[n] = ta[n-1]
        // Actualizamos fichero
        gf.actualizarFicheroTabla(this,"Entrar Bloque", "Añadir un nuevo bloque de ámbitos e incrementar el ámbito del programa.");

    }

    // SALIR BLOQUE
    // Si el valor vale 0 es un grave error del compilador
    // Si el valor es mayor que 0, entonces hay que copiar todas las definiciones
    // de la tabla de expansión a la tabla de descripciones desde la posición
    public void salirBloque() throws Exception {
        // if n = 0 { error del compilador!! }
        if (this.ambitoActual == 0) {             
            String mensajeError = "Excepción en la tabla de símbolos de tipo fuera de rango en el método \"SALIR DEL BLOQUE\":No puedes salir del ámbito actual, es el límite!!";
            gf.escribirError("src/Salidas/Errores/ErroresTablaSimbolos.txt", mensajeError);
            throw new Exception(mensajeError);
        }

        int lini = this.tablaAmbitos.get(this.ambitoActual) - 1; // lini = ta[n]
        this.ambitoActual--;                                 // n = n – 1
        int lfi = this.tablaAmbitos.get(this.ambitoActual);  // lfi = ta[n]
        // Traducir variables de la tabla de expansión a variables de la tabla de descripcion
        for (;lini >= lfi; lini--) { // Para todo i que este incluido en este rango  
            if (!(this.tablaExpansion.get(lini).getAmbito() == -1)) {
                // id = te[i].id
                // td[id].np = te[i].np y td[id].d = te[i].d
                // Una vez creada la descripción, la metemos en la tabla de descripciones
                this.tablaDescripcion.put(this.tablaExpansion.get(lini).getId(), new NodoTabla(this.tablaExpansion.get(lini).getId(), this.tablaExpansion.get(lini).getAmbito(),this.tablaExpansion.get(lini).getTipoDescripcion(), -1 )); 
            }
        }
        // Por ultimo, eliminamos todas aquellos identificadores que quedaron en el ámbito anterior
        eliminarIdentificadoresAmbitoAnterior();
        this.tablaAmbitos.pop();
        // Actualizamos
        gf.actualizarFicheroTabla(this, "Salir Bloque", "Eliminar un bloque de ámbitos y decrementar el ámbito del programa.");
    }
    
    // Cuando salimos de un ámbito, borramos todos los identificadores que había ya que ya no
    // se pueden volver a usar
    private void eliminarIdentificadoresAmbitoAnterior(){
            Iterator<String> iterador = this.tablaDescripcion.keySet().iterator(); // Un iterador para recorrer la tabla de hash
            while (iterador.hasNext()) { // Hacemos un recorrido
                // Obtenemos la descripción relacionada con ese identificador
                if (this.tablaDescripcion.get(iterador.next()).getAmbito() > this.ambitoActual) iterador.remove(); // Si el ámbito era mayor, entonces eliminamos la variable          
            }
    }

    // S’ha de definir un mètode per posar un paràmetre d’un procediment a la taula de símbols
    public void ponerParametro(String idpr, String idParametro, DescripcionTipo d)throws Exception {
        // Guardamos la descripción de la tabla de descripciones de la variable
        // this.tablaDescripcion.get(idpr) --> d = td[idpr].d
        // if d.td != dproc { error }
        if (this.tablaDescripcion.get(idpr).getTipoDescripcion().getDescripcionValor() != DescripcionValor.dvSubprograma) {
            String mensajeError = "Excepción en la tabla de símbolos de tipo incorrecto en el método \"PONER PARÁMETRO\":";
            gf.escribirError("src/Salidas/Errores/ErroresTablaSimbolos.txt", mensajeError);
            throw new Exception(mensajeError);
        }            
        int idxe = this.tablaDescripcion.get(idpr).getFirst(); // idxe = td[id].first
        int idxep = -1;                    // idxep = 0
        while (idxe != -1 && !(this.tablaExpansion.get(idxe).getIdConstante().equals(idParametro))) {
            idxep = idxe; // idxep = idxe
            idxe = this.tablaExpansion.get(idxe).getFollow(); // idxe = te[idxe].next
        }
        // if idxe != 0 { error }
        if (idxe != -1) {             
            String mensajeError = "Excepción en la tabla de símbolos de tipo ya existe en el método \"PONER PARÁMETRO\": el siguiente identificador está duplicado '" +idParametro;
            gf.escribirError("src/Salidas/Errores/ErroresTablaSimbolos.txt", mensajeError);
            throw new Exception(mensajeError);
        }
        idxe = this.tablaAmbitos.get(this.ambitoActual); // idxe = ta[n]
        idxe++; //idxe = idxe + 1
        this.tablaAmbitos.set(this.ambitoActual, idxe);  // ta[n] = idxe
        // te[idxe].idcamp = idparam
        // te[idxe].np = -1
        // te[idxe].d = d
        // te[idxe].next = 0
        this.tablaExpansion.add(idxe -1, new NodoTabla(idpr, -1, d, idParametro, -1));

        if (idxep == -1) {
            // td[idpr].first = idxe
            this.tablaDescripcion.get(idpr).setFirst(idxe -1);
            this.tablaDescripcion.put(idpr, this.tablaDescripcion.get(idpr));
        } else {
            // te[idxep].next = idxe
            this.tablaExpansion.get(idxep).setFollow(idxe - 1);
            this.tablaExpansion.set(idxep, this.tablaExpansion.get(idxep));
        }
        // Actualizamos
        gf.actualizarFicheroTabla(this,"Poner Parámetro", "Añadir un parámetro nuevo '" + idParametro + "' al subprograma  '" + idpr + "'");

    }
    
    public void cerrarFichero() {
            gf.cerrarFicheroTabla();
    }
}
