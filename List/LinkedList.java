public interface LinkedList <T> {
    public void pushFront(T key); // Add to front
    public void pushBack(T key);  // Add to back
    public T popFront(); // Remove front item
    public T popBack();  // remove back item
    public boolean find(T key); // Is key in list?
    public void erase(T key);  // Remove key from list
    public void addBefore(Nodo<T> node, T key); // Adds key before node
    public void addAfter(Nodo<T> node, T key);  // adds key after node
    public T topFront();  // Return front item
    public T topBack();  // Return back item
    public boolean empty(); // Empty list?
} 
