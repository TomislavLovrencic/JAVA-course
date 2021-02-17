package hr.fer.zemris.java.hw05.shell.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.Helper;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellStatus;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell.Pair;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents cat command. It is used to print out a file in given charset.
 * This command takes 1 argument and its suppose to be one of valid available charsets.
 *
 */
public class CatShellCommand implements ShellCommand ,Helper {
	
	private Charset def = null;
	private StringBuilder sb = new StringBuilder();

	@Override
	public ShellStatus executeCommand(Environment env, String arguments)  {
		
		Pair element1 = SharedUtilityShell.parse(arguments);

		Pair element2 = SharedUtilityShell.parse(arguments.substring(element1.getB() >= arguments.length() ? arguments.length() : element1.getB()+1).trim());


		if(element2.getA() != null) {
			int lastSubstringIndex  = arguments.lastIndexOf(element2.getA());
			
			Pair element3 = SharedUtilityShell.parse(arguments.substring(lastSubstringIndex+element2.getB()).trim());
			
			if(element3.getA() != null) {
				env.writeln("To many arguments for this command!");
				return ShellStatus.CONTINUE;
			}
		}
		
		if(element1.getA() == null) {
			env.writeln("0 arguments given , please add something !");
			return ShellStatus.CONTINUE;
		}
		
		String path = element1.getA();
		
		File file = new File(path);
		if(!file.isFile()) {
			env.writeln("This is not a file , please try something else!");

			return ShellStatus.CONTINUE;
		}
		
		
		
		if(element2.getA() == null) {
			def = Charset.defaultCharset();
		}
		else {
		
			String chrSet = element2.getA();
			
			if(Charset.availableCharsets().containsKey(chrSet)) {
				def = Charset.forName(chrSet);
			}
			else {
				env.writeln("I cant recognize given charset!");

				return ShellStatus.CONTINUE;
			}
			
		}
		
		InputStream is = null;
		try {
			is = Files.newInputStream(Paths.get(path), StandardOpenOption.READ);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] buff = new byte[4086];
		
		SharedUtilityShell.readFiles(env,is, buff, this);
		
		
			
		env.writeln(sb.toString());
		
		return ShellStatus.CONTINUE;
	}
	

	@Override
	public String getCommandName() {
		return "cat";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add(" This command opens given file and writes its content to console!");

		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}


	@Override
	public void after(byte[] buff,int k,Environment env) {
	
		CharBuffer chr = def.decode(ByteBuffer.wrap(buff));
		sb.append(chr);
		
	}

	@Override
	public void before(byte[] buff) {
		return;
	}

}
