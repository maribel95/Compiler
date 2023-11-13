package Lexico;

import java_cup.runtime.*;
import Sintactico.ParserSym;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Ficheros.GestorFicheros;
%%

%public
%class AnalizadorLexico
%cup
%line
%column
%{

    public class Token {     

        private String lexema;
        private int linea;
        private int columna;
        private int numero;

        public Token(String lexema,  int linea, int columna, int numero){
            this.lexema = lexema;
            this.linea = linea;
            this.columna = columna;
            this.numero = numero;
        }

        //Metodo que devuelve los datos necesarios que escribiremos en un archive de salida
        public String toString() {
            return "Token #"+numero+": lexema identificado: '"+lexema+"'  Línea: "+linea
            + ", Columna: " +columna;
        }
    }   
    private int contador;
    private ArrayList<Token> tokens;
    private GestorFicheros gf;
    public void escribirTokensFichero() throws IOException {
      BufferedWriter bw = new BufferedWriter(new FileWriter("src/Salidas/Tokens/Tokens.txt"));
      bw.write("---------------------------- TOKENS -------------------------------\n\r");

        for(Token t: this.tokens){                
            bw.write(t.toString() + "\n");
        }
      bw.write("-------------------------------------------------------------------");
      bw.close();
    }

    private Symbol symbol(int tipo, Object valor) {
        return new Symbol(tipo, yyline, yycolumn, valor);
    }

    private Symbol symbol(int tipo) {
        return new Symbol(tipo, yyline, yycolumn);
    }
    
%}
%init{
        contador = 0;
        tokens = new ArrayList<>();
        gf = new GestorFicheros();
%init}
%eof{
    try{
        this.escribirTokensFichero();
    }catch(IOException ex){ ex.printStackTrace();}
%eof}

%eofval{
  return symbol(ParserSym.EOF);
%eofval}

%%
<YYINITIAL> {

(\r | \n | \t | " ")           {/* No hacer nada */}
("***".*"***")                 {/* No hacer nada */}
"CONST"        {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.constante);
                 }
"="             {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.asignacion);
                }
"if"            {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_if);
               }
"else"       {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_else);
                  }
"else if"       {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_elseif);
                  }
"while"      {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_while);
                  }   
          
"func"   {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_func);
                  }
"return"     {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_return);
                  }
"stdin"         {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_stdin);
                }
"stdout"        {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_stdout);
                }
( "+" | "-" | "*" | "/" )   {
                                    contador++;
                                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                                    tokens.add(token);
                                    return symbol(ParserSym.operador_aritmetico, yytext());
                                }
("==" | "!=" | ">" | "<" | ">=" | "<=")   {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.operador_relacional, yytext());
                  }
("&" | "|")        {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.operador_logico, yytext());
                  }

"("          {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.parentesis_izq);
                  }
"{"        {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.llave_izq);
                  }
")"          {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.parentesis_der);
                  }

"}"        {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.llave_der);
                  }
";"  {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.fin_instruccion);
                  }
","       {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.coma);
                  }


( 0 | [1-9][0-9]*)          {
                    contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.entero, yytext());
                  }

(\" [^\"]* \")            {
                            contador++;

                            Token token = new Token(yytext(), yyline, yycolumn, contador);
                            tokens.add(token);

                            return symbol(ParserSym.cadena, yytext());
                          }

("true" | "false")      {
                            contador++;
                            Token token = new Token(yytext(), yyline, yycolumn, contador);
                            tokens.add(token);

                            return symbol(ParserSym.booleano, yytext());
                        }

([a-zA-Z_])([a-zA-Z_]|[0-9] )* {
                                    contador++;
                                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                                    tokens.add(token);

                                    return symbol(ParserSym.identificador, yytext());
                                 }
}
[^]               { 
                    try{                   
                    String mensajeError = "Excepción en el léxico, de tipo no existe, Token:  ERROR , Lexema : [" + yytext() +"]\n Linea: " + yyline +", Columna:" + yycolumn;
                    gf.escribirError("src/Salidas/Errores/ErroresLexicos.txt", mensajeError);
                    throw new Exception(mensajeError);
                    } catch (Exception ex) {
                        System.err.println("Error: " + ex.getMessage());
                    }
                  }
