package hr.fer.oprpp1.hw04.db;

/**
 * @author Tomislav Lovrencic
 *
 * 	This class is used to create an instance of one conditionalExpression that contains one 
 * {@link IFieldValueGetter},{@link IComparisonOperator} and one String , which all represents one query for database.
 *
 */
public class ConditionalExpression {

	private IFieldValueGetter fieldValueGetter;
	
	private String str;
	
	private IComparisonOperator comparisonOperator;
	
	/**
	 * This is a basic constructor for conditionalExpression.
	 * 
	 * @param fieldGetter
	 * @param str
	 * @param compOperator
	 */
	public ConditionalExpression(IFieldValueGetter fieldGetter,String str, IComparisonOperator compOperator) {
		this.fieldValueGetter = fieldGetter;
		this.str = str;
		this.comparisonOperator = compOperator;
	}

	/**
	 *  Basic getter for {@link IFieldValueGetter}
	 * @return
	 */
	public IFieldValueGetter getFieldValueGetter() {
		return fieldValueGetter;
	}
	
	/**
	 *  Basic setter for {@link IFieldValueGetter}
	 * @return
	 */
	public void setFieldValueGetter(IFieldValueGetter fieldValueGetter) {
		this.fieldValueGetter = fieldValueGetter;
	}

	/** This is a basic getter for str.
	 * @return
	 */
	public String getStr() {
		return str;
	}

	/** This is a basic setter for str.
	 * @param str
	 */
	public void setStr(String str) {
		this.str = str;
	}

	/** This is a getter for ComparisonOperator.
	 * @return
	 */
	public IComparisonOperator getComparisonOperator() {
		return comparisonOperator;
	}

	/** This is a setter for ComparisonOperator.
	 * @param comparisonOperator
	 */
	public void setComparisonOperator(IComparisonOperator comparisonOperator) {
		this.comparisonOperator = comparisonOperator;
	}
	
	
}
