package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class QueryParserTester {

	
	  @Test
	    public void directQuery() {
	        QueryParser qp1 = new QueryParser("query jmbag =\"0123456789\" ");
	        assertTrue(qp1.isDirectQuery());
	        assertEquals("0123456789", qp1.getQueriedJMBAG());
	        assertEquals(1, qp1.getQuery().size());
	    }

	    @Test
	    public void complexQuery() {
	        QueryParser qp2 = new QueryParser("query jmbag=\"0123456789\" AND lastName>\"J\"");
	        assertFalse(qp2.isDirectQuery());
	        assertEquals(2, qp2.getQuery().size());
	    }
}
