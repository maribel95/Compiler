/*
    Clase que transformará el código 3 direcciones a ensamblador
 */
package Ensamblador;

import Codigo3Direcciones.Codigo3D;
import CodigoIntermedioTablas.DescripcionVariable;
import CodigoIntermedioTablas.TablaSubprogramas;
import CodigoIntermedioTablas.TablaVariables;
import Enumerados.CategoriaOperador;
import Enumerados.Operacion3Direcciones;
import Ficheros.GestorFicheros;
import Enumerados.TipoSubjacenteBasico;
import java.util.ArrayList;
import java.util.LinkedList;

public class TraductorCodigo {
    private final ArrayList<Codigo3D> listaCodigo3D;
    private final String nombreFichero;
    private final ArrayList<String> variables;
    private final GestorFicheros gf;
    private final LinkedList<String> tablaEtiquetas;
    private final TablaVariables TV;
    private final TablaSubprogramas TP;

    public TraductorCodigo(String nombreFichero, ArrayList<Codigo3D> listaCodigo3D, LinkedList<String> tablaEtiquetas, TablaSubprogramas TP, TablaVariables TV) {
        this.nombreFichero = nombreFichero;
        this.listaCodigo3D = listaCodigo3D;
        this.variables = new ArrayList<>();
        this.gf = new GestorFicheros();
        this.tablaEtiquetas = tablaEtiquetas;
        this.TV = TV;
        this.TP = TP;
    }

    /*
     * Recorre la lista entera del código de 3 direcciones para ir generando el código ensamblador
     */
    public void generarCodigoEnsamblador() throws Exception {
        String codigoEnsamblador  = "*-----------------------------------------------------------\n";
        codigoEnsamblador += "* Title      : Practica\n";
        codigoEnsamblador += "* Written by : Odilo Fortes, Iker Diaz, Maribel Crespi \n";
        codigoEnsamblador += "* Date       : 28/1/2022\n";
        codigoEnsamblador += "* Description: Codigo ensamblador.\n";
        codigoEnsamblador += "* -------------------------------------------------------- *\n";
        codigoEnsamblador += "\tORG    $1000\n";
        codigoEnsamblador += "START:\t\t\t\t; first instruction of program\n*  Put program code here\n\n";
        
        for (int i = 0; i < this.listaCodigo3D.size(); i++) {
            codigoEnsamblador += ensamblarInstruccion(this.listaCodigo3D.get(i), i);
        }
        codigoEnsamblador += "\n* Put variables and constants here\n\n";
        for (int i = 0; i < this.variables.size(); i++) {
            codigoEnsamblador += "STR_" + i + " DC.B " + this.variables.get(i).replace("\"", "'") + ", 0\n";
        }
        codigoEnsamblador += "BUFF DS.B 1024\n";
        codigoEnsamblador += "\n\tEND\tSTART\t\t; last line of source\n";
        gf.escribirFichero(nombreFichero, codigoEnsamblador);
    }

