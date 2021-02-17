package hr.fer.zemris.java.hw05.shell;

import java.util.List;

/**
 * @author Tomislav Lovrencic
 *
 * This interface is used as a template of commands for every command that is 
 * processed in Shell.
 */
public interface ShellCommand {

	
	/**
	 * This method is used to execute this command.
	 * @param env Environment of the command and user.
	 * @param arguments Input that user gave.
	 * @return
	 */
	ShellStatus executeCommand(Environment env, String arguments);
	
	/**
	 * This method return command name. 
	 * @return
	 */
	String getCommandName();
	
	/**
	 * This method returns command description.
	 * @return Unmodifiable List<String> used to shove description of command. 
	 */
	List<String> getCommandDescription();
}
