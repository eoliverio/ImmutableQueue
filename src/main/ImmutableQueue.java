package main;

import java.util.NoSuchElementException;

/**
 * Immutable Queue implementation of the Software Engineer Challenge by PayPay. <br>
 * Problem definition:
 * <a href="https://github.com/Pay-Baymax/SoftwareEngineerChallenge">
 *   Pay-Baymax/SoftwareEngineerChallenge
 * </a>
 * 
 * @author Oliverio
 *
 * @param <T>
 */
public class ImmutableQueue<T> implements Queue<T> {

    /** The list where new elements are enqueued */
    private final ImmutableList<T> enqueueList;

    /** The list where elements are dequeued */
    private final ImmutableList<T> dequeueList;

    /** Constructor */
    public ImmutableQueue() {
        this.enqueueList = ImmutableList.getEmptyImmutableList();
        this.dequeueList = ImmutableList.getEmptyImmutableList();
    }

    /**
     * Internally used constructor for creating new instances.
     * 
     * @param enqList where elements are enqueued
     * @param deqList where elements are dequeued
     */
    private ImmutableQueue(ImmutableList<T> enqList, ImmutableList<T> deqList) {
        this.enqueueList = enqList;
        this.dequeueList = deqList;
    }

    /**
     * Enqueues an element to the queue.
     * 
     * @return a new instance of this queue including the enqueued element
     */
    @Override
    public Queue<T> enQueue(T t) {
        return new ImmutableQueue<T>(enqueueList.add(t), dequeueList);
    }

    /**
     * Dequeues by removing the head element of the queue.
     * 
     * @return a new instance of this queue excluding the dequeued element
     */
    @Override
    public Queue<T> deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        // The head of a queue is the element removed when dequeuing,
        //   but the head of the list is the most recently added element.
        if (dequeueList.isEmpty()) {
            // Reverse the enqueue list to be able to access the least recently added element in the list.
            ImmutableList<T> reversedEnqList = enqueueList.reverse();
            return new ImmutableQueue<T>(ImmutableList.getEmptyImmutableList(), reversedEnqList.getTail());
        } else {
            // Since the content of dequeue list is already reversed,
            //   taking the tailing list of the head element is effectively the "dequeued" list. 
            return new ImmutableQueue<T>(enqueueList, dequeueList.getTail());
        }
    }

    /**
     * Gets the head of the queue. <br>
     * The head is the element to be removed when dequeuing.
     * 
     * @return head element of the queue
     */
    @Override
    public T head() {
        // Since the head of a queue is the element removed when dequeuing,
        //   it is intuitive to check using the dequeue list. 
        if (dequeueList.isEmpty()) {
            // Since the head of a list is the most recently added element (as opposed to a queue),
            //   the enqueue list should be reversed to get the head of the queue.
            return enqueueList.reverse().getHead();
        } else {
            // The dequeue list is created from a reversed enqueueList
            //   so there is no need to reverse it to get the head element.
            return dequeueList.getHead();
        }
    }

    /**
     * Checks if the queue is empty or not.
     * 
     * @return true if the queue contains no elements
     */
    @Override
    public boolean isEmpty() {
        return enqueueList.isEmpty() && dequeueList.isEmpty();
    }
    
    @Override
    public String toString() {
        String output = "[ ";

        for (T t : dequeueList) {
            output += t + " ";
        }

        for (T t : enqueueList.reverse()) {
            output += t + " ";
        }

        return output + "]";
    }
}
