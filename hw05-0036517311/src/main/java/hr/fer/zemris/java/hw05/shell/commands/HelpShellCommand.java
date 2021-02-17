package hr.fer.zemris.java.hw05.shell.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents help command. It can take 1 arguments. if there is no argument
 * this class shows all the commands u can use. If the argument is a command name , 
 * it shows appropriate command and its description.
 */
public class HelpShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		String[] lista = arguments.trim().split(" ");
		String element1 = lista[0];
		
		if(lista.length != 1) {
			env.writeln("Wrong number of arguments!");
			return ShellStatus.CONTINUE;
		}
		
		if(element1.equals("")) {
			env.commands().forEach( (s,c) -> {
				env.writeln(s);
			});
		}
		
		else {
			ShellCommand comm = env.commands().get(element1);
			
			if(comm == null) {
				env.writeln("This command does not exist!");
				return ShellStatus.CONTINUE;
			}
			
			env.writeln(comm.getCommandName());

			comm.getCommandDescription().forEach((s) -> {
				env.writeln(s);
			});
		}
	
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "help";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("This command is used for getting information about all commands if used without argument");
		list.add("If u use this command with specific command u get information about that command!");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

}
