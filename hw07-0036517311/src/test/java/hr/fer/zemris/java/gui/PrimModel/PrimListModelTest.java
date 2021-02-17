package hr.fer.zemris.java.gui.PrimModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import hr.fer.zemris.java.gui.prim.PrimListModel;

public class PrimListModelTest {

    @Test
    public void getSize() {
        PrimListModel model = new PrimListModel();
        assertEquals(1, model.getSize());
        model.next();
        assertEquals(2, model.getSize());
    }

    @Test
    public void getElementAt() {
        PrimListModel model = new PrimListModel();
        assertEquals(1L, (long) model.getElementAt(0));
        model.next();
        model.next();
        assertEquals(3L, (long) model.getElementAt(2));

    }

    @Test
    public void next() {
        PrimListModel model = new PrimListModel();
        model.next();
        model.next();
        model.next();
        model.next();
        assertEquals(7L, (long) model.getElementAt(4));
    }
    
    @Test
    public void next1() {
        PrimListModel model = new PrimListModel();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        assertEquals(17L, (long) model.getElementAt(7));
    }
    
    @Test
    public void next2() {
        PrimListModel model = new PrimListModel();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        assertEquals(29L, (long) model.getElementAt(10));
    }
    
    @Test
    public void next3() {
        PrimListModel model = new PrimListModel();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        model.next();
        assertEquals(41L, (long) model.getElementAt(13));
    }
}