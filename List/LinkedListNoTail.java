import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class LinkedListNoTail<T> implements LinkedList<T> {
    public Nodo<T> head;

    public LinkedListNoTail() {
        this.head = null;
    }
    @Override
    public void pushFront(T valor) {
        Nodo<T> nodo = new Nodo<T>(valor);
        nodo.next = head;
        head = nodo;
    }
    @Override
    public T popFront(){
        if (empty()){throw new NullPointerException("La Lista está vacía.");} 
        else {
            T eliminado = head.key;
            head = head.next;
            return eliminado;
        }
    }
    @Override
    public void pushBack(T valor){
        Nodo<T> nodo = new Nodo<T>(valor);
        if (empty()) { head = nodo;}
        else {
            Nodo<T> p = head;
            while (p.next != null) {p = p.next;}
            p.next = nodo;
        }
    }
    @Override
    public T popBack(){
        T ultimo;
        if (empty()){throw new NullPointerException("La lista está vacía");}
        if (head.next == null) {
            ultimo = head.key;
            head = null;
        } else {
            Nodo<T> p = head;
            while (p.next.next != null) {p = p.next;}
            ultimo = p.next.key;
            p.next = null;
        }
        return ultimo;
    }

    @Override
    public T topBack(){
        if (empty()){throw new NullPointerException("La lista está vacía");}
        T ultimo;
        if (head.next == null) { ultimo = head.key;}
        else {
            Nodo<T> p = head;
            while (p.next.next != null) {p = p.next;}
            ultimo = p.next.key;
        }
        return ultimo;
    }
    @Override
    public boolean find (T Key){
        if (empty()) {throw new NullPointerException("La lista está vacía.");}
        Nodo<T> p = head;
        while (p != null) {
            if (p.key.equals(Key)){ return true;}
            p = p.next;
        }
        return false; 
    }
    @Override
    public void erase (T Key){
        if (empty()) {throw new NullPointerException("La lista está vacía.");}
        if (find(Key)){
            Nodo<T> p = head;
            if (p.key.equals(Key)){head = p.next;}
            else {
                while (!p.next.key.equals(Key)) { p = p.next;}
                p.next = p.next.next;
            }
        } else {//throw new NullPointerException("key not found in list.");
        }
    }
    @Override
    public T topFront(){ return head.key;}
    @Override
    public boolean empty(){ return head == null;} 
    @Override
    public void addBefore(Nodo<T> node, T Key){
        if(empty()){throw new NullPointerException("There is no node in list.");}
        Nodo<T> nodoNuevo = new Nodo<T>(Key);
        Nodo<T> corredor = head;
        nodoNuevo.next = node;
        if (node == head){ head = nodoNuevo;} 
        else {
            while (corredor.next != null && corredor.next != node){corredor = corredor.next;}
            corredor.next = nodoNuevo;
            if (corredor.next == null){//throw new NoSuchElementException("Node is not in list.");
            }
        }    
    }

    public void addAfter(Nodo<T> nodo, T Key){
        Nodo<T> nuevoNodo = new Nodo<T>(Key);
        nuevoNodo.next = nodo.next;
        nodo.next = nuevoNodo;
    }

    public Nodo<T> getNodo(T Key){
        Nodo<T> p = head;
        while (p != null && !p.key.equals(Key)){p = p.next;}
        //if (p == null) {throw new NoSuchElementException("Node is not in list.");}
        return p;
    }

    public void imprimir(){
        Nodo<T> x = head;
        while (x != null){
            System.out.print(x.key + " ");
            x = x.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListNoTail<Integer> listaN = new LinkedListNoTail<Integer>();
        Random rand = new Random();
        long tiempo = 0;
        //pushFront(), pushBack()
        
        for (int i = 0; i<100;i++){
            listaN = new LinkedListNoTail<Integer>(); //Reiniciar lista en cada prueba
            long start = System.nanoTime();
            for (int j = 0; j < 10000; j++) {listaN.pushFront(rand.nextInt());}
            long end = System.nanoTime();
            tiempo += (end - start);
        } 
        System.out.println("Tiempo: "+ (tiempo/10) + "ns");

        // popFront(), popBack(), topFront(), topBack()

        for (int i = 0; i<100;i++){
            listaN = new LinkedListNoTail<Integer>(); //Reiniciar lista en cada prueba
            for (int j = 0; j < 1001; j++) {listaN.pushFront(rand.nextInt());}
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {listaN.topBack();}
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");

        //find(), erase()

        /*for (int i = 0; i<100;i++){
            listaN = new LinkedListNoTail<Integer>(); //Reiniciar lista en cada prueba
            for (int j = 0; j < 1000; j++) {listaN.pushFront(rand.nextInt());}
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {listaN.find(rand.nextInt());}
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        // AddBefore(), AddAfter()

        /*List<Integer> valores = new ArrayList<>();
        for (int i = 0; i < 10000; i++) valores.add(i);
        Collections.shuffle(valores);
        for (int j = 0; j < 10000; j++) {listaN.pushFront(valores.get(j));}

        for (int j = 0; j < 10000; j++) {
            Nodo<Integer> nodoposterior = listaN.getNodo(rand.nextInt(10000));
            long start = System.nanoTime();
            listaN.addAfter(nodoposterior, rand.nextInt());
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo) + "ns");*/

        //empty()

        /*for (int j = 0; j < 10000; j++) {listaN.pushFront(rand.nextInt());}
        for (int i=0;i<100;i++){
            long start = System.nanoTime();
            listaN.empty();
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        //imprimir()

        /*for (int j = 0; j < 10000; j++) {listaN.pushFront(rand.nextInt());}
        long start = System.nanoTime();
        listaN.imprimir();
        long end = System.nanoTime();
        tiempo = (end - start);
        System.out.println("Tiempo: "+ (tiempo) + "ns");*/

    }   
}