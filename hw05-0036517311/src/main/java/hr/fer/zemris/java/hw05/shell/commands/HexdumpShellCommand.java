package hr.fer.zemris.java.hw05.shell.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.oprpp1.hw05.crypto.Util;
import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.Helper;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell.Pair;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellIOException;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

/**
 * @author Tomislav Lovrencic
 *
 * This class represents hexdump command. It takes one argument which is a file name.
 * It is used to show every char of file in bytes.
 *
 */
public class HexdumpShellCommand implements ShellCommand, Helper {
	
	private StringBuilder sb = new StringBuilder();
	private StringBuilder input = new StringBuilder();
	private int a = 0x00000000;



	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Pair element1 = SharedUtilityShell.parse(arguments);
		
		if(element1.getA() == null) {
			env.writeln("zero arguments given , please add something !");

			return ShellStatus.CONTINUE;
		}
		
		Pair element2 = SharedUtilityShell.parse(arguments.substring(element1.getB() >= arguments.length() ? arguments.length() : element1.getB()+1).trim());
		
		if(element2.getA() != null) {
			env.writeln("This command only takes one argument!");

			return ShellStatus.CONTINUE;
		}
		
		File file = new File(element1.getA());
		
		if(!file.isFile()) {
			env.writeln("This argument isnt a file!");

			return ShellStatus.CONTINUE;
		}
	
		InputStream is = null;
		
		try {
			
			is = Files.newInputStream(Paths.get(element1.getA()), StandardOpenOption.READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		byte[] buff = new byte[16];
		
		SharedUtilityShell.readFiles(env, is, buff, this);
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "hexdump";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("The hexdump command expects a single argument");
		list.add("The hexdump command can only work with Files");
		list.add("Produces hex-output of the given file");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

	@Override
	public void after(byte[] buff,int k,Environment env) {
		String b = Util.bytetohex(buff);
		for(int i=0;i<b.length();i++) {
			String help = (String) b.subSequence(i, i+2);
			
			i+=1;
			if(i == 15 ) {
				if(i < k*2) {
					sb.append(help+"|");
				}
				else {
					sb.append("   ");
				}
				
			}
			if(i < k*2) {
				sb.append(help+" ");
			}
			else {
				sb.append("   ");
			}
			
		}
		sb.append("| ");
		for(int i=0;i<k;i++) {
			
			if(buff[i] < 32 || buff[i] > 127) {
				input.append('.');
			}
			else {
				input.append((char) buff[i]);
			}
			
		}
		sb.append(input);
		a+=16;
	
		try {
			env.writeln(sb.toString());
		} catch (ShellIOException e) {
			e.printStackTrace();
		}
		sb.setLength(0);
		input.setLength(0);
	}

	@Override
	public void before(byte[] buff) {
		sb.append(String.format("%08X", a)+": ");
	}

}
