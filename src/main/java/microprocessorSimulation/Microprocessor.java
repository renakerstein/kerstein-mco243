package microprocessorSimulation;

public class Microprocessor {

	private char[] memory;
	private char accumulatorA;
	private char accumulatorB;
	private int counter;
	private int address1;
	private int address2;
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
		// address1 = Integer.parseInt(String.valueOf(memory[position + 1]), 16)
		// * 16;
		// address2 = Integer.parseInt(String.valueOf(memory[position + 2]),
		// 16);
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
		// address1 = Integer.parseInt(String.valueOf(memory[counter+ 1]), 16) *
		// 16;
		// address2 = Integer.parseInt(String.valueOf(memory[counter+ 2]), 16);
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
		String sTotal = Integer.toHexString(total).toUpperCase();
		if (sTotal.length() == 2) {
			accumulatorB = sTotal.charAt(0);
			accumulatorA = sTotal.charAt(1);
		} else {
			accumulatorB = '0';
			accumulatorA = sTotal.charAt(0);
		}
	}

	private void increment() {
		if (accumulatorA == 'F') {
			accumulatorA = '0';
		} else {
			int dec = Integer.parseInt(String.valueOf(accumulatorA), 16);
			dec++;
			String hex = Integer.toHexString(dec).toUpperCase();
			accumulatorA = hex.charAt(0);
		}

	}

	private void decrement() {
		if (accumulatorA == '0') {
			accumulatorA = 'F';
		} else {
			int dec = Integer.parseInt(String.valueOf(accumulatorA), 16);
			dec--;
			String hex = Integer.toHexString(dec).toUpperCase();
			accumulatorA = hex.charAt(0);
			System.out.println("decremented " + accumulatorA);
		}

	}

	private void bz() {

		if (accumulatorA == '0') {
			builder = new StringBuilder();
			builder.append(memory[counter + 1]);
			builder.append(memory[counter + 2]);
			/*
			 * address1 = Integer.parseInt(String.valueOf(memory[counter+ 1]),
			 * 16) * 16; address2 =
			 * Integer.parseInt(String.valueOf(memory[counter + 2]), 16);
			 * location = address1 + address2;*
			 */
			location = Integer.parseInt(builder.toString(), 16);
			counter = location;
		} else {
			counter += 2;
		}
	}

	private void br() {
		/*
		 * address1 = Integer.parseInt(String.valueOf(memory[position + 1]), 16)
		 * * 16; address2 = Integer.parseInt(String.valueOf(memory[position +
		 * 2]), 16); location = address1 + address2; counter = location;*
		 */
		int counter = 0;
		for (char m : memory) {
			if (counter < 16) {
				System.out.print(m);

				counter++;
			}
		}
		builder = new StringBuilder();
		builder.append(memory[counter + 1]);
		builder.append(memory[counter + 2]);
		location = Integer.parseInt(builder.toString(), 16);
		counter = location;
	}

}
