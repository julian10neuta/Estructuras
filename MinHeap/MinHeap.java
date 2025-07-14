class MinHeap{
    private int[] Heap;
    private int size;
    private int maxsize;

    public MinHeap(int maxsize){
        this.maxsize = maxsize;
        this.size = 0;
        this.Heap = new int[this.maxsize];
    }

    private int parent(int i){
        return (i - 1)/2;
    }

    private int left(int i){
        return (2*i + 1);
    }

    private int right(int i){
        return (2*i + 2);
    }

    private boolean isLeaf(int i){
        return i >= (size/2) && i < size;
    }

    public void swap(int a, int b){
        int med = Heap[b];
        Heap[b] = Heap[a];
        Heap[a] = med;
    }

    public void siftUp(int pos) {
        while (pos > 0 && Heap[pos] < Heap[parent(pos)]){
            swap(pos, parent(pos));
            siftUp(parent(pos));
        }
    }

    public void siftDown(int pos) {
        
        if (isLeaf(pos)) return;

        if (Heap[pos] > Heap[left(pos)] || Heap[pos] > Heap[right(pos)]){

            if (Heap[left(pos)] < Heap[right(pos)]){
                swap(pos, left(pos));
                siftDown(left(pos));
            } else {
                swap(pos, right(pos));
                siftDown(right(pos));
            }
        }
    }

    public void insert(int value){
        if (size >= maxsize){
            resize();
        }
        Heap[size] = value;
        siftUp(size);
        size++;
    }

    public int extractMin(){
        int min = Heap[0];
        swap(0, size-1);
        size--;
        siftDown(0);
        return min;
    }

    public void resize(){
        this.maxsize = this.maxsize * 2;
        int[] newHeap = new int[this.maxsize];
        System.arraycopy(this.Heap, 0, newHeap, 0, size);
        this.Heap = newHeap;
    }

    public void print()
    {

        for (int i = 0; i < size / 2; i++) {

            System.out.print("Parent Node : " + Heap[i]);

            if (left(i) < size) 
                System.out.print(" Left Child Node: " + Heap[left(i)]);

            if (right(i) < size) // the right child index must not
                        // be out of the index of the array
                System.out.print(" Right Child Node: "
                                 + Heap[right(i)]);

            System.out.println(); // for new line
        }
    }

}