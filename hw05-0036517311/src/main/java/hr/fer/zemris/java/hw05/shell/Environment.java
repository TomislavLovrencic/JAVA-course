package hr.fer.zemris.java.hw05.shell;

import java.util.SortedMap;

/**
 * @author Tomislav Lovrencic
 * This interface is used to create an environment used by user and commands.
 */
public interface Environment {

	/**
	 * This method is used to read a new line of input in shell.
	 * @return
	 * @throws ShellIOException
	 */
	String readLine() throws ShellIOException;
	
	/**
	 * This method is used to write something to the user without going into new row.
	 * @param text
	 * @throws ShellIOException
	 */
	void write(String text) throws ShellIOException;
	/**
	 * This method is used to write something to the user with going into new row.
	 * @param text
	 * @throws ShellIOException
	 */
	void writeln(String text) throws ShellIOException;
	
	/**
	 * This method is used to show a map of all commands.
	 * @return Unmodifiable sorted map. 
	 */
	SortedMap<String, ShellCommand> commands();
	
	/**
	 * This is a getter for multilines symbol.
	 * @return
	 */
	Character getMultilineSymbol();
	/**
	 * This is a setter for multilines symbol.
	 * @return
	 */
	void setMultilineSymbol(Character symbol);
	/**
	 * This is a getter for prompt symbol.
	 * @return
	 */
	Character getPromptSymbol();
	/**
	 * This is a setter for prompt symbol.
	 * @return
	 */
	void setPromptSymbol(Character symbol);
	/**
	 * This is a getter for morelines symbol.
	 * @return
	 */
	Character getMorelinesSymbol();
	/**
	 * This is a setter for morelines symbol.
	 * @return
	 */
	void setMorelinesSymbol(Character symbol);
}
