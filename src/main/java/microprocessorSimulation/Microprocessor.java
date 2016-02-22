package microprocessorSimulation;

public class Microprocessor {

	private char[] memory;
	private char accumulatorA;
	private char accumulatorB;
	private int counter;
	private int location;
	private StringBuilder builder;
	private int num = 0;

	public Microprocessor(Memory m) {
		this.accumulatorA = '0';
		this.accumulatorB = '0';
		this.counter = 0;
		this.memory = m.getMemory();
		processMemory();

	}

	public void processMemory() {
		while (memory[counter] != '8') {
			switch (memory[counter]) {
			case '0':
				load();
				break;

			case '1':
				store();
				break;

			case '2':
				swap();
				break;

			case '3':
				add();
				break;

			case '4':
				increment();
				break;

			case '5':
				decrement();
				break;

			case '6':
				bz();
				break;
			case '7':
				br();
				break;
			case '8':
				return;
			}// close switch
			counter++;

		}// close while

	}

	private void load() {
		builder = new StringBuilder();
		builder.append(memory[counter + 1]);
		builder.append(memory[counter + 2]);
		location = Integer.parseInt(builder.toString(), 16);
		accumulatorA = memory[location];
		counter += 2;
	}

	private void store() {
		builder = new StringBuilder();
		builder.append(memory[counter + 1]);
		builder.append(memory[counter + 2]);
		location = Integer.parseInt(builder.toString(), 16);
		memory[location] = accumulatorA;
		counter += 2;
	}

	private void swap() {
		char temp = accumulatorA;
		accumulatorA = accumulatorB;
		accumulatorB = temp;
	}

	private void add() {
		// convert to decimal
		int decimalA = Integer.parseInt(String.valueOf(accumulatorA), 16);
		int decimalB = Integer.parseInt(String.valueOf(accumulatorB), 16);
		int total = decimalA + decimalB;
		// convert to hex
		String hex = Integer.toString(total, 16).toUpperCase();
		if (hex.length() == 2) {
			accumulatorB = hex.charAt(0);
			accumulatorA = hex.charAt(1);
		} else {
			accumulatorB = '0';
			accumulatorA = hex.charAt(0);
		}
	}

	private void increment() {
		if (accumulatorA == 'F') {
			accumulatorA = '0';
		} else {
			int dec1 = Integer.parseInt(String.valueOf(accumulatorA), 16);
			dec1++;
			String hex1 = Integer.toString(dec1, 16).toUpperCase();
			accumulatorA = hex1.charAt(0);
		}

	}

	private void decrement() {
		if (accumulatorA == '0') {
			accumulatorA = 'F';
		} else {
			int dec2 = Integer.parseInt(String.valueOf(accumulatorA), 16);
			dec2--;
			String hex = Integer.toHexString(dec2).toUpperCase();
			accumulatorA = hex.charAt(0);
		}

	}

	private void bz() {

		if (accumulatorA == '0') {
			builder = new StringBuilder();
			builder.append(memory[counter + 1]);
			builder.append(memory[counter + 2]);
			location = Integer.parseInt(builder.toString(), 16);
			counter = location;
		} else {
			counter += 2;
		}
	}

	private void br() {
		builder = new StringBuilder();
		builder.append(memory[counter + 1]);
		builder.append(memory[counter + 2]);
		location = Integer.parseInt(builder.toString(), 16);
		counter = location;

		/*
		 * if (counter < 16) { for (char m : memory) {
		 * 
		 * System.out.print(m);
		 * 
		 * num++;
		 * 
		 * } }*
		 */
	}

}
