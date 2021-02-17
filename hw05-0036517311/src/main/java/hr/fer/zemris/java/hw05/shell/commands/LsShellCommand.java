package hr.fer.zemris.java.hw05.shell.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
 * This is a class that represents a ls command. It is used to show all files inside 
 * an given directory. It prints all files with given attributes such as time of creation,
 * is the file is executable , writable and  readable.
 */
public class LsShellCommand implements ShellCommand {

	@Override
	public ShellStatus executeCommand(Environment env, String arguments) {
		Pair element1 = SharedUtilityShell.parse(arguments);
		Pair element2 = SharedUtilityShell.parse(arguments.substring(element1.getB() >= arguments.length() ? arguments.length() : element1.getB()+1).trim());
		
		if(element1.getA() == null) {
			env.writeln("zero arguments given , please add something !");

			return ShellStatus.CONTINUE;
		}
		
		if(element2.getA() != null) {
			env.writeln("This command takes only one argument!");

			return ShellStatus.CONTINUE;
		}
		
		String path = element1.getA();
		File staza = new File(path);
		
				
		if(!staza.isDirectory()) {
			env.writeln("This is not directory!");

		}
		else {
			
			for(File elem : staza.listFiles()) {
				
				StringBuilder elemInfo = new StringBuilder("----");
				
				if(elem.isDirectory()) {
					elemInfo.replace(0, 1, "d");
				}
				if(Files.isReadable(elem.toPath())) {
					elemInfo.replace(1, 2, "w");
				}
				if(Files.isWritable(elem.toPath())) {
					elemInfo.replace(2, 3, "r");
				}
				if(Files.isExecutable(elem.toPath())) {
					elemInfo.replace(3, 4, "e ");
				}
				
				elemInfo.append(String.format("%10d",elem.length()));
				elemInfo.append(" ");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				BasicFileAttributeView faView = Files.getFileAttributeView(
				Paths.get(elem.getAbsolutePath()), BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS
				);
				BasicFileAttributes attributes = null;
				try {
					attributes = faView.readAttributes();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				FileTime fileTime = attributes.creationTime();
				String formattedDateTime = sdf.format(new Date(fileTime.toMillis()));
				elemInfo.append(formattedDateTime+" ");
				elemInfo.append(elem.getName());
				env.writeln(elemInfo.toString());

			}
		}
		
		return ShellStatus.CONTINUE;
	}

	@Override
	public String getCommandName() {
		return "ls";
	}

	@Override
	public List<String> getCommandDescription() {
		List<String> list = new ArrayList<String>();
		list.add("This command is used  to print all elemets from one directory");
		list.add("It does not work recursive so it prints only childs from current directory");
		
		List<String> unmod = Collections.unmodifiableList(list);
		return unmod;
	}

}
