/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolesavl;

/**
 *
 * @author juliana
 */
public class Rifa {
    
    int numero;
    
    public Rifa(){
        this.numero = numero;
    }
    
    //Método de Jugar 
    public int jugar(){
        numero = (int) (Math.random()*20+1);
        return numero;
    }
}
