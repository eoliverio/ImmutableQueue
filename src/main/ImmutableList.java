package main;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImmutableList<T> implements Iterable<T> {

    /** The head is the most recently added element. */
    private final T head;

    /** The tail is the list following the head element. */
    private final ImmutableList<T> tail;

    /** An empty list. */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private final static ImmutableList NULL_LIST = new ImmutableList(null, null);
    
    /**
     * Checks
     * 
     * @param head
     * @param tail
     */
    private ImmutableList(T head, ImmutableList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Adds a new element to the list.
     * 
     * @param t the element to be added
     * @return a new instance of this list including the added element
     */
    public ImmutableList<T> add(T t) {
        return new ImmutableList<T>(t, this);
    }

    /**
     * Reverses the order of the elements of this list.
     * 
     * @return a new instance of this list in reversed order
     */
    public ImmutableList<T> reverse() {
        ImmutableList<T> result = getEmptyImmutableList();
        for (T t : this) {
            result = result.add(t);
        }
        return result;
    }

    /**
     * Checks if the list is empty or not.
     * 
     * @return true if the list contains no elements
     */
    public boolean isEmpty() {
        return this == NULL_LIST;
    }

    /**
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> ImmutableList<T> getEmptyImmutableList() {
        return NULL_LIST;
    }

    /**
     * Gets the head of this list. <br>
     * The head is the most recently added element into this list.
     * 
     * @return head element of this list
     */
    public T getHead() {
        return head;
    }

    /**
     * Gets the tail of this list. <br>
     * The tail is the least recently added element into this list.
     * 
     * @return last element of this list.
     */
    public ImmutableList<T> getTail() {
        return tail;
    }

    /**
     * Custom iterator for this list. <br>
     * Strictly not allowing remove() operation to adhere to immutable principles.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private ImmutableList<T> list = ImmutableList.this;

            @Override
            public boolean hasNext() {
                return !list.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T t = list.head;
                list = list.tail;
                return t;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    @Override
    public String toString() {
        String output = "[ ";
        for (T t : this) {
            output += t + " ";
        }
        return output + "]";
    }
}
