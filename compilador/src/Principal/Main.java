package Principal;

import Ficheros.GestorFicheros;
import Lexico.AnalizadorLexico;
import Sintactico.Parser;
import java.io.File;
import java.io.FileReader;
import java_cup.runtime.ComplexSymbolFactory;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    /*
        En el main simplemente se ejecuta un menú, donde según la opción lo que hará es:
        Opción 1: Generar fichero JFLEX y CUP
        Opción 2: Ejecutar analizador del compilador.
        Opción 3: Fin del programa.
     */
    static GestorFicheros gf = new GestorFicheros();

    public static void main(String[] args) {
        String opcion;
        System.out.println("\t\t\t\t--------------------------------------------------------------");
        System.out.println("\t\t\t\t----------------- PRÁCTICA COMPILADORES ----------------------");
        System.out.println("\t\t\t\t--------------------------------------------------------------\n");
        Scanner lector = new Scanner(System.in);
        do {
            mostrarMenu();
            opcion = lector.nextLine();
            switch (opcion) {
                case "1":generarJflexYCup();
                    break;
                case "2":
                    compilar();
                    break;
                case "3":
                    System.out.println("Hasta la vista ;)");
                    break;
                default:
                    System.out.println("Opción incorrecta. Prueba otra cosa!!");
            }
        } while (!"1".equals(opcion) && !"3".equals(opcion));
    }

    /*
        Aquí se imprimen las opciones del menú
     */
    public static void mostrarMenu() {
        System.out.println("1) Generar fichero JFLEX y CUP.");
        System.out.println("2) Compilar programa.");
        System.out.println("3) Finalizar.");
        System.out.print("Introduce aquí tu opción: ");
    }

    public static void generarJflexYCup() {
        try {
            // PRIMERO GENERAMOS EL ARCHIVO JFLEX
            File archivoFlex = new File("src/Lexico/Lexer.flex");
            JFlex.Main.generate(archivoFlex);
            //Files.move(Paths.get("src/Principal/ScannerLex.java"), Paths.get("src/Lexico/ScannerLex.java"), StandardCopyOption.REPLACE_EXISTING);
            gf.moverFichero("src/Principal/ScannerLex.java", "src/Lexico/ScannerLex.java");
            // SEGUNDO GENERAMOS ARCHIVO CUP
            String[] rutaSintactico = {"-parser", "Sintactico", "src/Sintactico/Sintactico.cup"};
            java_cup.Main.main(rutaSintactico);
            gf.moverFichero("src/Sintactico/ParserSym.java", "ParserSym.java");
            gf.moverFichero("src/Sintactico/Parser.java", "Parser.java");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
        Primero eliminamos todos los ficheros de la carpeta de salida.
        Segundo escogemos qué fichero es el que vamos a analizar.
    
        Generamos un symbolFactory, y hacemos el scanner del fichero. Hacemos
        un parser del scanner y el SymbolFactory
        Guardamos el symbolo de la raiz y si este es diferente de NULL y ha acabado,
        significa que todo ha salido bien.
        
     */
    public static void compilar() {
        try {
            gf.borrarFicherosSalidas();
            if ((boolean) new Parser(new AnalizadorLexico(new FileReader(menuFicheros())), new ComplexSymbolFactory()).parse().value) {
                System.out.println("\n\n\t\tEL CÓDIGO FUENTE ES CORRECTO!\n\n");
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public static String menuFicheros() {
        String ficherosPrueba[] = {"programa0.txt", "programa1.txt", "programa2.txt", "fallo0.txt", "fallo1.txt", "fallo2.txt"};
        boolean esIncorrecto;
        Scanner lectorFicheros = new Scanner(System.in);
        int opcion;
        do {
            for (int i = 0; i < ficherosPrueba.length; i++) {
                System.out.println(i + ") " + ficherosPrueba[i]);
            }
            System.out.println("Escoge uno para compilar:");
            opcion = lectorFicheros.nextInt();
            if (opcion < 0 || opcion >= ficherosPrueba.length) {
                System.out.println("\nOpción incorrecta.");
                esIncorrecto = true;
            } else {
                esIncorrecto = false;
            }
        } while (esIncorrecto);
        return "src/Entradas/"+ficherosPrueba[opcion];
    }
    
}
