import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class LinkedListWithTail<T> implements LinkedList<T> {
    public Nodo<T> head;
    public Nodo<T> tail;

    public LinkedListWithTail () {
        this.head = null;
        this.tail = null;
    }

    public void pushFront(T key) {
        Nodo<T> nodo = new Nodo<>(key);
        nodo.next = head;
        head = nodo;
        if (tail == null){
            tail = head;
        }
    }

    public T topFront()  {
        return head.key;
    }
    public T popFront() {
        if (empty()){throw new NullPointerException("La lista está vacía.");}
        T eliminado = head.key;
        head = head.next;
        if (head == null){ tail = null;}
        return eliminado;
    }
    public void pushBack(T key)  {
        Nodo<T> nodo = new Nodo<>(key);
        if (tail == null){ head = nodo;}
        else { tail.next = nodo;}
        tail = nodo;
    }
    public T topBack()  {
        return tail.key;
    }
    public T popBack()  {
        if (empty()){throw new NullPointerException("Lista vacía.");}
        T ultimo = tail.key;
        if (head == tail){
            head = null;
            tail = null;
        } else {
            Nodo<T> p = head;
            while (p.next.next != null){p = p.next;}
            p.next = null;
            tail = p;
        }
        return ultimo;
    }
    public boolean find(T key) {
        if (empty()) {throw new NullPointerException("La lista está vacía.");}
        Nodo<T> p = head;
        while (p != null) {
            if (p.key.equals(key)){ return true;}
            p = p.next;
        }
        return false; 
    }
    public void erase(T Key)  {
        if (empty()) {throw new NullPointerException("La lista está vacía.");}
        if (find(Key)){
            Nodo<T> p = head;
            if (p.key.equals(Key)){
                head = p.next;
                if (head == null) {tail = null;}
            }
            else {
                while (!p.next.key.equals(Key)) { p = p.next;}
                if (p.next == tail){ tail = p;}
                p.next = p.next.next;
            }
        } else {//throw new NoSuchElementException("key not found in list.");
        }
    }

    public boolean empty() { return head == null;}

    public void addBefore(Nodo<T> node, T key) {
        if(empty()){throw new NullPointerException("There is no node in list.");}
        Nodo<T> nodoNuevo = new Nodo<>(key);
        Nodo<T> corredor = head;
        nodoNuevo.next = node;
        if (node == head){ head = nodoNuevo;} 
        else {
            while (corredor.next != null && corredor.next != node){corredor = corredor.next;}
            corredor.next = nodoNuevo;
            if (corredor.next == null){throw new NoSuchElementException("Node is not in list.");}
        } 
    }
    public void addAfter(Nodo<T> node, T key)  {
        Nodo<T> nuevoNodo = new Nodo<>(key);
        nuevoNodo.next = node.next;
        node.next = nuevoNodo;
        if (tail == node){ tail = nuevoNodo;}
    }

    public Nodo<T> getNodo(T Key){
        Nodo<T> p = head;
        while (p != null && !p.key.equals(Key)){p = p.next;}
        if (p == null) {throw new NoSuchElementException("Node is not in list.");}
        return p;
    }

    public void imprimir() {
        Nodo<T> x = head;
        while (x != null){
            System.out.print(x.key + " ");
            x = x.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListWithTail<Integer> listaW = new LinkedListWithTail<Integer>();
        Random rand = new Random();
        long tiempo = 0;
        //pushFront(), pushBack()
        
        for (int i = 0; i<100;i++){
            listaW = new LinkedListWithTail<Integer>(); //Reiniciar lista en cada prueba
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {listaW.pushBack(rand.nextInt());}
            long end = System.nanoTime();
            tiempo += (end - start);
        } 
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");

        // popFront(), popBack(), topFront(), topBack()

        /*for (int i = 0; i<100;i++){
            listaW = new LinkedListWithTail<Integer>(); //Reiniciar lista en cada prueba
            for (int j = 0; j < 1001; j++) {listaW.pushFront(rand.nextInt());}
            long start = System.nanoTime();
            for (int j = 0; j < 1000; j++) {listaW.topFront();}
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        //find(), erase()

        /*for (int i = 0; i<100;i++){
            listaW = new LinkedListWithTail<Integer>(); //Reiniciar lista en cada prueba
            for (int j = 0; j < 10; j++) {listaW.pushFront(rand.nextInt());}
            long start = System.nanoTime();
            for (int j = 0; j < 10; j++) {listaW.find(rand.nextInt());}
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        // AddBefore(), AddAfter()

        /*List<Integer> valores = new ArrayList<>();
        for (int i = 0; i < 10000; i++) valores.add(i);
        Collections.shuffle(valores);
        for (int j = 0; j < 10000; j++) {listaW.pushFront(valores.get(j));}

        for (int j = 0; j < 10000; j++) {
            Nodo<Integer> nodoposterior = listaW.getNodo(rand.nextInt(10000));
            long start = System.nanoTime();
            listaW.addBefore(nodoposterior, rand.nextInt());
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo) + "ns");*/

        //empty()

        /*for (int j = 0; j < 10000; j++) {listaW.pushFront(rand.nextInt());}
        for (int i=0;i<100;i++){
            long start = System.nanoTime();
            listaW.empty();
            long end = System.nanoTime();
            tiempo += (end - start);
        }
        System.out.println("Tiempo: "+ (tiempo/100) + "ns");*/

        //imprimir()

        /*for (int j = 0; j < 10000; j++) {listaW.pushFront(rand.nextInt());}
        long start = System.nanoTime();
        listaW.imprimir();
        long end = System.nanoTime();
        tiempo = (end - start);
        System.out.println("Tiempo: "+ (tiempo) + "ns");*/
    }
}