    /*
     * Genera el código ensamblador de una instrucción pasada por parámetro.
     */
    private String ensamblarInstruccion(Codigo3D instruccion, int indice) throws Exception {

        String mensaje = "";
        switch (instruccion.getCodigo()) {
            case llamadaMain:
                mensaje += "\tJSR\t" + this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getEtiqueta().toUpperCase() + "\n" + "\tSIMHALT\t\t\t; halt simulator\n";
                break;
            case inicializacion:
                mensaje += "\tSUB.L\t#" + Math.abs(this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacionParametros()) + ", SP\n"+"\tSUB.L\t#4, SP" + "\t;\tBP\n"+"\tMOVE.L\t#0,(SP)\n"+"\tMOVE.L\tSP, A3\n"+"\tSUB.L\t#" + Math.abs(this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacionVariablesLocales()) + ", A3\t;\tActualizar SP para nuevo Bloque de activación\n";
                break;
            case llamada:
                mensaje += "\tMOVE.L\tSP, A5\n" + "\tMOVE.L\tA3, SP\n" + "\tMOVE.L\tA5,-(SP) " + "\t;\tBP anterior\n";
                if (this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getTipoSubjacenteBasico() != TipoSubjacenteBasico.tsVoid) {
                    mensaje += "\tSUB.L\t#" + this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacionTotal() + ", SP" + "\t;\tEspacio de memoria para el retorno del BP\n";
                }
                mensaje += "\tSUB.L\t#4, SP" + "\t;\tEspacio memoria para el PC\n";

                if (this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getNumeroParametros() > 0) {
                    boolean quedanParametros = true;
                    indice--;
                    while (quedanParametros) {
                        if (Operacion3Direcciones.parametroSimple == this.listaCodigo3D.get(indice).getCodigo()) {
                            mensaje += "\tCLR.L\tD0 \n";
                            switch (this.TV.getVariable(Integer.parseInt(this.listaCodigo3D.get(indice).getOperando1().getValor())).getTipoSubjacenteBasico()) {
                                case tsEntero:
                                    mensaje += "\tMOVE.L\tSP, A4 \n" + "\tMOVE.L\tA5, SP \n" + "\tMOVE.L\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(this.listaCodigo3D.get(indice).getOperando1().getValor()))) + "(SP), D0\n" + "\tMOVE.L\tA4, SP \n" + "\tMOVE.L\tD0, -(SP) \n";
                                    break;
                                case tsBooleano:
                                    mensaje += "\tMOVE.L\tSP, A4 \n" + "\tMOVE.L\tA5, SP \n" + "\tMOVE\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(this.listaCodigo3D.get(indice).getOperando1().getValor()))) + "(SP), D0 \n" + "\tMOVE.L\tA4, SP \n" + "\tMOVE\tD0, -(SP) \n";
                               }
                            if (Integer.parseInt(this.listaCodigo3D.get(indice).getOperando2().getValor()) == 1) quedanParametros = false;                           
                        }
                        indice--;
                    }
                }
                mensaje += "\tADD.L\t#" + Math.abs(this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacionParametros() + 4) + ", SP\n"+"\tJSR " + this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getEtiqueta().toUpperCase() + "\n";
                // Volvemos del subprograma
                mensaje += "\tADD.L\t#" + this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacionTotal() + ", SP" + "\t;\tRetorno del salto\n"+"\tMOVE.L\t(SP)+, A5" + "\t;\tObtener BP\n"+"\tMOVE.L\tSP, A3" + "\t;\tA3 contiene el SP\n" + "\tMOVE.L\tA5, SP" + "\t;\tActualizar BP\n";
                break;
            case idSubprograma:
                mensaje += this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getEtiqueta().toUpperCase() + ":\n";
                break;
            case finSubprograma:
                mensaje += "\tADD.L\t#" + Math.abs(this.TP.getSubprograma(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacionParametros() + 4) /* +4 porque es lo que ocupa en bytes el BP */ + ", SP\n"+"\tRTS\n";
                break;
            case retorno:
                if (TipoSubjacenteBasico.tsEntero == this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getTipoSubjacenteBasico()) {
                    mensaje += "\tMOVE.L\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP), D0\n"+"\tMOVE.L\tD0, " + Math.abs(this.TP.getSubprograma(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacionParametros() + 4 + 4) + "(SP)\n";
                } else if (TipoSubjacenteBasico.tsBooleano == this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getTipoSubjacenteBasico()) {
                    mensaje += "\tMOVE\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP), D0\n"+"\tMOVE\tD0, " + Math.abs(this.TP.getSubprograma(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacionParametros() + 4 + 4) + "(SP)\n";
                } else if (TipoSubjacenteBasico.tsString == this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getTipoSubjacenteBasico()) {
                    int retornoString = identificarParametroString(Integer.parseInt(instruccion.getDestino().getValor()));
                    if (retornoString == 0) retornoString = this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getDesplazamiento();                    
                    mensaje += "\tMOVE.L\tSP, A2\n\tADD.L\t#" + Math.abs(this.TP.getSubprograma(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacionParametros()) + 4 + 4 + ",A2\n\tMOVE.L\tSP, A1\n\tADD.L\t#" + retornoString + ", A1\n\t MOVE.L\t#" + this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion() + ", D1\n\tCMP.L\t#0, D1\n\tBEQ\tFINAL_RETORNO"+indice+"\nSIGUIENTE_CARACTER_RETS"+indice+"\n\tSUB.L\t#2, D1\n\tMOVE\t(A1)+, D2\n\tMOVE\tD2,(A2)+\n\tCMP.L\t#0, D1\n\tBNE\tSIGUIENTE_CARACTER_RETS"+indice+"\nFINAL_RETORNO"+indice+"\n";
                }
                break;
            case etiquetaPasar:
                mensaje += this.tablaEtiquetas.get(Integer.parseInt(instruccion.getDestino().getValor()) - 1) + ":\n";
                break;
            case saltoIncondicional:
                mensaje += "\tJMP\t" + this.tablaEtiquetas.get(Integer.parseInt(instruccion.getDestino().getValor()) - 1) + "\n";
                break;
            case esTrue:
                mensaje += "\tMOVE\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor()))) + "(SP), D0\n" + "\tCMP\t#0, D0\n"+"\tBNE\t" + this.tablaEtiquetas.get(Integer.parseInt(instruccion.getDestino().getValor()) - 1) + "\n";
                break;
            case esFalse:
                mensaje += "\tMOVE\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor()))) + "(SP), D0\n" + "\tCMP\t#1, D0\n"+"\tBNE\t" + this.tablaEtiquetas.get(Integer.parseInt(instruccion.getDestino().getValor()) - 1) + "\n";
                break;
            case entradaDatos:
                mensaje += "\tCLR.L\tD1\n\tMOVE.L\t#4, D0\n\tTRAP\t#15\n\tMOVE.L\tD1," + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                break;
            case salidaDatos:
                switch (this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getTipoSubjacenteBasico()) {
                    case tsString:
                        int desplazamientoVariable = identificarParametroString(Integer.parseInt(instruccion.getDestino().getValor()));
                        if (desplazamientoVariable == 0) desplazamientoVariable = this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getDesplazamiento();                       
                        mensaje += "\tMOVE.L\t#BUFF, A1\n\tMOVE.L\tSP, A2\n\tADD.L\t#" + desplazamientoVariable + ",A2\n\tMOVE.L\t#" + (this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion() / 2) + ",D1\n\tCMP.L\t#0, D1\n\tBEQ\tFIN_SALIDA"+indice+"\nSIGUIENTE_CARACTER_SS"+indice+":\n\tMOVE\t(A2)+, D0\n\tCMP\t#8224, D0\n\tBEQ FIN_SALIDA"+indice+"\n\tMOVE.B\tD0,(A1)+\n\tSUB.L\t#1, D1\n\tCMP.L\t#0, D1\n\tBNE\tSIGUIENTE_CARACTER_SS"+indice+"\nFIN_SALIDA"+indice+":\n\tMOVE\t#1, D0\n\tMOVE.L\t#" + (this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion() / 2)+",D1\n\tMOVE.L\t#BUFF, A1\n\tTRAP\t#15\n";
                        break;
                    case tsEntero:
                        mensaje += "\tCLR.L\tD0\n\tMOVE.L\t" + this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getDesplazamiento() + "(SP), D1\n\tMOVE #20, D0\n\tTRAP #15\n";
                        break;
                    case tsBooleano:
                        mensaje += "\tCLR.L\tD0\n\tCLR.L\tD1\n\tMOVE " + this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getDesplazamiento() + "(SP), D1\nMOVE #20, D0\n\tTRAP #15\n";
                }
                mensaje += "\tCLR\tD0\n\tMOVE.L\t#BUFF ,A1\n\tMOVE.L\t#0, D1\n\tTRAP #15\n";
                break;
            case copia:
                if(instruccion.getOperando1().getCategoriaOperador() == CategoriaOperador.variable){
                        if (TipoSubjacenteBasico.tsEntero == this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getTipoSubjacenteBasico()) {
                            mensaje += "\tMOVE.L\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor()))) + "(SP)," + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                        } else if (TipoSubjacenteBasico.tsBooleano == this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getTipoSubjacenteBasico()) {
                            mensaje += "\tMOVE\t" + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor()))) + "(SP)," + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                        } else if (TipoSubjacenteBasico.tsString == this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getTipoSubjacenteBasico()) {
                            if (this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacion() >= this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion()) {
                                String mensajeError = "Excepción en la sintaxis del programa. Error al intentar asignar un string de mayor tamaño a uno de menor tamaño.\n" + "La variable fuente es " + this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getNombre() + ", con tamaño " + this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacion() + " (en bytes).\n" + "La variable destino es " + this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getNombre() + ", con tamaño " + this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion() + " (en bytes).";
                                gf.escribirError("src/Salidas/Errores/ErroresSintacticos.txt", mensajeError);
                                throw new Exception(mensajeError);
                            }
                            int desplazamientoOperando1 = identificarParametroString(Integer.parseInt(instruccion.getOperando1().getValor()));
                            if (desplazamientoOperando1 == 0)desplazamientoOperando1 = this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getDesplazamiento();                           
                            int desplazamientoDestino = identificarParametroString(Integer.parseInt(instruccion.getDestino().getValor()));
                            if (desplazamientoDestino == 0) desplazamientoDestino = this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getDesplazamiento();                           
                            mensaje += "\tMOVE.L SP, A1\n\tADD.L #" + desplazamientoDestino + ",A1\n\tMOVE.L #" + Math.abs(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacion() - this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion()) + ",D1\n\tMOVE.L SP,A2\n\tADD.L #" + desplazamientoOperando1 + ",A2\n\tMOVE.L #" + this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacion() + ",D2\n\tCMP.L #0, D2\n\tBEQ PONER_RESTANTE"+indice+"\n\tCLR.L D3\nPONER_CARACTER_AVS"+indice+"\n\tMOVE (A2)+,D3\n\tMOVE\tD3,(A1)+\n\tSUB.L\t#2, D2\n\tCMP.L\t#0, D2\n\tBNE\tPONER_CARACTER_AVS"+indice+"\nPONER_RESTANTE"+indice+"\t;\tSi variable1 = variable2 && variable1 > variable2\n\tCMP.L\t#0, D1\n\tBEQ\tCOPIADO_AVS"+indice+"\n\tCLR.L\tD2\n\tMOVE\t#8224, D2\t;\tEspacio en blanco\nPONER_BLANCO"+indice+"\n\tMOVE\tD2,(A1)+\n\tSUB.L\t#2, D1\n\tCMP.L\t#0, D1\n\tBNE\tPONER_BLANCO"+indice+"\nCOPIADO_AVS"+indice+":\n";
                        }
                }else if(instruccion.getOperando1().getCategoriaOperador() == CategoriaOperador.subprograma){
                        if (TipoSubjacenteBasico.tsEntero == this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getTipoSubjacenteBasico()) {
                            mensaje += "\tMOVE.L\tSP, A5\n\tMOVE.L\tA3, SP\n\tSUB.L\t#8, SP\n\tMOVE.L\t(SP), D0\n\tMOVE.L\tA5, SP\n\tMOVE.L\tD0," + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                        } else if (TipoSubjacenteBasico.tsBooleano == this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getTipoSubjacenteBasico()) {
                            mensaje += "\tCLR.L\tD0\n\tMOVE.L\tSP, A5\n\tMOVE.L\tA3, SP\n\tSUB.L\t#6, SP\n\tMOVE\t(SP), D0\n\tMOVE.L\tA5, SP\n\tMOVE D0," + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                        } else if (TipoSubjacenteBasico.tsString == this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getTipoSubjacenteBasico()) {
                            int desplazamientoRetornoDestino = identificarParametroString(Integer.parseInt(instruccion.getDestino().getValor()));
                            if (desplazamientoRetornoDestino == 0) {
                                desplazamientoRetornoDestino = this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getDesplazamiento();
                            }
                            mensaje += "\tMOVE.L\tA3, A1\n\tSUB.L\t#" + this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacion() + ", A1\n\tSUB.L\t#4, A1\n\tMOVE.L\tSP, A2\n\tADD.L\t#" + desplazamientoRetornoDestino + ", A2\n\tMOVE.L\t#" + this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion() + ",D0\n\tMOVE.L\t#" + this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())).getOcupacion()+",D1\n\tCMP.L\t#0, D1\n\tBEQ\tPONER_STRING"+indice+"\nSIGUIENTE_CARACTER_RS"+indice+"\n\tMOVE\t(A1)+, D3\n\tMOVE\tD3, (A2)+\n\tSUB.L\t#2, D1\n\tCMP.L\t#0, D1\n\tBNE SIGUIENTE_CARACTER_RS"+indice+"\nPONER_STRING"+indice+"\n\tCMP.L\t#0, D0\n\tBEQ\tFIN_STRING"+indice+"\n\tCLR.L\tD1\n\tMOVE\t#8224, D1\t;\tEspacios\nESTA_VACIO"+indice+"\n\tMOVE\tD1,(A2)+\n\tSUB.L\t#2, D0\n\tCMP.L\t#0, D0\n\tBNE\tESTA_VACIO"+indice+"\nFIN_STRING"+indice+"\n";
                        }
                } else if(instruccion.getOperando1().getCategoriaOperador() == CategoriaOperador.valorAritmetico){
                        mensaje += "\tMOVE.L\t"+"#" + Integer.parseInt(instruccion.getOperando1().getValor())+"," + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP) \n";
                }else if(instruccion.getOperando1().getCategoriaOperador() == CategoriaOperador.valorBooleano){
                        mensaje += "\tMOVE\t";
                        if (instruccion.getOperando1().getValor().equals("true")) mensaje += "#1";else  mensaje += "#0";
                        mensaje += "," + this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                } else if(instruccion.getOperando1().getCategoriaOperador() == CategoriaOperador.valorString){
                        int desplazamientoParametros = identificarParametroString(Integer.parseInt(instruccion.getDestino().getValor()));
                        if (desplazamientoParametros == 0)desplazamientoParametros = this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getDesplazamiento();                       
                        this.variables.add(instruccion.getOperando1().getValor());
                        mensaje += "\tMOVE.L\tSP, A1\n\tADD.L\t"+ "#" + desplazamientoParametros+",A1\n\tMOVE.L\t#STR_" +( this.variables.size() - 1)+",A2\n\tMOVE.L\t #" + this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())).getOcupacion() + ",D0\n\tCMP.L\t#0, D0\n\tBEQ\tCOPIADO"+indice+"\n\tCLR.L D1\nPONER_CARACTER_ADS"+indice+":\n\tMOVE.B\t(A2)+, D1\n\tMOVE\tD1,(A1)+\n\tSUB.L #2, D0\n\tCMP.L #0, D0\n\tBNE\tPONER_CARACTER_ADS"+indice+"\nCOPIADO"+indice+":\n";                     
                }
                break;
            case and:
                mensaje += "\tMOVE\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP), D1\n\tMOVE\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP), D0\n\tAND D1,D0\n\tMOVE D0," +this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) +"(SP)\n";
                break;
            case or:
                mensaje += "\tMOVE\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP), D1\n\tMOVE\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP), D0\n\tOR D1,D0\n\tMOVE D0," +this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) +"(SP)\n";
                break;
            case igual:
                if (this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())).getTipoSubjacenteBasico() == TipoSubjacenteBasico.tsEntero) {
                    mensaje += "\tMOVE.L\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+ "(SP), D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP), D1\n\tCMP.L D1, D0\n\tBEQ VERDADERO_CIE"+indice+"\n\tMOVE #0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+"(SP)\n\tBRA FIN_COMPARACION_CIE"+indice+"\nVERDADERO_CIE"+indice+":\n\tMOVE\t#1,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\nFIN_COMPARACION_CIE"+indice+":\n\t";
                } else if (this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())).getTipoSubjacenteBasico() == TipoSubjacenteBasico.tsBooleano) {
                    mensaje += "\tMOVE\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+ "(SP), D0\n\tMOVE\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP), D1\n\tCMP D1, D0\n\tBEQ VERDADERO_CIB\n\tMOVE #0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+"(SP)\n\tBRA FIN_COMPARACION_CIB"+indice+"\nVERDADERO_CIB"+indice+"\n\tMOVE\t#1,"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+ "(SP)\nFIN_COMPARACION_CIB"+indice+"\n\t";
                }
                break;
            case diferente:
                if (this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())).getTipoSubjacenteBasico() == TipoSubjacenteBasico.tsEntero) {
                    mensaje += "\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L "+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP), D1\n\tCMP.L D1, D0\n\tBNE VERDADERO_CDE"+indice+"\n\tMOVE\t#0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+"(SP)\n\tBRA\tFIN_COMPARACION_CDE"+indice+"\nVERDADERO_CDE"+indice+"\n\tMOVE\t#1,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+"(SP)\nFIN_COMPARACION_CDE"+indice+"\n";
                } else if (this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())).getTipoSubjacenteBasico() == TipoSubjacenteBasico.tsBooleano) {
                    mensaje += "\tMOVE\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE "+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tCMP.L D1, D0\n\tBNE VERDADERO_CDB"+indice+"\n\tMOVE\t#0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+"(SP)\n\tBRA\tFIN_COMPARACION_CDB"+indice+"\nVERDADERO_CDB"+indice+"\n\tMOVE\t#1,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+"(SP)\nFIN_COMPARACION_CDB"+indice+"\n";
                }
                break;
            case mayor:
                mensaje += "\tMOVE.L\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tCMP.L\tD1, D0\n\tBGT VERDADERO_CMA"+indice+"\n\tMOVE\t#0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n\tBRA\tFIN_COMPARACION_CMA"+indice+"\nVERDADERO_CMA"+indice+":\n\tMOVE\t#1," +this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+ "(SP)\nFIN_COMPARACION_CMA"+indice+"\n";
                break;
            case mayorIgual:
                mensaje += "\tMOVE.L\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tCMP.L\tD1, D0\n\tBGE VERDADERO_CMAI"+indice+"\n\tMOVE\t#0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n\tBRA\tFIN_COMPARACION_CMAI"+indice+"\nVERDADERO_CMAI"+indice+":\n\tMOVE\t#1," +this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+ "(SP)\nFIN_COMPARACION_CMAI"+indice+"\n";
                break;
            case menor:
                mensaje += "\tMOVE.L\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tCMP.L\tD1, D0\n\tBLT VERDADERO_CME"+indice+"\n\tMOVE\t#0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n\tBRA\tFIN_COMPARACION_CME"+indice+"\nVERDADERO_CME"+indice+":\n\tMOVE\t#1," +this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+ "(SP)\nFIN_COMPARACION_CME"+indice+"\n";
                break;
            case menorIgual:
                mensaje += "\tMOVE.L\t"+ this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tCMP.L\tD1, D0\n\tBLE VERDADERO_CMEI"+indice+"\n\tMOVE\t#0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n\tBRA\tFIN_COMPARACION_CMEI"+indice+"\nVERDADERO_CMEI"+indice+":\n\tMOVE\t#1," +this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor())))+ "(SP)\nFIN_COMPARACION_CMEI"+indice+"\n";
                break;
            case suma:
                mensaje += "\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tADD.L\tD1,D0\n\tMOVE.L\tD0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                break;
            case resta:
                mensaje += "\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tSUB.L\tD1,D0\n\tMOVE.L\tD0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                break;
            case producto:
                mensaje += "\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tMULS\tD1,D0\n\tMOVE.L\tD0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                break;
            case division:
                mensaje += "\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando1().getValor())))+"(SP),D0\n\tMOVE.L\t"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getOperando2().getValor())))+"(SP),D1\n\tDIVU\tD1,D0\n\tMOVE.L\tD0,"+this.desplazamientoVariable(this.TV.getVariable(Integer.parseInt(instruccion.getDestino().getValor()))) + "(SP)\n";
                break;
        }
        return mensaje;

    }

    private int desplazamientoVariable(DescripcionVariable dv) {
        // Es un argumento y es de tipo String o Booleano
        if ((dv.getTipoSubjacenteBasico() == TipoSubjacenteBasico.tsBooleano || dv.getTipoSubjacenteBasico() == TipoSubjacenteBasico.tsString ) && (dv.getDesplazamiento() > 0)) return dv.getDesplazamiento() + 2;
        else return dv.getDesplazamiento(); // Si es una variable local o argumento de tipo entero       
    }
    
        private int identificarParametroString(int parametro) {
        if (this.TV.getVariable(parametro).getDesplazamiento() > 0) {
            if (parametro == 0 || this.TV.getVariable(parametro).getIdSubprograma() != this.TV.getVariable(parametro - 1).getIdSubprograma()) {
                return 4;
            } else {
                if (this.TV.getVariable(parametro - 1).getTipoSubjacenteBasico() != TipoSubjacenteBasico.tsEntero)return this.TV.getVariable(parametro - 1).getDesplazamiento() + 4;
                else  return this.TV.getVariable(parametro - 1).getOcupacion() + this.TV.getVariable(parametro - 1).getDesplazamiento();               
            }
        }else{
            return 0; 
        }
    }

}
