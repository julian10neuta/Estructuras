import java.util.NoSuchElementException;

public class OrderedList implements OrderedListInterface{
    int[] array;
    int cap;

    public OrderedList(int capacidad){
        this.array = new int[capacidad];
        this.cap = 0;
    }

    public void insert(int value){
        if (cap == array.length){System.out.println("La lista está llena");}
        else{
            int pos = cap -1;
            while (pos >= 0 && value < array[pos]) {
                array[pos+1] = array[pos];
                pos--;
            }
            array[pos+1] = value;
            cap++;
        }
    }

    public void delete(int value){
        int pos = search(value);
        if (pos == -1){throw new NoSuchElementException("El numero no está en la lista.");}
        for (int i=pos;i<cap-1;i++){
            array[i] = array[i+1];
        }
        cap--;
    }
    public int search (int value){
        int left = 0;
        int rigth = cap-1;
        while (left <= rigth){
            int middle = (left + rigth)/2;
            if (array[middle] == value){return middle;}
            else if (array[middle] > value){rigth = middle-1;}
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
        OrderedList lista = new OrderedList(10);
        lista.insert(4);
        lista.insert(7);
        lista.insert(2);
        lista.insert(3);
        lista.insert(8);
        System.out.println(lista.search(3));
        lista.print();
        lista.delete(2);
        lista.print();
    }
}