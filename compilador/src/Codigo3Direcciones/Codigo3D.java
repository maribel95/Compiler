/*
    Clase que define la estructura del código de 3 direcciones
 */
package Codigo3Direcciones;

import Enumerados.Operacion3Direcciones;

/*
    Un dels possibles modes de representació del codi intermedi que es pot utilitzar,
    i que de fet és un dels mecanismes més comunament utilitzats per fer-ho, és el 
    conegut com a codi de tres adreces (c3@).
*/
public class Codigo3D {
/*
    Cada instrucció està formada per un bloc de 4 enters, dels quals un s’utilitza 
    per especificar de quina instrucció es tracta (una enumeració) i els altres tres 
    són utilitzats per indicar els operands necessaris. Es pot establir un criteri 
    d’ordenació: 
    1. Indica operació
    2. Possible primer operand
    3. Possible segon operand
    4. Destinació on dipositar el valor resultat
*/   
    private Operacion3Direcciones codigo;
    
//************************ GETTERS Y SETTERS ***********************************
    
    public Operacion3Direcciones getCodigo() {
        return codigo;
    }

    public void setCodigo(Operacion3Direcciones codigo) {
        this.codigo = codigo;
    }

    public Operador getOperando1() {
        return operando1;
    }

    public void setOperando1(Operador operando1) {
        this.operando1 = operando1;
    }

    public Operador getOperando2() {
        return operando2;
    }

    public void setOperando2(Operador operando2) {
        this.operando2 = operando2;
    }

    public Operador getDestino() {
        return destino;
    }

    public void setDestino(Operador destino) {
        this.destino = destino;
    }
    //**************************************************************************
    
    private Operador operando1;
    private Operador operando2;
    private Operador destino;
    
    public Codigo3D(Operacion3Direcciones codigo, Operador operando1, Operador operando2, Operador destino){
        this.codigo = codigo;
        this.operando1 = operando1;
        this.operando2 = operando2;
        this.destino = destino;
    }

}
