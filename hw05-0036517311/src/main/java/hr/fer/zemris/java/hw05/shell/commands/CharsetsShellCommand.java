package hr.fer.zemris.java.hw05.shell.commands;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell.Pair;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents a charsets command.It is used to show all available charsets in java. 
 * This command takes no arguments.
 */
public class CharsetsShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Pair element1 = SharedUtilityShell.parse(arguments);
		
		if(element1.getA() != null) {
			env.writeln("This command isnt suppose to take arguments!");

		}
		else {
			SortedMap<String,Charset> list = Charset.availableCharsets();
			
			list.forEach((a,b) -> {
				env.writeln(a);
			});
		}
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "charsets";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("This command is used  to get all available charsets");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

}
