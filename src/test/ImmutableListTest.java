package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import main.ImmutableList;

class ImmutableListTest {

    @Test
    void canGetHead() {
        ImmutableList<Integer> list = ImmutableList.getEmptyImmutableList();
        
        list = list.add(new Integer(1));
        list = list.add(new Integer(2));
        list = list.add(new Integer(3));
        
        assertNotNull(list.getHead());
        assertEquals(list.getHead(), new Integer(3));
    }
    
    @Test
    void canAddElement() {
        ImmutableList<Integer> list = ImmutableList.getEmptyImmutableList();
        
        Integer expectedHead = new Integer(3);
        list = list.add(new Integer(1));
        list = list.add(new Integer(2));
        list = list.add(expectedHead);

        assertEquals(list.getHead(), expectedHead);
    }
    
    @Test
    void canReverseList() {
        ImmutableList<Integer> list = ImmutableList.getEmptyImmutableList();
        
        Integer expectedHead = new Integer(1);
        list = list.add(expectedHead);
        list = list.add(new Integer(2));
        list = list.add(new Integer(3));
        list = list.reverse();

        assertEquals(list.getHead(), expectedHead);
    }

    @Test
    void emptyList() {
        ImmutableList<Integer> list = ImmutableList.getEmptyImmutableList();
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
    }

}
