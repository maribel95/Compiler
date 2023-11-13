package Simbolos;

import java_cup.runtime.Symbol;
// Símbolo que servirá de base para todos los demás
public class Base extends Symbol {
    private static int idAutoIncrement = 0;
    
    public Base(String variable, Integer valor) {
        super(valor, variable);
    }
 }
