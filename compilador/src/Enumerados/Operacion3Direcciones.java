/*
    Recopilación de las posibles operaciones que puede realizar nuestro código 
    de 3 direcciones
 */
package Enumerados;

public enum Operacion3Direcciones {
          
    //--------------- OPERACIONES ARITMÉTICAS Y LÓGICAS ----------------------//
    
    copia,                // a = b 
    
    suma,                 //  a = b + c 
    resta,                //  a = b - c 
    producto,             //  a = b * c 
    division,             //  a = b / c 
    modulo,               //  a = b - c 
    menos,                //  a = -b
    
    and,                  //  a = b AND c
    or,                   //  a = b OR c
    not,                  //  a = NOT b
    
    //------------------- OPERACIONES DE BIFUCARCIÓN -------------------------//
    
    etiquetaPasar,        // e: skip
    saltoCondicional,     // if oprel goto e
    saltoIncondicional,     // goto e
    
    menor,                // a < b 
    menorIgual,           // a ≤ b
    igual,                // a = b
    diferente,            // a ≠ b
    mayor,                // a > b
    mayorIgual,           // a ≥ b
    
    esTrue,               // a = true 
    esFalse,              // a = false 
    
    //-------------------- LLAMADAS A PROCEDIMIENTOS -------------------------//
    
    inicializacion,       // pmb np          
    llamada,              // call np         
    retorno,              // rtn np          
    parametroSimple,      // param_s a       
    idSubprograma,        // np              
    finSubprograma,       // end np          
    llamadaMain,          // call main       
    
    //-------------------- LLAMADAS A PROCEDIMIENTOS -------------------------//
    
    entradaDatos,         // in         
    salidaDatos           // out       
            
        
}
