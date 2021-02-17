package hr.fer.zemris.java.hw05.shell;

import java.util.Collections;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

import hr.fer.zemris.java.hw05.shell.commands.CatShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.CharsetsShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.CopyShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.ExitShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.HelpShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.HexdumpShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.LsShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.MkdirShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.SymbolShellCommand;
import hr.fer.zemris.java.hw05.shell.commands.TreeShellCommand;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents is used as a communication between a command and its user in a shell.
 *
 */
public class ShellEnvironment implements Environment {
	
	/**
	 *  Scanner used to get input from user.
	 */
	public Scanner sc;
	
	
	/**
	 *  Symbol for going into more rows.
	 */
	public char multiLineSymbol;
	
	/**
	 * Symbol that is shown to user before giving an input into shell.
	 */
	public char promptSymbol;

	/**
	 * Symbol used if an user enters more rows of input.
	 */
	public char moreLinesSymbol;
	
	/**
	 * This is a map of commands.
	 */
	private SortedMap<String, ShellCommand> commands;

	
	public  ShellEnvironment() {
		sc = new Scanner(System.in);
		
		commands = new TreeMap<String,ShellCommand>();
		commands.put("exit", new ExitShellCommand());
		commands.put("ls", new LsShellCommand());
		commands.put("cat", new CatShellCommand());
		commands.put("charsets", new CharsetsShellCommand());
		commands.put("copy", new CopyShellCommand());
		commands.put("help", new HelpShellCommand());
		commands.put("hexdump", new HexdumpShellCommand());
		commands.put("mkdir", new MkdirShellCommand());
		commands.put("tree", new TreeShellCommand());
		commands.put("symbol", new SymbolShellCommand());
	}
	
	

	@Override
	public String readLine()  throws ShellIOException{
		
		StringBuilder sb = new StringBuilder();
		boolean mul = false;
		char sl = getPromptSymbol();
		while(true) {
			write(getPromptSymbol()+" ");
			String line = sc.nextLine();
						
			String[] l = line.split(" ");
			for(int i=0;i<l.length;i++) {
				if( l[i].length() == 1 && l[i].charAt(0) == getMorelinesSymbol() && i == l.length -1 ) {
					mul = true;
				}
				else {
					mul = false;
					sb.append(l[i]+" ");
				}
			}
			if(mul == false) {
				break;
			}
			
			setPromptSymbol(getMultilineSymbol());
		}
		
		setPromptSymbol(sl);
		
		return sb.toString();
		
	}

	@Override
	public void write(String text) throws ShellIOException {
		System.out.print(text);
		
	}

	@Override
	public void writeln(String text) throws ShellIOException {
		System.out.println(text);
	}

	@Override
	public SortedMap<String, ShellCommand> commands() {
	
		SortedMap<String,ShellCommand> mapa = Collections.unmodifiableSortedMap(commands);
		return mapa;
	}

	@Override
	public Character getMultilineSymbol() {
		return this.multiLineSymbol;
	}

	@Override
	public void setMultilineSymbol(Character symbol) {
		this.multiLineSymbol = symbol;
	}

	@Override
	public Character getPromptSymbol() {
		return this.promptSymbol;
	}

	@Override
	public void setPromptSymbol(Character symbol) {
		this.promptSymbol = symbol;

	}

	@Override
	public Character getMorelinesSymbol() {
		return this.moreLinesSymbol;
	}

	@Override
	public void setMorelinesSymbol(Character symbol) {
		this.moreLinesSymbol = symbol;
	}

}
