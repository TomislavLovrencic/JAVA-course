package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FieldValueGetterTester {

	StudentRecord record;
	
	@BeforeEach
    public void setUp() throws Exception {
        record = new StudentRecord("0000000001", "Barakuda", "Merko", 4);

    }

    @Test
    public void testFirstName() {
        assertEquals("Merko", FieldValueGetters.FIRST_NAME.get(record));
    }

    @Test
    public void testLastName() {
        assertEquals("Barakuda", FieldValueGetters.LAST_NAME.get(record));
    }

    @Test
    public void testJMBAG() {
        assertEquals("0000000001", FieldValueGetters.JMBAG.get(record));
    }
}
