package hr.fer.zemris.java.hw05.shell;

/**
 * @author Tomislav Lovrencic
 *
 * This is an interface used in class SharedUtilityShell.
 */
public interface Helper {

	/** 
	 * This method is used for working in files after reading it.
	 * @param buff
	 * @param k
	 * @param env
	 */
	public void after(byte[] buff,int k,Environment env);

	/**
	 * This method is used for working in files before reading it.
	 * @param buff
	 */
	public void before(byte[] buff);

}
