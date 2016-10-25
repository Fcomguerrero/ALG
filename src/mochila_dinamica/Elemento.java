/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mochila_dinamica;
/**
 *
 * @author Fco Manuel Guerrero Jiménez 
 * @date 20-jun-2016
 * @time 8:55:56
 */
public class Elemento {
        
        String nomObjeto;
        private double peso;
        private double valor;
        private int posicion;

        //constructor
        public Elemento(String nom, double valor, double peso, int posicion ) {
            this.nomObjeto = nom;
            this.valor = valor;
            this.peso = peso;
            this.posicion = posicion; 
        }
        //consultores
        public String getnomObjeto(){
            return nomObjeto;
        }
        public double getPeso() {
            return peso;
        }
        public double getValor() {
            return valor;
        }
        public String getNomObjeto() {
            return nomObjeto;
        }
        public int getPosicion() {
            return posicion;
        }
        
    public String toString(){
        return ""+ getnomObjeto()+":  Peso --> "+getPeso()+ " Valor --> "+getValor()+
                " Posicion --> "+getPosicion();
    }    
        
}
