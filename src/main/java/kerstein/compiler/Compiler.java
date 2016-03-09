package kerstein.compiler;

import java.io.BufferedReader;
import java.io.IOException;

public class Compiler {

	private BufferedReader reader;
	private StringBuilder builder;

	public Compiler(BufferedReader reader) {
		this.reader = reader;
		this. builder = new StringBuilder();

	}

	public String compile() throws IOException {

		String line;
		while ((line = reader.readLine()) != null) {
			String[] lineArray = new String[2];
			lineArray = line.split(" ");
			switch (lineArray[0]) {
			case "LD":
				builder.append("0");
				String hex1=(Integer.toString(Integer.parseInt(lineArray[1]), 16).toUpperCase());
				if(hex1.length()==1){
					builder.append("0"+hex1);
				}
				else{
					builder.append(hex1);
				}
				break;
			case "ST":
				builder.append("1");
				String hex2=(Integer.toString(Integer.parseInt(lineArray[1]), 16).toUpperCase());
				if(hex2.length()==1){
					builder.append("0"+hex2);
				}
				else{
					builder.append(hex2);
				}
				break;
			case "SWP":
				builder.append("2");
				break;
			case "ADD":
				builder.append("3");
				break;
			case "INC":
				builder.append("4");
				break;
			case "DEC":
				builder.append("5");
				break;
			case "BZ":
				builder.append("6");
				String hex3=(Integer.toString(Integer.parseInt(lineArray[1]), 16).toUpperCase());
				if(hex3.length()==1){
					builder.append("0"+hex3);
				}
				else{
					builder.append(hex3);
				}				
				break;
			case "BR":
				builder.append("7");
				String hex=(Integer.toString(Integer.parseInt(lineArray[1]), 16).toUpperCase());
				if(hex.length()==1){
					builder.append("0"+hex);
				}
				else{
					builder.append(hex);
				}				
				break;
			case "STP":
				builder.append("8");
				break;
			case "DATA":
				builder.append(lineArray[1]);
				break;
			}
		}
	
		return builder.toString();
	}
}
