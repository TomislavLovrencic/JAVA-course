package hr.fer.oprpp1.hw04.db;

/**
 * @author Tomislav Lovrencic
 * 
 * This is a functional interface. It is used to get values from {@link StudentRecord}.
 *
 */
public interface IFieldValueGetter {

	/**
	 * This method is used to get String value of this given FieldValuegetter.
	 * @param record
	 * @return String
	 */
	public String get(StudentRecord record);
}
