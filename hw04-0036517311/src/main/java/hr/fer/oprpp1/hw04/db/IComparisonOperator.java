package hr.fer.oprpp1.hw04.db;

/**
 * @author Tomislav Lovrencic
 * 
 * This is an functional interface used for comparing values in database.
 *
 */
public interface IComparisonOperator {
	
	/**
	 * This method is given 2 strings and then does an appropriate functionality based on {@link ComparisonOperators}.
	 * @param value1
	 * @param value2
	 * @return True if value1 and value2 satisfy this {@link ComparisonOperators}.
	 */
	public boolean satisfied(String value1, String value2);

}
