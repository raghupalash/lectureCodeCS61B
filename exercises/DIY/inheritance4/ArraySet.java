import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class ArraySet<T> implements Iterable<T>{
    private T[] set;
    private int length;
    private int size;

    public ArraySet() {
        length = 100;
        set = (T[]) new Object[length];
        size = 0;
    }

    private class ArraySetIterator implements Iterator<T> {
        int wizPos;
        ArraySetIterator() {
            wizPos = 0;
        }
        public boolean hasNext() {
            if (wizPos == size) {
                return false;
            }
            return true;
        }
        public T next() {
            T returnItem = set[wizPos];
            wizPos++;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size; i++) {
            if (set[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. 
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        // Checks if the item is already there in the array and adds it if not
        // Also resizes the array if the size exceeds length
        if (x == null) {
            return;
        }
        if (contains(x)) {
            return;
        }
        if (size == length) {
            resize();
        }
        set[size] = x;
        size++;
        return;
    }

    public void resize() {
        T[] newArray = (T[]) new Object[length * 2];
        System.arraycopy(set, 0, newArray, 0, length);
        set = newArray;
        length = length * 2;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    /* Returns a String form of the Set */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{");
        for (int i = 0; i < size; i++) {
            s.append(set[i]);
            if (i != size - 1) {
                s.append(", ");
            }
        }
        s.append("}");
        return s.toString();
    }

    // /* Another implementation */
    // @Override
    // public String toString() {
    //     List<String> a = new ArrayList<>();
    //     for (T item: this) {
    //         a.add(item.toString());
    //     }
    //     return "{" + String.join(", ", a) + "}"; // twice as slow because of adding brackets
    // }

    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null) { return false; }
        if (o.getClass() != this.getClass()) { return false; }
        ArraySet<T> other = (ArraySet<T>) o;
        if (other.size() != this.size()) { return false; }
        for (T item: this) {
            if (!other.contains(item)) { return false; }
        }
        return true;
    }
    
    public static <Glerp> ArraySet<Glerp> of(Glerp... stuff) {
        ArraySet<Glerp> aset = new ArraySet<>();
        for (Glerp item: stuff) {
            aset.add(item);
        }
        return aset;
    }

    public static void main(String[] args) {
        ArraySet<String> s = new ArraySet<>();
        s.add(null);
        s.add("horse");
        s.add("fish");
        s.add("house");
        s.add("fish");  
        s.add("horse"); 
        String a = new String("fish"); // Had to use the new keyword because of "Interning"
        String b = new String("fish");
        s.add(a);     
        s.add(b);

        ArraySet<Integer> aset = ArraySet.of(1, 2, 3, 4);
        System.out.println(aset);
    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
