package hr.fer.zemris.java.hw05.shell.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellStatus;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell.Pair;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents a mkdir command. It takes one argument which is a directory name.
 * It is used to create a directory in working directory or in specific path if given.
 */
public class MkdirShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Pair element1 = SharedUtilityShell.parse(arguments);
		String path = element1.getA();
		
		if(element1.getA() == null) {
			env.writeln("zero arguments given , please add something !");

			return ShellStatus.CONTINUE;
		}
		
		Pair element2 = SharedUtilityShell.parse(arguments.substring(element1.getB() >= arguments.length() ? arguments.length() : element1.getB()+1).trim());
		
		if(element2.getA() != null) {
			env.writeln("This command takes only one argument , name of the directory!");

			return ShellStatus.CONTINUE;
		}
		try {
			Files.createDirectories(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "mkdir";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("This command is used  to create a directory");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

}
