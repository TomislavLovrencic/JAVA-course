package hr.fer.oprpp1.hw04.db;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents instances of {@link IFieldValueGetter}.
 */
public class FieldValueGetters {

	
	/**
	 *  This is a getter for students first name.
	 */
	public static final IFieldValueGetter FIRST_NAME = StudentRecord::getFirstName;
	 
	/**
	 *  This is a getter for students last name.
	 */
	 public static final IFieldValueGetter LAST_NAME = StudentRecord::getLastName;
	 
	 /**
	  *  This is a getter for students jmbag.
	 */
	 public static final IFieldValueGetter JMBAG = StudentRecord::getJmbag;
}
