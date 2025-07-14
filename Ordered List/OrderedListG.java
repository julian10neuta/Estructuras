import java.util.NoSuchElementException;

public class OrderedListG <T extends Comparable<T>> implements OrderedListInterfaceG <T>{
    T[] array;
    int cap;

    public OrderedListG(int capacidad){
        this.array = (T[]) new Comparable[capacidad];
        this.cap = 0;
    }

    public void insert(T value){
        if (cap == array.length){System.out.println("La lista está llena");}
        else{
            int pos = cap -1;
            while (pos >= 0 && value.compareTo(array[pos]) < 0) {
                array[pos+1] = array[pos];
                pos--;
            }
            array[pos+1] = value;
            cap++;
        }
    }

    public void delete(T value){
        int pos = search(value);
        if (pos == -1){throw new NoSuchElementException("El valor no está en la lista.");}
        for (int i=pos;i<cap-1;i++){
            array[i] = array[i+1];
        }
        cap--;
    }
    public int search (T value){
        int left = 0;
        int rigth = cap-1;
        while (left <= rigth){
            int middle = (left + rigth)/2;
            if (array[middle].compareTo(value) == 0){return middle;}
            else if (array[middle].compareTo(value) > 0){rigth = middle-1;}
            else {left = middle+1;}
        }
        return -1;
    }

    public void print(){
        for (int i=0;i<cap;i++){
            System.out.print(array[i]+ " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        //int numero = sc.nextInt();
        OrderedListG<Integer> lista = new OrderedListG<>(10);
        lista.insert(4);
        lista.insert(7);
        lista.insert(2);
        lista.insert(3);
        lista.insert(8);
        System.out.println(lista.search(3));
        lista.print();
        lista.delete(2);
        lista.print();
        OrderedListG<String> listax = new OrderedListG<>(5);
        listax.insert("Alejandra");
        listax.insert("Valentina");
        listax.insert("Carlos");
        listax.insert("Katherine");
        listax.print();
        listax.delete("Carlos");
        listax.print();
    }
}