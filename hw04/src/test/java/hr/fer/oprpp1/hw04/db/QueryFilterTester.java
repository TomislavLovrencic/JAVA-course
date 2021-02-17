package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class QueryFilterTester {

	
	
	@Test
	public void filter() {
		QueryParser parser = new QueryParser("query jmbag = \"0000000003\" AND lastName LIKE \"B*\"");
		QueryFilter filter = new QueryFilter(parser.getQuery());
		
		StudentRecord record = new StudentRecord("0000000003","Bosniæ","Andrea",4);
		
		assertTrue(filter.accepts(record));
		
		
	}
	
	@Test
	public void filter2() {
		QueryParser parser = new QueryParser("query jmbag = \"0000000003\" AND lastName LIKE \"B*\"");
		QueryFilter filter = new QueryFilter(parser.getQuery());
		
		StudentRecord record = new StudentRecord("0000000003","Gosniæ","Andrea",4);
		
		assertFalse(filter.accepts(record));
		
		
	}
}
