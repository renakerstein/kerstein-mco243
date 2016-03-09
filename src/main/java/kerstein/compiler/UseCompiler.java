package kerstein.compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UseCompiler {

	public static void main(String[] args) throws IOException {

	
			BufferedReader reader;
			try {
				reader = new BufferedReader( new FileReader("assemblyLanguage.txt"));
				Compiler compiler=new Compiler(reader);
				System.out.println(compiler.compile().toString());
			} catch (FileNotFoundException e) {
				
			}
			

	}

}
