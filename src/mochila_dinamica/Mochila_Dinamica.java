/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mochila_dinamica;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 *
 * @author Fco Manuel Guerrero Jiménez
 */
public class Mochila_Dinamica {

  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */   
    Vector<Elemento> almacen = new Vector<Elemento>();
    Vector<Elemento> mochila = new Vector<Elemento>();
    
    static int beneficios[]= new int[13];   //vector de beneficios
    static int pesos[]=new int[13];         //vector de pesos
    int solucion[]=new int[13];
    final double  pesoMaximo;               //peso Maximo de la mochila
 
    //constructor
    public Mochila_Dinamica (int pm) {
        pesoMaximo = pm;
        cargarDatos();      
    }
//****************************************************************************** 
    public void cargarDatos() {
        // Elementos            Objeto          Valor  Peso
        almacen.add(new Elemento("Televisión",  300, 15,0));
        almacen.add(new Elemento("Radio CD",    102,3,1));
        almacen.add(new Elemento("Libro",       10,1,2));
        almacen.add(new Elemento("Lampara",     5,  1,3));
        almacen.add(new Elemento("DVD",         50,  1,4));
        almacen.add(new Elemento("Disco HDD",   30,  1,5));
        almacen.add(new Elemento("Ventilador",  150,  1,6));
        almacen.add(new Elemento("Impresora 3D",150,  4,7));
        almacen.add(new Elemento("Silla",       200,  4,8));
        almacen.add(new Elemento("Portatil",    21,  3,9));
        almacen.add(new Elemento("impresora",   20,  2,10));
        almacen.add(new Elemento("PC",          100,  5,11));
        almacen.add(new Elemento("Tlf movil",   150,  1,12));
        
        beneficios[0]=300;
        beneficios[1]=102;
        beneficios[2]=10;
        beneficios[3]=5;
        beneficios[4]=50;
        beneficios[5]=30;
        beneficios[6]=150;
        beneficios[7]=150;
        beneficios[8]=200;
        beneficios[9]=21;
        beneficios[10]=20;
        beneficios[11]=100;
        beneficios[12]=150;
        
        pesos[0]=15;
        pesos[1]=3;
        pesos[2]=1;
        pesos[3]=1;
        pesos[4]=1;
        pesos[5]=1;
        pesos[6]=1;
        pesos[7]=4;
        pesos[8]=4;
        pesos[9]=3;
        pesos[10]=2;
        pesos[11]=5;
        pesos[12]=1;
        
         for(int i=0; i<solucion.length; i++){
		solucion[i]=0;
	}
   }
//******************************************************************************
    public void mostrarMochila() {
        double pesoMochila=0;
        double valorMochila=0;
        System.out.println("\n\n******* Objetos en la Mochila *******");
        for(Elemento e: mochila) {
            System.out.println(e);
            pesoMochila+=e.getPeso();
            valorMochila+=e.getValor();
        }
        System.out.println("--------------------");
        System.out.printf("Peso  = %,12.2f %n", pesoMochila);
        System.out.printf("Valor = %,12.2f %n", valorMochila);       
    }
//****************************************************************************** 
//solución calculando el costo por unidad de peso
public void resolverProblema() {
    // Comparador para ordenar los elementos del almacen por valor
   Comparator cmp = new Comparator<Elemento>() {
        
        public int compare(Elemento x, Elemento y) {
            return (int) (x.getValor()/x.getPeso() - y.getValor()/y.getPeso());            
        }
    };
    Collections.sort(almacen,cmp);  // ordena usando el comparador anterior
    Collections.reverse(almacen);   // reversa el orden de los elementos
    
    double pesoMochila=0;
    int    posicion=0;
     while(pesoMochila<pesoMaximo && posicion < almacen.size()) {
        Elemento tmp = almacen.get(posicion);
        if(pesoMochila + tmp.getPeso() <= pesoMaximo) {
            mochila.add(tmp);
            solucion[tmp.getPosicion()]= 1;
            pesoMochila+=tmp.getPeso();
            
        }
        posicion++;        
    }     
}
//******************************************************************************
public void mostrarMatriz (int matriz[][]){
    System.out.println("********************************Tabla de valores**********************************");
      int i,j; 
      int filas = matriz.length;  //14
      int columnas = matriz[0].length; //21
      // Recorrido de las filas de la matriz
    for (i=0; i<filas; i++) {  
        // Recorrido de las celdas de una fila    
        for (j=0; j<columnas; j++) {    
              System.out.print(matriz[i][j]+ ",");               
              if(matriz[i][j]==0)
                System.out.print("  ");
        }
        System.out.println("\n");
    }
}
//******************************************************************************
public void mostrarVector(int[] v){
    System.out.println("********* Vector Solucion ***********");
    for(int i=0; i<v.length; i++){        
        System.out.print(v[i]+ ",");
    }
}
//******************************************************************************
 public int[][] Mochila(int[] pesos, int[] beneficios, int capacidad){
   
    //Creamos la matriz de devoluciones
    int[][]  matriz_mochila = new int[pesos.length+1][capacidad+1];
   //Rellenamos la 1ª fila de ceros
   for(int i = 0; i <= capacidad; i++)
            matriz_mochila[0][i] = 0; 
   //Rellenamos la 1ª columna de ceros
   for(int i = 0; i <= pesos.length; i++)  
           matriz_mochila[i][0] = 0; 
   
   for(int j = 1; j <= pesos.length ; j++)  
        for(int c = 1; c <= capacidad; c++){  
            if(c <  pesos[j-1] ){   
                matriz_mochila[j][c] = matriz_mochila[j-1][c]; //entonces el objeto "j" no se selecciona                
            }else{                          
                if(matriz_mochila[j-1][c] > matriz_mochila[j-1][c-pesos[j-1]]+ beneficios[j-1]){
                    matriz_mochila[j][c] = matriz_mochila[j-1][c];                    
                }else{
                matriz_mochila[j][c] = matriz_mochila[j-1][c-pesos[j-1]]+beneficios[j-1];
                
                }
            }
        }
       return matriz_mochila;
     }
    //**************************************************************************
    public static void main(String[] args) {
        
        // Crear una mochila que soporta hasta 20 Kg. de peso
        Mochila_Dinamica pm = new Mochila_Dinamica(20);
        int[][] matri = new int[14][21];
      
        matri = (pm.Mochila(pesos,beneficios,20)); 
        pm.mostrarMatriz(matri);
        pm.resolverProblema();
        pm.mostrarVector(pm.solucion);
        pm.mostrarMochila();
    }
}
   
   
   
    

   
