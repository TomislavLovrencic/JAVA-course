package hr.fer.zemris.java.hw05.shell.commands;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hr.fer.zemris.java.hw05.shell.Environment;
import hr.fer.zemris.java.hw05.shell.Helper;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell;
import hr.fer.zemris.java.hw05.shell.SharedUtilityShell.Pair;
import hr.fer.zemris.java.hw05.shell.ShellCommand;
import hr.fer.zemris.java.hw05.shell.ShellStatus;

/**
 * @author Tomislav Lovrencic
 * 
 * This class represents a copy command. It takes 2 arguments(a file to copy and a location).
 * It is used for copying a file into another file or into some directory. 
 */
public class CopyShellCommand implements ShellCommand ,Helper{
	OutputStream os = null;

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		
		Pair element1 = SharedUtilityShell.parse(arguments);
		Pair element2 = SharedUtilityShell.parse(arguments.substring(element1.getB() >= arguments.length() ? arguments.length() : element1.getB()+1).trim());
		
		if(element1.getA() == null || element2.getA() == null) {
			env.writeln("This command requires 2 arguments");

			return ShellStatus.CONTINUE;
		}
		
		if(element2.getA() != null) {
			int lastSubstringIndex  = arguments.lastIndexOf(element2.getA());
			
			Pair element3 = SharedUtilityShell.parse(arguments.substring(lastSubstringIndex+element2.getB()).trim());
			
			if(element3.getA() != null) {
				env.writeln("To many arguments for this command!");
				return ShellStatus.CONTINUE;
			}
		}
	
		String path1 = element1.getA();
		String path2 = element2.getA();
		File file1 = new File(path1);
		File file2 = new File(path2);
		
		if(!file1.isFile()) {
			env.writeln("First path needs to be file");

			return ShellStatus.CONTINUE;
		}
		
		if(file2.exists()) {
			
			if(file2.isDirectory()){
				
				File newFile = new File(path2.toString()+File.separator+file1.getName());
             
                try {
					newFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
                path2 = newFile.getAbsolutePath();
			}
            else if(file2.isFile()) {
				env.writeln("Do u want to overwrite this file : "+file2.getName()+ " ? (yes/no)");

				String answer = "";
				answer = env.readLine();

				if(answer.trim().equals("no")) {
					return ShellStatus.CONTINUE;
				}
	
			}
			InputStream is = null;
			try {
				os = Files.newOutputStream(Paths.get(path2), StandardOpenOption.TRUNCATE_EXISTING);
				is = Files.newInputStream(Paths.get(path1), StandardOpenOption.READ);
			} catch (IOException e) {
				e.printStackTrace();
			}
			byte[] buff = new byte[4096];
			
			SharedUtilityShell.readFiles(env, is, buff, this);
   
            return ShellStatus.CONTINUE;
				
		}
		env.writeln("Second file/directory does not exist!");		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "copy";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("This command is used  to copy a file into another file");
		list.add("The copy command expects two arguments: source file name and destination file name");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

	@Override
	public void after(byte[] buff,int k,Environment env) {
		try {
			os.write(buff, 0, k);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void before(byte[] buff) {
		return;
	}

}
