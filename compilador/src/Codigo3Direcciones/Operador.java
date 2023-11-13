
package Codigo3Direcciones;

import Enumerados.CategoriaOperador;

public class Operador { 
    private String valor;
    private CategoriaOperador categoriaOperador;

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public CategoriaOperador getCategoriaOperador() {
        return categoriaOperador;
    }

    public void setCategoriaOperador(CategoriaOperador categoriaOperador) {
        this.categoriaOperador = categoriaOperador;
    }
    
    public Operador(String valor, CategoriaOperador categoriaOperador) {
        this.valor = valor;
        this.categoriaOperador = categoriaOperador; 
    }  
}
