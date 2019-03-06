/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesavl;

/**
 *
 * @author estudiantes
 */
public class NodoArbolAvl {
    int dato,FacEqu;
    String nombre;
    String numero;
    
    NodoArbolAvl hijoIzquierdo, HijoDerecho;
    public NodoArbolAvl(int d){
        this.dato=d;
        this.FacEqu=0;
        this.nombre = null;
        this.HijoDerecho=null;
        this.hijoIzquierdo=null;
        
    }
    
    public String toString(){
        return nombre;
    }
}
