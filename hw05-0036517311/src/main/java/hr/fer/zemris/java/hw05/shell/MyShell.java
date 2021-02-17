package hr.fer.zemris.java.hw05.shell;



/**
 * @author Tomislav Lovrencic
 *
 * This class represents a working shell in terminal.
 */
public class MyShell {

	
	/**
	 * This is a main method of this class.
	 * @param args
	 * @throws ShellIOException
	 */
	public static void main(String[] args) throws ShellIOException {
		
		System.out.println("Welcome to MyShell v 1.0");

		
		Environment env = new ShellEnvironment();
		env.setPromptSymbol('>');
		env.setMorelinesSymbol('\\');
		env.setMultilineSymbol('|');
	
		
		while(true) {
	
			String l = env.readLine();		
						
			String comm = l.split(" ")[0];
			
			String arguments = l.substring(comm.length()).trim();

			ShellCommand command = env.commands().get(comm);
			ShellStatus status = null;
			
			if(command == null) {
				env.writeln("This command is not valid!");
			}
			
			else {
				status = command.executeCommand(env, arguments);
				
				if(status.equals(ShellStatus.TERMINATE)) break;

			}
			
			
		}
		
	}
}
