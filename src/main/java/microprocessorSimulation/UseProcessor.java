package microprocessorSimulation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class UseProcessor {

	public static void main(String[] args) {
		Memory memory;
		Microprocessor processor;

		try {
			BufferedReader reader = new BufferedReader(
					new FileReader("mach.in"));

			String line;
			while ((line = reader.readLine()) != null) {
				memory = new Memory(line);
				processor = new Microprocessor(memory);

				for (char m : memory.getMemory()) {
					System.out.print(m);
				}
				System.out.println("\n");

			}
			reader.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}

	}
}
