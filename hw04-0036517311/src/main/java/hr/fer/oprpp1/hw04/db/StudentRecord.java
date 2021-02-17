package hr.fer.oprpp1.hw04.db;

import java.util.Objects;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents studentRecord. Every student has its unique studentRecord.
 *
 */
public class StudentRecord {

	
	/**
	 *  This is a students jmbag.
	 */
	private String jmbag;
	/**
	 *  This is a students first name .
	 */
	private String firstName;
	/**
	 *  This is a students last name.
	 */
	private String lastName;
	/**
	 *  This is a students final grade.
	 */
	private int finalGrade;
	
	/** This is a basic constructor.
	 * @param jmbag
	 * @param lname
	 * @param fname
	 * @param grade
	 */
	public StudentRecord(String jmbag,String lname,String fname,int grade) {
		this.jmbag = jmbag;
		this.firstName = fname;
		this.lastName = lname;
		this.finalGrade = grade;
	}
	

	/**
	 * This is a getter for jmbag.
	 * @return
	 */
	public String getJmbag() {
		return jmbag;
	}

	/**
	 * This is a setter for jmbag.
	 * @return
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}


	/**
	 * This is a getter for first name.
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This is a setter for first name.
	 * @return
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * This is a getter for last name .
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * This is a setter for last name .
	 * @return
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	/**
	 * This is a getter for final grade.
	 * @return
	 */
	public int getFinalGrade() {
		return finalGrade;
	}

	/**
	 * This is a setter for final grade.
	 * @return
	 */
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}


	@Override
	public int hashCode() {
		return Objects.hash(jmbag);
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof StudentRecord)) return false;
		StudentRecord record = (StudentRecord) obj;
		return this.jmbag.equals(record.jmbag);
	}
	
	
	
	
}
