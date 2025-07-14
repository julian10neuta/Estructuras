import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class DoubleLinkedList<T> implements LinkedList<T> {
    public Nodo<T> head;
    public Nodo<T> tail;

    public DoubleLinkedList(){
        this.head = null;
        this.tail = null;
    }

    public void pushFront(T key){
        Nodo<T> nodo = new Nodo<>(key);
        if (head == null){head = tail = nodo;}
        else{
            nodo.next = head;
            nodo.next.prev = nodo;
            head = nodo;
        }
    }

    public T topFront(){
        if(empty()){throw new NullPointerException("La lista está vacía.");}
        return head.key;
    }
    public T popFront(){
        if(empty()){throw new NullPointerException("La lista está vacía.");}
        T eliminado = head.key;
        if (head.next == null) {head = tail = null;}
        else{
            head = head.next;
            head.prev = null;
        }
        return eliminado;
    }
    public void pushBack(T key){
        Nodo<T> nodo = new Nodo<>(key);
        if (tail == null){head = tail = nodo;}
        else {
            tail.next = nodo;
            nodo.prev = tail;
            tail = nodo;
        }
    }
    public T topBack(){
        if(empty()){throw new NullPointerException("La lista está vacía.");}
        return tail.key;
    }
    public T popBack(){
        if(empty()){throw new NullPointerException("La lista está vacía.");}
        T ultimo = tail.key;
        if (head == tail){head = tail = null;}
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return ultimo;
    }
    public boolean find(T key){
        if (empty()) {throw new NullPointerException("La lista está vacía.");}
        Nodo<T> p = head;
        while (p != null) {
            if (p.key.equals(key)){ return true;}
            p = p.next;
        }
        return false;
    }

    public void erase(T key){
        if (empty()) {throw new NullPointerException("La lista está vacía.");}
        if(find(key)){
            if (head == tail){ head = tail = null;}
              else if (head.key.equals(key)){
                head = head.next;
                head.prev = null;
            } else if (tail.key.equals(key)){
                tail = tail.prev;
                tail.next = null;
            } else {
                Nodo<T> corredor = head;
                while (!corredor.key.equals(key)){corredor = corredor.next;}
                corredor.prev.next = corredor.next;
                corredor.next.prev = corredor.prev;
                corredor.next = corredor.prev = null;
            }
        } else {//throw new NoSuchElementException("Node not in list.");
        }
    }

    public boolean empty(){ return head == null;}

    public void addBefore(Nodo<T> node, T key){
        if(empty()){throw new NullPointerException("There is no node in list.");}
        Nodo<T> nodoNuevo = new Nodo<>(key);
        nodoNuevo.next = node;
        nodoNuevo.prev = node.prev;
        node.prev = nodoNuevo;
        if (head == node){head = nodoNuevo;}
        else {nodoNuevo.prev.next = nodoNuevo;}
    }
    public void addAfter(Nodo<T> node, T key){
        if(empty()){throw new NullPointerException("There is no node in list.");}
        Nodo<T> nodoNuevo = new Nodo<>(key);
        nodoNuevo.prev = node;
        nodoNuevo.next = node.next;
        node.next = nodoNuevo;
        if (tail == node){ tail = nodoNuevo;}
        else{ nodoNuevo.next.prev = nodoNuevo;}
    }
    public Nodo<T> getNodo(T Key){
        Nodo<T> p = head;
        while (p != null && !p.key.equals(Key)){p = p.next;}
        if (p == null) {throw new NoSuchElementException("Node is not in list.");}
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
        DoubleLinkedList<Integer> listaD = new DoubleLinkedList<Integer>();
        Random rand = new Random();
        long tiempo = 0;
        //pushFront(), pushBack()
        
        for (int i = 0; i<100;i++){
            listaD = new DoubleLinkedList<Integer>(); //Reiniciar lista en cada prueba
            long start = System.nanoTime();
            for (int j = 0; j < 10000; j++) {listaD.pushFront(rand.nextInt());}
            long end = System.nanoTime();
            tiempo += (end - start);
        } 
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");

        // popFront(), popBack(), topFront(), topBack()

        /*for (int i = 0; i<100;i++){
            listaD = new DoubleLinkedList<Integer>(); //Reiniciar lista en cada prueba
            for (int j = 0; j < 10001; j++) {listaD.pushFront(rand.nextInt());}
            long start = System.nanoTime();
            for (int j = 0; j < 10000; j++) {listaD.topFront();}
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        //find(), erase()

        /*for (int i = 0; i<100;i++){
            listaD = new DoubleLinkedList<Integer>(); //Reiniciar lista en cada prueba
            for (int j = 0; j < 10000; j++) {listaD.pushFront(rand.nextInt());}
            long start = System.nanoTime();
            for (int j = 0; j < 10000; j++) {listaD.find(rand.nextInt());}
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        // AddBefore(), AddAfter()

        /*List<Integer> valores = new ArrayList<>();
        for (int i = 0; i < 10000; i++) valores.add(i);
        Collections.shuffle(valores);
        for (int j = 0; j < 10000; j++) {listaD.pushFront(valores.get(j));}

        for (int j = 0; j < 10000; j++) {
            Nodo<Integer> nodoposterior = listaD.getNodo(rand.nextInt(10000));
            long start = System.nanoTime();
            listaD.addBefore(nodoposterior, rand.nextInt());
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo) + "ns");*/

        //empty()

        /*for (int j = 0; j < 10000; j++) {listaD.pushFront(rand.nextInt());}
        for (int i=0;i<100;i++){
            long start = System.nanoTime();
            listaD.empty();
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        //imprimir()

        /*for (int j = 0; j < 10; j++) {listaD.pushFront(rand.nextInt());}
        long start = System.nanoTime();
        listaD.imprimir();
        long end = System.nanoTime();
        tiempo = (end - start);
        System.out.println("Tiempo: "+ (tiempo) + "ns");*/
    }
}
