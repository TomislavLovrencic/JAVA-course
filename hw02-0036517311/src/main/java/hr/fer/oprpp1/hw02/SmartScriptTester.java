package hr.fer.oprpp1.hw02;




import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import hr.fer.oprpp1.custom.scripting.nodes.DocumentNode;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParser;
import hr.fer.oprpp1.custom.scripting.parser.SmartScriptParserException;


/**
 * @author Tomislav Lovrencic
 *
 *This class represents a tester for constructed parser.
 *
 */
public class SmartScriptTester { 
  
    /**
     *  This is a main method of the class which takes an argument that is the path to the file we are parsing.
     * 
     * @param args path to file.
     */
    public static void main(String[] args) {
      
        String docBody = null;

        try {
            docBody = new String(
                    Files.readAllBytes(Paths.get(args[0])),
                    StandardCharsets.UTF_8
            );
        } catch (IOException ex) {
            System.out.println("Putanja do datoteke nije ispravna!");
            System.exit(1);
        }

        SmartScriptParser parser = null;
  
        try {
            parser = new SmartScriptParser(docBody);
        } catch (SmartScriptParserException e) {
            System.out.println("Unable to parse document!");
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.out.println("If this line ever executes, you have failed " +
                    "this class!"+e);
            System.exit(1);
        }
        
        

        DocumentNode document = parser.getDocumentNode();
        String originalDocumentBody = document.toString();

        SmartScriptParser parser2 = new SmartScriptParser(originalDocumentBody);
        DocumentNode document2 = parser2.getDocumentNode();
        
        System.out.println(document.equals(document2));
        System.out.println(originalDocumentBody);
        

                
         
    }
    
   
  
    
   
}