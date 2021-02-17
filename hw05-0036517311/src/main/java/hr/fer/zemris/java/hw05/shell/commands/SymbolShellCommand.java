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
 * This class represents symbol command. It can take up to 2 arguments. 
 * It is used to work with PROMPT,MULTILINE and MORELINES symbol.
 */
public class SymbolShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		String[] lista = arguments.trim().split(" ");

		String element1 = lista[0];
		
		if(lista.length > 2) {
			env.writeln("Too many arguments for this command!");
			return ShellStatus.CONTINUE;
		}
		
		
		if(element1.equals("")) {
			env.writeln("zero arguments given , please add something !");

			return ShellStatus.CONTINUE;
		}
				
		if(lista.length == 1) {
			switch(element1) {
				
				case("PROMPT"):
					System.out.println("Symbol for PROMPT is "+"'"+env.getPromptSymbol()+"'");
					break;
				
				case("MULTILINE"):
					System.out.println("Symbol for MULTILINE is "+"'"+env.getMultilineSymbol()+"'");
					break;
	
				case("MORELINES"):
					System.out.println("Symbol for MORELINES is "+"'"+env.getMorelinesSymbol()+"'");
					break;
				default:
					System.out.println("This is not a valid Symbol!");
			}
			return ShellStatus.CONTINUE;
		}
		
		switch(element1) {
		
			case("PROMPT"):
				System.out.println("Symbol for PROMPT changed from "+ "'" + env.getPromptSymbol() + "'" + " to " + "'" + lista[1] + "'");
				env.setPromptSymbol(lista[1].charAt(0));
				break;
			
			case("MULTILINE"):
				System.out.println("Symbol for MULTILINE changed from "+ "'" + env.getMultilineSymbol() + "'" + " to " + "'" + lista[1] + "'");
				env.setMultilineSymbol(lista[1].charAt(0));
	
				break;
	
			case("MORELINES"):
				System.out.println("Symbol for MORELINES changed from "+ "'" + env.getMorelinesSymbol() + "'" + " to " + "'" + lista[1] + "'");
				env.setMorelinesSymbol(lista[1].charAt(0));
				break;
			default:
				env.writeln("This is not a valid symbol!");
		}
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "symbol";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("This command is used  to check the values of symbols PROMPT,MORELINES,MULTILINES");
		list.add("Also this command can be used to change these symbols");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

}
