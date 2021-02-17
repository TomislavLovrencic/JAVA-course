package hr.fer.oprpp1.math;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Vector2DTester {
	
	private static final double DELTA = 1E-7;

	@Test
    public void getX() {
        Vector2D vector = new Vector2D(4, 5);

        assertEquals(4.0, vector.getX(), DELTA);
    }

    @Test
    public void getY() {
        Vector2D vector = new Vector2D(4, 5);

        assertEquals(5.0, vector.getY(), DELTA);
    }

    @Test
    public void add() {

        Vector2D vector = new Vector2D(10.0, 10.0);
        Vector2D translating = new Vector2D(5, -5);

        vector.add(translating);

        assertEquals(15.0, vector.getX(), DELTA);
        assertEquals(5, vector.getY(), DELTA);

        assertEquals(5, translating.getX(), DELTA);
        assertEquals(-5, translating.getY(), DELTA);
    }

    @Test
    public void added() {

        Vector2D vector = new Vector2D(10.0, 10.0);
        Vector2D translating = new Vector2D(5, -5);

        Vector2D translatedVector = vector.added(translating);

        assertEquals(15.0, translatedVector.getX(), DELTA);
        assertEquals(5, translatedVector.getY(), DELTA);

        assertEquals(10.0, vector.getX(), DELTA);
        assertEquals(10, vector.getY(), DELTA);

        assertEquals(5, translating.getX(), DELTA);
        assertEquals(-5, translating.getY(), DELTA);

    }

    @Test
    public void rotate() {
        Vector2D vector = new Vector2D(10, 10);

        vector.rotate(Math.PI / 4);

        assertEquals(0, vector.getX(), DELTA);
        assertEquals(10 * Math.sqrt(2), vector.getY(), DELTA);

    }

    @Test
    public void rotated() {

        Vector2D vector = new Vector2D(10, 10);

        Vector2D rotated = vector.rotated(Math.PI / 4);

        assertEquals(0, rotated.getX(), DELTA);
        assertEquals(10 * Math.sqrt(2), rotated.getY(), DELTA);

        assertEquals(10.0, vector.getX(), DELTA);
        assertEquals(10, vector.getY(), DELTA);

    }

    @Test
    public void scale() {

        Vector2D vector = new Vector2D(1, 2);

        vector.scale(0.5);

        assertEquals(0.5, vector.getX(), DELTA);
        assertEquals(1, vector.getY(), DELTA);
    }

    @Test
    public void scaled() {
        Vector2D vector = new Vector2D(1, 2);

        Vector2D scaled = vector.scaled(0.5);

        assertEquals(0.5, scaled.getX(), DELTA);
        assertEquals(1, scaled.getY(), DELTA);

        assertEquals(1, vector.getX(), DELTA);
        assertEquals(2, vector.getY(), DELTA);


    }

    @Test
    public void copy() {
        Vector2D vector = new Vector2D(3, 4);
        Vector2D copy = vector.copy();

        assertEquals(3, copy.getX(), DELTA);
        assertEquals(4, copy.getY(), DELTA);

        assertEquals(3, vector.getX(), DELTA);
        assertEquals(4, vector.getY(), DELTA);

    }
    
    @Test
    public void testVectorCreate() {
        var vector = new Vector2D(2.2, 10);

        assertEquals(2.2, vector.getX());
        assertEquals(10, vector.getY());
    }

    @Test
    public void testVectorAdd() {
        var vector = new Vector2D(2.2, 10);
        vector.add(new Vector2D(1.1, 100));

        assertTrue(3.3 - Math.abs(vector.getX()) < 1E-8);
        assertTrue(110 - Math.abs(vector.getY()) < 1E-8);
    }

    @Test
    public void testVectorAdded() {
        var vector = new Vector2D(2.2, 10);
        var newVector = vector.added(new Vector2D(1.1, 100));

        assertTrue(2.2 - Math.abs(vector.getX()) < 1E-8);
        assertTrue(10 - Math.abs(vector.getY()) < 1E-8);

        assertTrue(3.3 - Math.abs(newVector.getX()) < 1E-8);
        assertTrue(110 - Math.abs(newVector.getY()) < 1E-8);
    }

    @Test
    public void testVectorRotate() {
        var vector = new Vector2D(2.2, 0);
        vector.rotate(Math.PI / 2);

        assertTrue(Math.abs(vector.getX()) < 1E-8);
        assertTrue(2.2 - Math.abs(vector.getY()) < 1E-8);
    }

    @Test
    public void testVectorRotate2() {
        var vector = new Vector2D(2.2, 2.2);
        vector.rotate(Math.PI);

        assertTrue(2.2 - Math.abs(vector.getX()) < 1E-8);
        assertTrue(2.2 - Math.abs(vector.getY()) < 1E-8);
    }

    @Test
    public void testVectorRotated() {
        var vector = new Vector2D(2.2, 2.2);
        var newVector = vector.rotated(Math.PI);

        assertNotSame(newVector, vector);
        assertTrue(2.2 - Math.abs(newVector.getX()) < 1E-8);
        assertTrue(2.2 - Math.abs(newVector.getY()) < 1E-8);
    }

    @Test
    public void testVectorCopy() {
        var vector = new Vector2D(2.2, 2.2);
        var newVector = vector.copy();

        assertNotSame(newVector, vector);
    }

    @Test
    public void testVectorScale() {
        var vector = new Vector2D(2.2, 2.2);
        vector.scale(2);

        assertTrue(4.4 - Math.abs(vector.getX()) < 1E-8);
        assertTrue(4.4 - Math.abs(vector.getY()) < 1E-8);
    }

    @Test
    public void testVectorScaled() {
        var vector = new Vector2D(2.2, 2.2);
        var newVector = vector.scaled(2);

        assertNotSame(newVector, vector);
        assertTrue(4.4 - Math.abs(newVector.getX()) < 1E-8);
        assertTrue(4.4 - Math.abs(newVector.getY()) < 1E-8);
    }


}
