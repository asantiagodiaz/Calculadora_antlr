# Calculadora Interactiva con ANTLR v4 

Este proyecto es una implementación de una calculadora y evaluador de expresiones matemáticas utilizando **ANTLR v4** y el patrón de diseño **Visitor** en Java. Se basa en el ejercicio del Capítulo 4.2 del libro *"The Definitive ANTLR 4 Reference"*.

## Características
- Evaluación de expresiones matemáticas básicas (`+`, `-`, `*`, `/`).
- Manejo de precedencia de operadores usando paréntesis `()`.
- Asignación y almacenamiento de variables en memoria (ej: `a = 5`).
- Uso de variables guardadas en nuevas expresiones.

## Archivos Principales
- `LabeledExpr.g4`: Gramática del lenguaje.
- `EvalVisitor.java`: Implementación del patrón Visitor que contiene la lógica matemática y la memoria de variables.
- `Calc.java`: Clase principal que enlaza el Lexer, Parser y Visitor.
- `entrada.txt`: Archivo de prueba con operaciones matemáticas.

---

##  Cómo ejecutar el proyecto

Para compilar y ejecutar este proyecto desde cero en una terminal (Linux/Mac), sigue estos pasos:

### 1. Descargar ANTLR
Descarga la herramienta en la raíz del proyecto:

`wget -O antlr-4.13.2-complete.jar https://www.antlr.org/download/antlr-4.13.2-complete.jar`

2. Generar el Lexer y el Parser
Usa la gramática .g4 para generar el código base en Java habilitando el patrón Visitor:
`java -jar antlr-4.13.2-complete.jar -no-listener -visitor LabeledExpr.g4`

3. Compilar el código Java
Compila todos los archivos conectando el .jar de ANTLR al classpath:

`javac -cp ".:antlr-4.13.2-complete.jar" *.java`

4. Ejecutar el programa
Pásale el archivo de texto a la clase principal para ver los resultados en consola:

`java -cp ".:antlr-4.13.2-complete.jar" Calc entrada.txt`


