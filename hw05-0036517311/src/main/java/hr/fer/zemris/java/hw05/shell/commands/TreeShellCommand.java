package hr.fer.zemris.java.hw05.shell.commands;

import java.io.File;
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
 * This class represents a tree command. It takes one argument that has to be a directory.
 * it prints all files inside it with appropriate distance.
 */
public class TreeShellCommand implements ShellCommand{

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Pair element1 = SharedUtilityShell.parse(arguments);
		Pair element2 = SharedUtilityShell.parse(arguments.substring(element1.getB() >= arguments.length() ? arguments.length() : element1.getB()+1).trim());
		
		
		if(element1.getA() == null) {
			env.writeln("zero arguments given , please add something !");
			return ShellStatus.CONTINUE;
		}
		if(element2.getA() != null) {
			env.writeln("Too many arguments for this command!");

			return ShellStatus.CONTINUE;
		}
		
		String path = element1.getA();
		
		if(element1.getA() == null) {
			env.writeln("zero arguments given , please add something !");

			return ShellStatus.CONTINUE;
		}
		
		File staza = new File(path);
		int razina = 0;
		
		if(staza.isDirectory()) {
			ispisiStablo(staza,razina);
		}
		else {
			env.writeln("It needs to be a directory!");

		}
		return ShellStatus.CONTINUE;
	}

	private void ispisiStablo(File staza, int razina) {
		if(staza.listFiles() != null) {
			for(File elem : staza.listFiles()) {
				System.out.println(" ".repeat(razina*2) + elem.getName());
				ispisiStablo(elem, razina+1);
			}
		}
	
	}

	@Override
	public String getCommandName() {
		return "tree";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("This command is used  to print a structure of a directory");
		list.add("each directory level shifts output two charatcers to the right");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

}
