package hr.fer.oprpp1.hw04.db;

/**
 * @author Tomislav Lovrencic
 * 
 * This class is used to store instances of {@link IComparisonOperator} interface and use them as operators in queries.
 *
 */
public class ComparisonOperators {
	
	
	
	 /**
	 *  Operator less 
	 */
	public static final IComparisonOperator LESS = (s1,s2) -> s1.compareTo(s2) < 0;
	 
	/**
	 *  Operator less or equals
	 */
	 public static final IComparisonOperator LESS_OR_EQUALS = (s1,s2) -> s1.compareTo(s2) <= 0;
	 
	 
	 /**
	 * Operator greater
	 */
	public static final IComparisonOperator GREATER = (s1,s2) -> s1.compareTo(s2) > 0;
	 
	 /**
	 * Operator greater or equals
	 */
	public static final IComparisonOperator GREATER_OR_EQUALS = (s1,s2) -> s1.compareTo(s2) >= 0;
	 
	 /**
	 *  Operator equals
	 */
	public static final IComparisonOperator EQUALS = (s1,s2) -> s1.equals(s2);
	 
	 /**
	 * Operator not equals
	 */
	public static final IComparisonOperator NOT_EQUALS = (s1,s2) -> !s1.equals(s2);
	 
	 /**
	 * Operator like
	 */
	public static final IComparisonOperator LIKE =  new IComparisonOperator() {

		@Override
		public boolean satisfied(String value1, String value2) {
			boolean first = false;
			if(value2.equals("*")) return true;
			
			if(value2.charAt(0) == '*') first = true;
			
			if(value1.length() < value2.length()-1) return false;
			
			String[] elems = value2.split("\\*");
			if(elems.length == 2) {
				if(value1.startsWith(elems[0]) && value1.endsWith(elems[1])) {
					return true;
				}
				return false;
			}
			else if(elems.length > 2){
				throw new IllegalArgumentException("Wildcard has been used more than once!");
			}
			
			if(first) {
				if(value1.endsWith(elems[0])) return true;
				return false;
			}
			if(value1.startsWith(elems[0])) return true;
			return false;
		}
		 
	 };

}
