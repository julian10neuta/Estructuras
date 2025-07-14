public class Nodo <T>{
    T key;
    Nodo<T> next;
    Nodo<T> prev;

    Nodo(T key) {
        this.key = key;
        this.next = null;
        this.prev = null;
    }
}

//Cambio de prueba para git