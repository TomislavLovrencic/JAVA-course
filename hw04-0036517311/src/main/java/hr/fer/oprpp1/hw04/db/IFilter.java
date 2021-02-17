package hr.fer.oprpp1.hw04.db;


/**
 * @author Tomislav Lovrencic
 * This interface represents a Filter used for accepting a record into database.
 */
public interface IFilter {   
	
	/**
	 *  This method is used to accept a record if it passed this filter.
	 * @param record
	 * @return true if this record is accepted by QueryFilter query,false otherwise.
	 */
	public boolean accepts(StudentRecord record); 
	
}