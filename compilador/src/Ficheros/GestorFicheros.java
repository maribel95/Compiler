package Ficheros;

import Codigo3Direcciones.Codigo3D;
import CodigoIntermedioTablas.TablaSubprogramas;
import CodigoIntermedioTablas.TablaVariables;
import Enumerados.DescripcionValor;
import EstructurasDatos.TablaSimbolos;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorFicheros {

    private BufferedWriter bw;
    private FileWriter fw;

    /*
                FICHEROS TABLA SÍMBOLOS
                Tenemos 3 funcionalidades diferentes:
                1. ABRIR EL FICHERO
                2. ACTUALIZAR LA TABLA
                3. CERRAR FICHERO JUSTO AL FINAL DE TODO EL PROCESO
     */
    //**************** TEMA FICHEROS TABLA SÍMBOLOS *****************************
    public void abrirFicheroTabla() {
        try {
            this.fw = new FileWriter("src/Salidas/Tablas/TablaDeSimbolos.txt");
            this.bw = new BufferedWriter(fw);
        } catch (IOException ex) {
            Logger.getLogger(GestorFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void actualizarFicheroTabla(TablaSimbolos TS, String metodo, String info) {

        try {
            this.bw.write(">>>>>>>>>>>>>>>>>>>>>>>> MÉTODO: " + metodo + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
            this.bw.write("\n");
            this.bw.write(info + "\n");
            this.bw.write("*********************  TABLA DE SÍMBOLOS  **************************\n");
            this.bw.write("\n");
            this.bw.write("----------------------------------------------------------------------\n");
            this.bw.write("NIVEL ÁMBITO ACTUAL: " + TS.getAmbitoActual() + "\n");
            this.bw.write("Tabla de ámbitos\n");
            for (int i = 0; i < TS.getTablaAmbitos().size(); i++) {
                bw.write("Ámbito: " + i + ", Puntero: " + TS.getTablaAmbitos().get(i) + "\n");
            }
            this.bw.write("----------------------------------------------------------------------\n");
            this.bw.write("TABLA DESCRIPCIÓN:\n\r");
            for (String llave : TS.getTablaDescripcion().keySet()) {
                bw.write("\n----------------------------------------------------------------------\n");
                bw.write("Id: "+TS.getTablaDescripcion().get(llave).getId()+"\n");
                bw.write("Ámbito: "+TS.getTablaDescripcion().get(llave).getAmbito()+"\n");
                bw.write("First: "+TS.getTablaDescripcion().get(llave).getFirst()+"\n");
                bw.write("Follow: "+TS.getTablaDescripcion().get(llave).getFollow()+"\n");               
                if(TS.getTablaDescripcion().get(llave).getIdConstante() != null)bw.write("IdConstante: "+TS.getTablaDescripcion().get(llave).getIdConstante()+"\n"); 
                bw.write("Tipo Descripción: \n");
                bw.write("      Tipo valor: "+TS.getTablaDescripcion().get(llave).getTipoDescripcion().getDescripcionValor()+"\n");
                // Si es un tipo, imprimimos las características de este
                if (TS.getTablaDescripcion().get(llave).getTipoDescripcion().getDescripcionValor() == DescripcionValor.dvTipo) {
                    bw.write("      Tipo subjacente básico: "+TS.getTablaDescripcion().get(llave).getTipoDescripcion().getTipoSubjacenteBasico()+"\n");
                    bw.write("      Ocupacion: "+TS.getTablaDescripcion().get(llave).getTipoDescripcion().getOcupacion()+"\n");
                    bw.write("      Límite superior: "+TS.getTablaDescripcion().get(llave).getTipoDescripcion().getLimiteSup()+"\n");
                    bw.write("      Límite Inferior: "+TS.getTablaDescripcion().get(llave).getTipoDescripcion().getLimiteInf()+"\n");
                    // Si es una función, un argumento o una variable
                } else {
                    bw.write("      Nombre del tipo: "+TS.getTablaDescripcion().get(llave).getTipoDescripcion().getNombreId()+"\n");        
                    switch(TS.getTablaDescripcion().get(llave).getTipoDescripcion().getNombreId()){
                        
                        case "str":  bw.write("      Valor: "+(String) TS.getTablaDescripcion().get(llave).getTipoDescripcion().getValorConstante()+"\n");   break;
                        case "bool": bw.write("      Valor: "+(Boolean) TS.getTablaDescripcion().get(llave).getTipoDescripcion().getValorConstante()+"\n");  break;
                        case "int":  bw.write("      Valor: "+(Integer) TS.getTablaDescripcion().get(llave).getTipoDescripcion().getValorConstante()+"\n");               
                    }
                }
                bw.write("\n----------------------------------------------------------------------\n");
            }
            this.bw.write("----------------------------------------------------------------------\n");
            this.bw.write("TABLA EXPANSIÓN:\n\r");
            for (int llave = 0; llave < TS.getTablaExpansion().size(); llave++) {
                bw.write("\n----------------------------------------------------------------------\n");
                bw.write("Id: "+TS.getTablaExpansion().get(llave).getId()+"\n");
                bw.write("Ámbito: "+TS.getTablaExpansion().get(llave).getAmbito()+"\n");
                bw.write("First: "+TS.getTablaExpansion().get(llave).getFirst()+"\n");
                bw.write("Follow: "+TS.getTablaExpansion().get(llave).getFollow()+"\n");               
                if(TS.getTablaExpansion().get(llave).getIdConstante() != null)bw.write("IdConstante: "+TS.getTablaExpansion().get(llave).getIdConstante()+"\n"); 
                bw.write("Tipo Descripción: \n");
                bw.write("      Tipo valor: "+TS.getTablaExpansion().get(llave).getTipoDescripcion().getDescripcionValor()+"\n");
                // Si es un tipo, imprimimos las características de este
                if (TS.getTablaExpansion().get(llave).getTipoDescripcion().getDescripcionValor() == DescripcionValor.dvTipo) {
                    bw.write("      Tipo subjacente básico: "+TS.getTablaExpansion().get(llave).getTipoDescripcion().getTipoSubjacenteBasico()+"\n");
                    bw.write("      Ocupacion: "+TS.getTablaExpansion().get(llave).getTipoDescripcion().getOcupacion()+"\n");
                    bw.write("      Límite superior: "+TS.getTablaExpansion().get(llave).getTipoDescripcion().getLimiteSup()+"\n");
                    bw.write("      Límite Inferior: "+TS.getTablaExpansion().get(llave).getTipoDescripcion().getLimiteInf()+"\n");
                    // Si es una función, un argumento o una variable
                } else {
                    bw.write("      Nombre del tipo: "+TS.getTablaExpansion().get(llave).getTipoDescripcion().getNombreId()+"\n");        
                    switch(TS.getTablaExpansion().get(llave).getTipoDescripcion().getNombreId()){
                        
                        case "str":  bw.write("      Valor: "+(String) TS.getTablaExpansion().get(llave).getTipoDescripcion().getValorConstante()+"\n");   break;
                        case "bool": bw.write("      Valor: "+(Boolean) TS.getTablaExpansion().get(llave).getTipoDescripcion().getValorConstante()+"\n");  break;
                        case "int":  bw.write("      Valor: "+(Integer) TS.getTablaExpansion().get(llave).getTipoDescripcion().getValorConstante()+"\n");               
                    }
                }
                bw.write("\n----------------------------------------------------------------------\n");
            }
            if (TS.getTablaExpansion().isEmpty()) {
                this.bw.write("Está vacía\n\r");
            }
            this.bw.write("----------------------------------------------------------------------\n");
            this.bw.write("*********************************************************************\n");
        } catch (IOException ex) {
            Logger.getLogger(GestorFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cerrarFicheroTabla() {

        try {
            this.bw.close();
        } catch (IOException ex) {
            Logger.getLogger(GestorFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //**************************************************************************
    /*
                FICHEROS CÓDIGO INTERMEDIO
                Tenemos 1 única funcionalidad:
                1. GUARDAR LA TABLA DEL CÓDIGO EN UN FICHERO
     */
    //************ TEMA FICHEROS CODIGO INTERMEDIO ****************
    public void guardarTablasCodigo3D(String nombreFichero, TablaSubprogramas TP, TablaVariables TV, LinkedList<String> tablaEtiquetas) throws IOException {
        String mensaje = "************************ CÓDIGO INTERMEDIO *************************\n";
        mensaje += "\nSubprogramas:\n";
        for (int i = 0; i < TP.getTP().size(); i++) {
            mensaje += "ID " + i + "\n";
            mensaje += "\n----------------------------------------------------------------------\n";
            mensaje += "DESCRIPCIÓN SUBPROGRAMA:\n" ;
            mensaje += "- Nivel de profundidad = " + TP.getTP().get(i).getProfundidad()+"\n"; 
            mensaje += "- Etiqueta inicial = " + TP.getTP().get(i).getEtiqueta() +"\n";
            mensaje += "- Ocupación de las variables locales = " + TP.getTP().get(i).getOcupacionVariablesLocales() +"\n";                     
            mensaje += "- Ocupación parámetros = " + TP.getTP().get(i).getOcupacionParametros() +"\n";               
            mensaje += "- Número de parámetros = " + TP.getTP().get(i).getNumeroParametros()+"\n"  ;               
            mensaje += "- Ocupación = " + TP.getTP().get(i).getOcupacionTotal() +"\n";
            mensaje += "- Tipo subjacente básico = " + TP.getTP().get(i).getTipoSubjacenteBasico() +"";
            mensaje += "\n----------------------------------------------------------------------\n";
        }
        mensaje += "Variables:\n";
        for (int i = 0; i < TV.getTV().size(); i++) {
            mensaje += "ID " + i + ": \n";
            mensaje += "\n----------------------------------------------------------------------\n";
            mensaje += "DESCRIPCION VARIABLE:\n";
            mensaje += "Nombre = " + TV.getTV().get(i).getNombre() + "\n";
            mensaje += "Número variable = "+ TV.getTV().get(i).getNumeroVariable()+"\n";
            mensaje += "IdSubprograma = " + TV.getTV().get(i).getIdSubprograma() + "\n";
            mensaje += "Ocupación = " + TV.getTV().get(i).getOcupacion() + "\n";
            mensaje += "Desplazamiento = " + TV.getTV().get(i).getDesplazamiento() + "\n";
            mensaje += "Tipo subjacente básico = " + TV.getTV().get(i).getTipoSubjacenteBasico();       
            mensaje += "\n----------------------------------------------------------------------\n";
        }
        mensaje += "\nEtiquetas:\n";
        for (int i = 0; i < tablaEtiquetas.size(); i++) {
            mensaje += "ID " + i + ": " + tablaEtiquetas.get(i) + "\n";
        }
        escribirFichero(nombreFichero, mensaje);
    }
    //**********************************************************************+***
        
        /*
                CÓDIGO DE 3 DIRECCIONES
                Tenemos 1 única funcionalidad:
                1. GUARDAR LA LISTA DE INSTRUCCIONES EN UN FICHERO
        */   
    //**************************************************************************  
        public void archivoRegistroCodigo3D(String nombreFichero, ArrayList<Codigo3D> listaCodigo3D) {
        String mensaje = "********** Instrucciones Código intermedio ***********\n\n";      
        for (int indice = 0; indice < listaCodigo3D.size(); indice++) {
            mensaje+= (indice + 1)+". (" + listaCodigo3D.get(indice).getCodigo();
            if(listaCodigo3D.get(indice).getOperando1() != null){
                mensaje +=" | OPERANDO 1: Categoria-> "+ listaCodigo3D.get(indice).getOperando1().getCategoriaOperador() +", valor -> "+ listaCodigo3D.get(indice).getOperando1().getValor();
            }else{
                mensaje += " | OPERANDO 1: -> Null";
            }
            if(listaCodigo3D.get(indice).getOperando2() != null){
                mensaje +=" | OPERANDO 2: Categoria -> "+ listaCodigo3D.get(indice).getOperando2().getCategoriaOperador() +", valor -> "+ listaCodigo3D.get(indice).getOperando2().getValor();
            }else{
                mensaje += " | OPERANDO 2: -> Null";
            }
            if(listaCodigo3D.get(indice).getDestino() != null){
                mensaje +=" | DESTINO: Categoria -> "+ listaCodigo3D.get(indice).getDestino().getCategoriaOperador() +", valor -> "+listaCodigo3D.get(indice).getDestino().getValor()+ ")\n\n";
            }else{
                mensaje += " | DESTINO: -> Null)\n\n";
            }
        }       
        escribirFichero(nombreFichero, mensaje);      
    }   
    //**********************************************************************+***
        
        
        
    //**************************************************************************

    /*
                FICHEROS ERRORES
                Tenemos 1 única funcionalidad:
                1. ESCRIBIR EL MENSAJE DE ERROR RECIBIDO
     */
    //************************   FICHERO ERRORES    ****************************
    public void escribirError(String nombreFicheroError, String mensajeError) {

        try {
            FileWriter fwE = new FileWriter(nombreFicheroError);
            try (BufferedWriter bwE = new BufferedWriter(fwE)) {
                bwE.write("------------------------ ERRORES ENCONTRADOS --------------------------\n\r");
                bwE.write(mensajeError + "\n");
                bwE.write("------------------------------------------------------------------------");
            }
        } catch (IOException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //**************************************************************************

    /*
                FICHEROS ENTRADAS PARA COMPILAR
                Tenemos 4 funcionalidades diferentes:
                1. ESCRIBIR EN EL FICHERO
                2. ELIMINAR TODOS LOS FICHEROS DE UN DIRECTORIO
                3. MOVER UN FICHERO DE UN DIRECTORIO A OTRO
     */
    //*************** TEMA FICHEROS ENTRADAS PARA COMPILAR *********************
    // ESCRIBIR EN FICHERO, PARÁMETROS: NOMBRE ARCHIVO Y CONTENIDO 
    public void escribirFichero(String nombreFichero, String mensaje) {
        try {
            FileWriter fwG = new FileWriter("src/Salidas/" + nombreFichero);
            try (BufferedWriter bwG = new BufferedWriter(fwG)) {
                bwG.write(mensaje);
            }

        } catch (IOException ex) {
            Logger.getLogger(GestorFicheros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ELIMINAR TODA LA LISTA DE ARCHIVOS DE UN FICHERO
    public void borrarFicherosSalidas() {
        File carpeta = new File("src/Salidas/Ensamblador");
        for (File ficheros : carpeta.listFiles()) {
            ficheros.delete();
        }
        carpeta = new File("src/Salidas/Codigo3Direcciones");
        for (File ficheros : carpeta.listFiles()) {
            ficheros.delete();
        }
        carpeta = new File("src/Salidas/Tablas");
        for (File ficheros : carpeta.listFiles()) {
            ficheros.delete();
        }
        carpeta = new File("src/Salidas/Tokens");
        for (File ficheros : carpeta.listFiles()) {
            ficheros.delete();
        }
        carpeta = new File("src/Salidas/Errores");
        for (File ficheros : carpeta.listFiles()) {
            ficheros.delete();
        }
    }

    // MOVER FICHERO DE UNA RUTA A OTRA
    public void moverFichero(String nuevaRuta, String antiguaRuta) {
        File ficheroAMover = new File(antiguaRuta);
        ficheroAMover.renameTo(new File(nuevaRuta));
    }

    //**************************************************************************
}
