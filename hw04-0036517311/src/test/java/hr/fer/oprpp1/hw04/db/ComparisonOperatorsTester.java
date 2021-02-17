package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ComparisonOperatorsTester {

	@Test
    public void lessTrueTest() {
        assertTrue(ComparisonOperators.LESS.satisfied("Ana", "Jasna"));
    }

    @Test
    public void lessFalseTest() {
        assertFalse(ComparisonOperators.LESS.satisfied("Jure", "Antica"));
    }

    @Test
    public void lessOrEqualsTrueTest() {
        assertTrue(ComparisonOperators.LESS_OR_EQUALS.satisfied("Ana", "Ana"));
    }

    @Test
    public void lessOrEqualsFalseTest() {
        assertFalse(ComparisonOperators.LESS_OR_EQUALS.satisfied("Jure", "Antica"));
    }

    @Test
    public void greaterTrueTest() {
        assertTrue(ComparisonOperators.GREATER.satisfied("Stipica", "Milica"));
    }

    @Test
    public void greaterFalseTest() {
        assertFalse(ComparisonOperators.GREATER.satisfied("Stipica", "Suzana"));
    }

    @Test
    public void greaterEqualsTrueTest() {
        assertTrue(ComparisonOperators.GREATER_OR_EQUALS.satisfied("Ana", "Ana"));
    }

    @Test
    public void greaterEqualsFalseTest() {
        assertFalse(ComparisonOperators.GREATER_OR_EQUALS.satisfied("Jure", "Špiro"));
    }

    @Test
    public void equalsTrueTest() {
        assertTrue(ComparisonOperators.EQUALS.satisfied("Ana", "Ana"));
    }

    @Test
    public void equalsFalseTest() {
        assertFalse(ComparisonOperators.EQUALS.satisfied("Jure", "Antica"));
    }

    @Test
    public void notEqualsTrueTest() {
        assertTrue(ComparisonOperators.NOT_EQUALS.satisfied("Ana", "Jasna"));
    }

    @Test
    public void notEqualsFalseTest() {
        assertFalse(ComparisonOperators.NOT_EQUALS.satisfied("Jure", "Jure"));
    }

    @Test
    public void likeTest() {
        assertFalse(ComparisonOperators.LIKE.satisfied("Zagreb", "Aba*"));
        assertFalse(ComparisonOperators.LIKE.satisfied("AAA", "AA*AA"));
        assertTrue(ComparisonOperators.LIKE.satisfied("AAAA", "AA*AA"));
    }
}
