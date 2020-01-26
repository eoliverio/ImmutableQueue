package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import main.ImmutableQueue;
import main.Queue;

class ImmutableQueueTest {

    @Test
    void canEnqueue() {
        Queue<Integer> iQueue = new ImmutableQueue<Integer>();

        Integer one = new Integer(1);
        iQueue = iQueue.enQueue(one);
        Integer enqueued = ((ImmutableQueue<Integer>) iQueue).head();

        assertEquals(enqueued, one);
    }

    @Test
    void canDequeue() {
        Queue<Integer> iQueue = new ImmutableQueue<Integer>();

        Integer two = new Integer(2);
        iQueue = iQueue.enQueue(new Integer(1));
        iQueue = iQueue.enQueue(two);
        iQueue = iQueue.enQueue(new Integer(3));
        iQueue = iQueue.enQueue(new Integer(4));
        iQueue = iQueue.deQueue();
        Integer head = ((ImmutableQueue<Integer>) iQueue).head();

        assertEquals(head, two);
    }

    @Test
    void canEnqueueAfterDequeue() {
        Queue<Integer> iQueue = new ImmutableQueue<Integer>();

        Integer two = new Integer(2);
        iQueue = iQueue.enQueue(new Integer(1));
        iQueue = iQueue.enQueue(two);
        iQueue = iQueue.enQueue(new Integer(3));
        iQueue = iQueue.enQueue(new Integer(4));
        iQueue = iQueue.deQueue();
        Integer enqueued = ((ImmutableQueue<Integer>) iQueue).head();

        assertEquals(enqueued, two);
    }

    @Test
    void canDequeueUntilEmpty() {
        Queue<Integer> queue = new ImmutableQueue<Integer>();

        queue = queue.enQueue(new Integer(1));
        queue = queue.enQueue(new Integer(2));
        queue = queue.enQueue(new Integer(3));
        queue = queue.enQueue(new Integer(4));

        ImmutableQueue<Integer> iQueue = (ImmutableQueue<Integer>) queue;

        while (iQueue.head() != null) {
            iQueue = (ImmutableQueue<Integer>) iQueue.deQueue();
        }

        assertNull(iQueue.head());
    }

    @Test
    void enqueueDequeueEnqueueDequeue() {
        Queue<Integer> queue = new ImmutableQueue<Integer>();

        Integer lastElement = new Integer(99);
        queue = queue.enQueue(new Integer(1));
        queue = queue.enQueue(new Integer(2));
        queue = queue.enQueue(new Integer(3));
        queue = queue.enQueue(new Integer(4));
        queue = queue.deQueue();
        queue = queue.deQueue();
        queue = queue.enQueue(new Integer(5));
        queue = queue.enQueue(new Integer(6));
        queue = queue.enQueue(lastElement);
        queue = queue.deQueue();
        queue = queue.deQueue();
        queue = queue.deQueue();
        queue = queue.deQueue();

        ImmutableQueue<Integer> iQueue = (ImmutableQueue<Integer>) queue;
        assertEquals(lastElement, iQueue.head());
    }

    @Test
    void getHeadAfterEnqueue() {
        Queue<Integer> iQueue = new ImmutableQueue<Integer>();

        Integer one = new Integer(1);
        Integer two = new Integer(2);
        iQueue = iQueue.enQueue(one);
        iQueue = iQueue.enQueue(two);
        Integer head = ((ImmutableQueue<Integer>) iQueue).head();

        assertEquals(head, one);
    }

    @Test
    void getHeadAfterDequeue() {
        Queue<Integer> iQueue = new ImmutableQueue<Integer>();

        Integer one = new Integer(1);
        Integer two = new Integer(2);
        iQueue = iQueue.enQueue(one);
        iQueue = iQueue.enQueue(two);
        iQueue = iQueue.deQueue();
        Integer head = ((ImmutableQueue<Integer>) iQueue).head();

        assertEquals(head, two);
    }

    @Test
    void nullHeadForEmptyQueue() {
        Queue<Integer> iQueue = new ImmutableQueue<Integer>();

        assertNull(((ImmutableQueue<Integer>) iQueue).head());
    }

    @Test
    void cantDequeueEmpty() {
        Queue<Integer> iQueue = new ImmutableQueue<Integer>();

        Assertions.assertThrows(NoSuchElementException.class, () -> iQueue.deQueue());
    }
}
