package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConditionalExpressionTester {

	 ConditionalExpression expr;
	    StudentRecord record;

	    @BeforeEach
	    public void setUp() throws Exception {

	        expr = new ConditionalExpression(
	                FieldValueGetters.LAST_NAME,
	                "Bat*",
	                ComparisonOperators.LIKE
	        );

	        record = new StudentRecord("0036491234", "Baturina", "Mate", 5);
	    }

	    @Test
	    public void testSatisified() {
	        assertTrue(expr.getComparisonOperator().satisfied(
	                expr.getFieldValueGetter().get(record),
	                expr.getStr()
	        ));
	    }
	
}
