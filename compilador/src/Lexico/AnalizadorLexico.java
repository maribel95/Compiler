/* The following code was generated by JFlex 1.4.3 on 16/02/22 9:47 */

package Lexico;

import java_cup.runtime.*;
import Sintactico.ParserSym;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Ficheros.GestorFicheros;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 16/02/22 9:47 from the specification file
 * <tt>src/Lexico/Lexer.flex</tt>
 */
public class AnalizadorLexico implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\1\1\2\2\0\1\1\22\0\1\3\1\32\1\45\3\0"+
    "\1\34\1\0\1\35\1\37\1\4\1\31\1\42\1\31\1\0\1\31"+
    "\1\43\11\44\1\0\1\41\1\33\1\12\1\33\2\0\2\47\1\5"+
    "\12\47\1\7\1\6\3\47\1\10\1\11\6\47\4\0\1\47\1\0"+
    "\1\46\1\47\1\24\1\27\1\15\1\14\1\47\1\21\1\13\2\47"+
    "\1\16\1\47\1\23\1\30\2\47\1\25\1\17\1\26\1\22\1\47"+
    "\1\20\3\47\1\36\1\34\1\40\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\2\4\1\5\7\4\1\3"+
    "\1\1\1\6\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\2\16\1\1\1\0\1\4\1\6\1\17\7\4"+
    "\1\0\1\20\1\0\10\4\1\0\1\4\1\21\1\22"+
    "\4\4\1\23\1\0\1\24\1\0\1\25\1\4\1\26"+
    "\1\4\1\2\1\0\1\27\1\30\1\31";

  private static int [] zzUnpackAction() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\50\0\120\0\170\0\240\0\310\0\360"+
    "\0\u0118\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0\0\50\0\310"+
    "\0\310\0\50\0\50\0\50\0\50\0\50\0\50\0\50"+
    "\0\50\0\u0208\0\u0230\0\u0258\0\u0280\0\50\0\240\0\u02a8"+
    "\0\u02d0\0\u02f8\0\u0320\0\u0348\0\u0370\0\u0398\0\u0230\0\50"+
    "\0\u03c0\0\u03e8\0\u0410\0\u0438\0\u0460\0\u0488\0\u04b0\0\u04d8"+
    "\0\u0500\0\u0528\0\u0550\0\240\0\u0578\0\u05a0\0\u05c8\0\u05f0"+
    "\0\u0618\0\240\0\u0640\0\240\0\u0668\0\240\0\u0690\0\240"+
    "\0\u06b8\0\u0640\0\u06e0\0\240\0\240\0\50";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\3\3\1\4\1\5\4\6\1\7\1\10\1\11"+
    "\1\12\1\6\1\13\1\14\4\6\1\15\1\16\2\6"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\1\27\1\30\1\31\1\32\1\33\2\6\54\0\1\34"+
    "\50\0\1\6\1\35\3\6\1\0\16\6\12\0\2\6"+
    "\1\0\2\6\5\0\5\6\1\0\16\6\12\0\2\6"+
    "\1\0\2\6\12\0\1\36\42\0\5\6\1\0\1\6"+
    "\1\37\14\6\12\0\2\6\1\0\2\6\5\0\5\6"+
    "\1\0\7\6\1\40\6\6\12\0\2\6\1\0\1\41"+
    "\1\6\5\0\5\6\1\0\3\6\1\42\12\6\12\0"+
    "\2\6\1\0\2\6\5\0\5\6\1\0\13\6\1\43"+
    "\2\6\12\0\2\6\1\0\2\6\5\0\5\6\1\0"+
    "\6\6\1\44\7\6\12\0\2\6\1\0\2\6\5\0"+
    "\5\6\1\0\2\6\1\45\13\6\12\0\2\6\1\0"+
    "\2\6\5\0\5\6\1\0\12\6\1\46\3\6\12\0"+
    "\2\6\1\0\2\6\43\0\2\32\3\0\45\47\1\50"+
    "\2\47\4\0\1\51\50\0\2\6\1\52\2\6\1\0"+
    "\16\6\12\0\2\6\1\0\2\6\5\0\5\6\1\0"+
    "\10\6\1\53\5\6\12\0\2\6\1\0\2\6\5\0"+
    "\5\6\1\0\3\6\1\54\12\6\12\0\2\6\1\0"+
    "\2\6\5\0\5\6\1\0\4\6\1\55\11\6\12\0"+
    "\2\6\1\0\2\6\5\0\5\6\1\0\14\6\1\56"+
    "\1\6\12\0\2\6\1\0\2\6\5\0\5\6\1\0"+
    "\1\57\15\6\12\0\2\6\1\0\2\6\5\0\5\6"+
    "\1\0\13\6\1\60\2\6\12\0\2\6\1\0\2\6"+
    "\5\0\5\6\1\0\7\6\1\61\6\6\12\0\2\6"+
    "\1\0\2\6\2\51\1\0\1\51\1\62\43\51\5\0"+
    "\3\6\1\63\1\6\1\0\16\6\12\0\2\6\1\0"+
    "\2\6\5\0\5\6\1\0\11\6\1\64\4\6\12\0"+
    "\2\6\1\0\2\6\5\0\5\6\1\0\4\6\1\61"+
    "\11\6\12\0\2\6\1\0\2\6\5\0\5\6\1\0"+
    "\2\6\1\65\13\6\12\0\2\6\1\0\2\6\5\0"+
    "\5\6\1\0\1\66\14\6\1\67\12\0\2\6\1\0"+
    "\2\6\5\0\5\6\1\0\3\6\1\70\12\6\12\0"+
    "\2\6\1\0\2\6\5\0\5\6\1\0\7\6\1\71"+
    "\6\6\12\0\2\6\1\0\2\6\5\0\5\6\1\0"+
    "\2\6\1\72\13\6\12\0\2\6\1\0\2\6\2\51"+
    "\1\0\1\51\1\73\43\51\5\0\4\6\1\74\1\0"+
    "\16\6\12\0\2\6\1\0\2\6\3\0\1\75\1\0"+
    "\5\6\1\0\16\6\12\0\2\6\1\0\2\6\5\0"+
    "\5\6\1\0\10\6\1\76\5\6\12\0\2\6\1\0"+
    "\2\6\5\0\5\6\1\0\7\6\1\77\6\6\12\0"+
    "\2\6\1\0\2\6\5\0\5\6\1\0\2\6\1\100"+
    "\13\6\12\0\2\6\1\0\2\6\5\0\5\6\1\0"+
    "\12\6\1\101\3\6\12\0\2\6\1\0\2\6\2\51"+
    "\1\0\1\51\1\102\43\51\13\0\1\103\41\0\5\6"+
    "\1\0\13\6\1\104\2\6\12\0\2\6\1\0\2\6"+
    "\5\0\5\6\1\0\10\6\1\105\5\6\12\0\2\6"+
    "\1\0\2\6\14\0\1\106\33\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1800];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\2\11\13\1\1\11\2\1\10\11\2\1\1\0"+
    "\1\1\1\11\10\1\1\0\1\11\1\0\10\1\1\0"+
    "\10\1\1\0\1\1\1\0\5\1\1\0\2\1\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[70];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */

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
    


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public AnalizadorLexico(java.io.Reader in) {
          contador = 0;
        tokens = new ArrayList<>();
        gf = new GestorFicheros();
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public AnalizadorLexico(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 132) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
        try{
        this.escribirTokensFichero();
    }catch(IOException ex){ ex.printStackTrace();}
  yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 24: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_return);
          }
        case 26: break;
        case 5: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.asignacion);
          }
        case 27: break;
        case 14: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.entero, yytext());
          }
        case 28: break;
        case 2: 
          { /* No hacer nada */
          }
        case 29: break;
        case 7: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.operador_logico, yytext());
          }
        case 30: break;
        case 12: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.fin_instruccion);
          }
        case 31: break;
        case 17: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_func);
          }
        case 32: break;
        case 20: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.constante);
          }
        case 33: break;
        case 25: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_elseif);
          }
        case 34: break;
        case 22: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_while);
          }
        case 35: break;
        case 18: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_else);
          }
        case 36: break;
        case 8: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.parentesis_izq);
          }
        case 37: break;
        case 21: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_stdin);
          }
        case 38: break;
        case 9: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.llave_izq);
          }
        case 39: break;
        case 23: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_stdout);
          }
        case 40: break;
        case 16: 
          { contador++;

                            Token token = new Token(yytext(), yyline, yycolumn, contador);
                            tokens.add(token);

                            return symbol(ParserSym.cadena, yytext());
          }
        case 41: break;
        case 13: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.coma);
          }
        case 42: break;
        case 1: 
          { try{                   
                    String mensajeError = "Excepción en el léxico, de tipo no existe, Token:  ERROR , Lexema : [" + yytext() +"]\n Linea: " + yyline +", Columna:" + yycolumn;
                    gf.escribirError("src/Salidas/Errores/ErroresLexicos.txt", mensajeError);
                    throw new Exception(mensajeError);
                    } catch (Exception ex) {
                        System.err.println("Error: " + ex.getMessage());
                    }
          }
        case 43: break;
        case 6: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.operador_relacional, yytext());
          }
        case 44: break;
        case 15: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.i_if);
          }
        case 45: break;
        case 10: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.parentesis_der);
          }
        case 46: break;
        case 19: 
          { contador++;
                            Token token = new Token(yytext(), yyline, yycolumn, contador);
                            tokens.add(token);

                            return symbol(ParserSym.booleano, yytext());
          }
        case 47: break;
        case 11: 
          { contador++;
                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                    tokens.add(token);

                    return symbol(ParserSym.llave_der);
          }
        case 48: break;
        case 3: 
          { contador++;
                                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                                    tokens.add(token);
                                    return symbol(ParserSym.operador_aritmetico, yytext());
          }
        case 49: break;
        case 4: 
          { contador++;
                                    Token token = new Token(yytext(), yyline, yycolumn, contador);
                                    tokens.add(token);

                                    return symbol(ParserSym.identificador, yytext());
          }
        case 50: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              {   return symbol(ParserSym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
