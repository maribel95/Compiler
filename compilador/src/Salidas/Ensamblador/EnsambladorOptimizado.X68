*-----------------------------------------------------------
* Title      : Practica
* Written by : Odilo Fortes, Iker Diaz, Maribel Crespi 
* Date       : 28/1/2022
* Description: Codigo ensamblador.
* -------------------------------------------------------- *
	ORG    $1000
START:				; first instruction of program
*  Put program code here

	JSR	PRINCIPAL
	SIMHALT			; halt simulator
FACTORIALRECURSIVO:
	SUB.L	#4, SP
	SUB.L	#4, SP	;	BP
	MOVE.L	#0,(SP)
	MOVE.L	SP, A3
	SUB.L	#42, A3	;	Actualizar SP para nuevo Bloque de activación
	MOVE.L	#0,-8(SP) 
	MOVE.L	#0,-12(SP) 
	MOVE.L	4(SP),D0
	MOVE.L -12(SP), D1
	CMP.L D1, D0
	BNE VERDADERO_CDE5
	MOVE	#0,-14(SP)
	BRA	FIN_COMPARACION_CDE5
VERDADERO_CDE5
	MOVE	#1,-14(SP)
FIN_COMPARACION_CDE5
	MOVE	-14(SP), D0
	CMP	#0, D0
	BNE	ETIQUETA1
	MOVE	-14(SP), D0
	CMP	#1, D0
	BNE	ETIQUETA2
ETIQUETA1:
	MOVE.L	#1,-18(SP) 
	MOVE.L	4(SP),D0
	MOVE.L	-18(SP),D1
	SUB.L	D1,D0
	MOVE.L	D0,-22(SP)
	MOVE.L	-22(SP),-26(SP)
	MOVE.L	SP, A5
	MOVE.L	A3, SP
	MOVE.L	A5,-(SP) 	;	BP anterior
	SUB.L	#4, SP	;	Espacio de memoria para el retorno del BP
	SUB.L	#4, SP	;	Espacio memoria para el PC
	CLR.L	D0 
	MOVE.L	SP, A4 
	MOVE.L	A5, SP 
	MOVE.L	-26(SP), D0
	MOVE.L	A4, SP 
	MOVE.L	D0, -(SP) 
	ADD.L	#8, SP
	JSR FACTORIALRECURSIVO
	ADD.L	#4, SP	;	Retorno del salto
	MOVE.L	(SP)+, A5	;	Obtener BP
	MOVE.L	SP, A3	;	A3 contiene el SP
	MOVE.L	A5, SP	;	Actualizar BP
	MOVE.L	SP, A5
	MOVE.L	A3, SP
	SUB.L	#8, SP
	MOVE.L	(SP), D0
	MOVE.L	A5, SP
	MOVE.L	D0,-30(SP)
	MOVE.L	-30(SP),-34(SP)
	MOVE.L	4(SP),D0
	MOVE.L	-34(SP),D1
	MULS	D1,D0
	MOVE.L	D0,-38(SP)
	MOVE.L	-38(SP),-8(SP)
	JMP	ETIQUETA3
ETIQUETA2:
	MOVE.L	#1,-8(SP) 
ETIQUETA3:
	MOVE.L	-8(SP), D0
	MOVE.L	D0, 12(SP)
	ADD.L	#8, SP
	RTS
PRINCIPAL:
	SUB.L	#0, SP
	SUB.L	#4, SP	;	BP
	MOVE.L	#0,(SP)
	MOVE.L	SP, A3
	SUB.L	#200, A3	;	Actualizar SP para nuevo Bloque de activación
	MOVE.L	SP, A1
	ADD.L	#-138,A1
	MOVE.L	#STR_0,A2
	MOVE.L	 #138,D0
	CMP.L	#0, D0
	BEQ	COPIADO26
	CLR.L D1
PONER_CARACTER_ADS26:
	MOVE.B	(A2)+, D1
	MOVE	D1,(A1)+
	SUB.L #2, D0
	CMP.L #0, D0
	BNE	PONER_CARACTER_ADS26
