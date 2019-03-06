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
public class ArbolAvl {
    public NodoArbolAvl raiz;
    
    public ArbolAvl(){
    raiz=null;
}

public NodoArbolAvl buscar (int d,NodoArbolAvl r ){
    if(raiz==null){
        return null;
    }else if(r.dato==0){
        return r;
    }else if(r.dato<d){
        return buscar(d,r.HijoDerecho);
    }else{
        return buscar(d,r.hijoIzquierdo);
    } 
}

public int ObtenerFE(NodoArbolAvl x){
    if(x==null){
        return -1;
    }else{
        return x.FacEqu;
    }
}
//simple izquierda
public NodoArbolAvl rotacionIzquierda(NodoArbolAvl c){
    NodoArbolAvl auxiliar=c.hijoIzquierdo;
    c.hijoIzquierdo=auxiliar.HijoDerecho;
    auxiliar.HijoDerecho=c;
    c.FacEqu=Math.max(ObtenerFE(c.hijoIzquierdo),ObtenerFE(c.HijoDerecho))+1;
    auxiliar.FacEqu=Math.max(ObtenerFE(auxiliar.hijoIzquierdo),ObtenerFE(auxiliar.HijoDerecho))+1;
    return auxiliar;
}
//simple izquierda
public NodoArbolAvl rotacionDerecha(NodoArbolAvl c){
    NodoArbolAvl auxiliar=c.HijoDerecho;
    c.HijoDerecho=auxiliar.hijoIzquierdo;
    auxiliar.hijoIzquierdo=c;
    c.FacEqu=Math.max(ObtenerFE(c.hijoIzquierdo),ObtenerFE(c.HijoDerecho))+1;
    auxiliar.FacEqu=Math.max(ObtenerFE(auxiliar.hijoIzquierdo),ObtenerFE(auxiliar.HijoDerecho))+1;
    return auxiliar;
}
//doble izquierda
public NodoArbolAvl rotacionDobleIzquierda(NodoArbolAvl c){
    NodoArbolAvl temporal;
    c.hijoIzquierdo=rotacionDerecha(c.hijoIzquierdo);
    temporal=rotacionIzquierda(c);
    return temporal;
}
//doble derecha
public NodoArbolAvl rotacionDobleDerecha(NodoArbolAvl c){
    NodoArbolAvl temporal;
    c.HijoDerecho=rotacionIzquierda(c.HijoDerecho);
    temporal=rotacionDerecha(c);
    return temporal;
}

public NodoArbolAvl insertarAvl(NodoArbolAvl nuevo,NodoArbolAvl subArb){
    NodoArbolAvl nuevoPadre=subArb;
    if(nuevo.dato<subArb.dato){
        if(subArb.hijoIzquierdo==null){
            subArb.hijoIzquierdo=nuevo;
        }else{
            subArb.hijoIzquierdo=insertarAvl(nuevo, subArb.hijoIzquierdo);
            if((ObtenerFE(subArb.hijoIzquierdo))-(ObtenerFE(subArb.HijoDerecho))==2){
                if(nuevo.dato<subArb.hijoIzquierdo.dato){
                    nuevoPadre=rotacionIzquierda(subArb);
                }else{
                    nuevoPadre=rotacionDobleIzquierda(subArb);
                }
            }
        }  
    }else if(nuevo.dato>subArb.dato){
        if(subArb.HijoDerecho==null){
            subArb.HijoDerecho=nuevo;
        }else{
            subArb.HijoDerecho=insertarAvl(nuevo, subArb.HijoDerecho);
            if((ObtenerFE(subArb.HijoDerecho))-(ObtenerFE(subArb.hijoIzquierdo))==2){
                 if(nuevo.dato>subArb.HijoDerecho.dato){
                     nuevoPadre=rotacionDerecha(subArb);
                 }else{
                     nuevoPadre=rotacionDobleDerecha(subArb);
                 }
            }
        }
    }else{
        System.out.println("Nodo duplicado");
    }
    if((subArb.hijoIzquierdo==null) && (subArb.HijoDerecho!=null)){
        subArb.FacEqu=subArb.HijoDerecho.FacEqu+1;
    }else if((subArb.HijoDerecho==null)&& (subArb.hijoIzquierdo!=null)){
        subArb.FacEqu=subArb.hijoIzquierdo.FacEqu+1;
    }else{
        subArb.FacEqu=Math.max(ObtenerFE(subArb.hijoIzquierdo),ObtenerFE(subArb.HijoDerecho));
    }
    return nuevoPadre;
}

public void insertar(int d,String nombre){
    NodoArbolAvl nuevo= new NodoArbolAvl(d);
    if(raiz==null){
        raiz=nuevo;
    }else{
        raiz=insertarAvl(nuevo,raiz);
    }
}

//Elimiar
//Método para eliminar un nodo
   public boolean eliminar(int d){
       NodoArbolAvl auxiliar = raiz;
       NodoArbolAvl padre = raiz;
       boolean esHijoIzq = true;
       
       while(auxiliar.dato != d){
           padre = auxiliar;
           if(d<auxiliar.dato){
              esHijoIzq = true;
              auxiliar = auxiliar.hijoIzquierdo;
           }else{
               esHijoIzq = false;
               auxiliar = auxiliar.HijoDerecho;
           }
           
           if(auxiliar == null){
               return false;
           }
       }//fin del while
       
       if(auxiliar.hijoIzquierdo == null && auxiliar.HijoDerecho == null){
           if(auxiliar == raiz){
               raiz = null;
           }else if(esHijoIzq){
               padre.hijoIzquierdo=null;
           }else{
               padre.HijoDerecho=null;
           }
       }else if(auxiliar.HijoDerecho == null){
           if(auxiliar == raiz){
               raiz = auxiliar.hijoIzquierdo;
           }else if(esHijoIzq){
               padre.hijoIzquierdo=auxiliar.hijoIzquierdo;
           }else{
               padre.HijoDerecho=auxiliar.hijoIzquierdo;
           }
       }else if(auxiliar.hijoIzquierdo == null){
           if(auxiliar == raiz){
               raiz = auxiliar.HijoDerecho;
           }else if(esHijoIzq){
               padre.hijoIzquierdo=auxiliar.HijoDerecho;
           }else{
               padre.HijoDerecho=auxiliar.hijoIzquierdo;
           }
       }else{
           NodoArbolAvl reemplazo = obtenerNodoReemplazo(auxiliar);
           if(auxiliar == raiz){
               raiz = reemplazo;
           }else if(esHijoIzq){
               padre.hijoIzquierdo = reemplazo;
           }else{
               padre.HijoDerecho = reemplazo;
           }
           
           reemplazo.hijoIzquierdo = auxiliar.hijoIzquierdo;
       }
       
       ObtenerFE(raiz);
       
       return true;
   }
   
   //Método encargado de devolver el nodo reemplazo
   public NodoArbolAvl obtenerNodoReemplazo(NodoArbolAvl nodoReemp){
       NodoArbolAvl reemplazarPadre = nodoReemp;
       NodoArbolAvl reemplazo = nodoReemp;
       NodoArbolAvl auxiliar = nodoReemp.HijoDerecho;
       
       while(auxiliar != null){
           reemplazarPadre = reemplazo;
           reemplazo = auxiliar;
           auxiliar = auxiliar.hijoIzquierdo;
       }
       if(reemplazo != nodoReemp.HijoDerecho){
           reemplazarPadre.hijoIzquierdo = reemplazo.HijoDerecho;
           reemplazo.HijoDerecho = nodoReemp.HijoDerecho;
       }
       System.out.println("El nodo reemplazo es: "+reemplazo);
       return reemplazo;
   }
   
   //Recorrer Árbol
   public void inOrden(NodoArbolAvl r){
       if(r != null){
           inOrden(r.hijoIzquierdo);
           System.out.print(r.dato);
           inOrden(r.HijoDerecho);
       }
   }

}