COPIADO26:
	MOVE.L	#BUFF, A1
	MOVE.L	SP, A2
	ADD.L	#-138,A2
	MOVE.L	#69,D1
	CMP.L	#0, D1
	BEQ	FIN_SALIDA27
SIGUIENTE_CARACTER_SS27:
	MOVE	(A2)+, D0
	CMP	#8224, D0
	BEQ FIN_SALIDA27
	MOVE.B	D0,(A1)+
	SUB.L	#1, D1
	CMP.L	#0, D1
	BNE	SIGUIENTE_CARACTER_SS27
FIN_SALIDA27:
	MOVE	#1, D0
	MOVE.L	#69,D1
	MOVE.L	#BUFF, A1
	TRAP	#15
	CLR	D0
	MOVE.L	#BUFF ,A1
	MOVE.L	#0, D1
	TRAP #15
	MOVE.L	#0,-146(SP) 
	CLR.L	D1
	MOVE.L	#4, D0
	TRAP	#15
	MOVE.L	D1,-150(SP)
	MOVE.L	-150(SP),-146(SP)
	MOVE.L	SP, A5
	MOVE.L	A3, SP
	MOVE.L	A5,-(SP) 	;	BP anterior
	SUB.L	#4, SP	;	Espacio de memoria para el retorno del BP
	SUB.L	#4, SP	;	Espacio memoria para el PC
	CLR.L	D0 
	MOVE.L	SP, A4 
	MOVE.L	A5, SP 
	MOVE.L	-146(SP), D0
	MOVE.L	A4, SP 
	MOVE.L	D0, -(SP) 
	ADD.L	#8, SP
	JSR FACTORIALRECURSIVO
	ADD.L	#4, SP	;	Retorno del salto
	MOVE.L	(SP)+, A5	;	Obtener BP
	MOVE.L	SP, A3	;	A3 contiene el SP
	MOVE.L	A5, SP	;	Actualizar BP
	MOVE.L	SP, A5
	MOVE.L	A3, SP
	SUB.L	#8, SP
	MOVE.L	(SP), D0
	MOVE.L	A5, SP
	MOVE.L	D0,-154(SP)
	MOVE.L	-154(SP),-158(SP)
	MOVE.L	SP, A1
	ADD.L	#-200,A1
	MOVE.L	#STR_1,A2
	MOVE.L	 #42,D0
	CMP.L	#0, D0
	BEQ	COPIADO35
	CLR.L D1
PONER_CARACTER_ADS35:
	MOVE.B	(A2)+, D1
	MOVE	D1,(A1)+
	SUB.L #2, D0
	CMP.L #0, D0
	BNE	PONER_CARACTER_ADS35
COPIADO35:
	MOVE.L	#BUFF, A1
	MOVE.L	SP, A2
	ADD.L	#-200,A2
	MOVE.L	#21,D1
	CMP.L	#0, D1
	BEQ	FIN_SALIDA36
SIGUIENTE_CARACTER_SS36:
	MOVE	(A2)+, D0
	CMP	#8224, D0
	BEQ FIN_SALIDA36
	MOVE.B	D0,(A1)+
	SUB.L	#1, D1
	CMP.L	#0, D1
	BNE	SIGUIENTE_CARACTER_SS36
FIN_SALIDA36:
	MOVE	#1, D0
	MOVE.L	#21,D1
	MOVE.L	#BUFF, A1
	TRAP	#15
	CLR	D0
	MOVE.L	#BUFF ,A1
	MOVE.L	#0, D1
	TRAP #15
	CLR.L	D0
	MOVE.L	-158(SP), D1
	MOVE #20, D0
	TRAP #15
	CLR	D0
	MOVE.L	#BUFF ,A1
	MOVE.L	#0, D1
	TRAP #15
	ADD.L	#4, SP
	RTS

* Put variables and constants here

STR_0 DC.B 'Programa que calcula el factorial del numero introducido por teclado:', 0
STR_1 DC.B 'Resultado factorial: ', 0
BUFF DS.B 1024

	END	START		; last line of source
