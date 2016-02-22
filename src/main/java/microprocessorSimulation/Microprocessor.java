package microprocessorSimulation;

public class Microprocessor {

	private String[] memory;
	private String accumulatorA;
	private String accumulatorB;
	private int counter;
	private int position;
	private int address1;
	private int address2;
	private int location;

	public Microprocessor(Memory m) {
		this.accumulatorA = "0";
		this.accumulatorB = "0";
		this.counter = 0;
		this.position = 0;
		this.memory = m.getMemory();
		processMemory(memory);
		// m.setMemory(memory);

	}

	public void processMemory(String[] memory) {

		while (memory[counter] != "8") {
			switch (memory[counter]) {
			case "0":
				load();
				break;

			case "1":
				store();
				break;

			case "2":
				swap();
				break;

			case "3":
				add();
				break;

			case "4":
				increment();
				break;

			case "5":
				decrement();
				break;

			case "6":
				bz();
				break;
			case "7":
				br();
				break;
			case "8":
				return;
			}// close switch
			counter++;

		}// close while

	}

	private void load() {
		position = counter;
		address1 = Integer.parseInt(memory[position + 1], 16) * 16;
		address2 = Integer.parseInt(memory[position + 2], 16);
		location = address1 + address2;
		accumulatorA = memory[location];
		counter += 2;
	}

	private void store() {
		position = counter;
		address1 = Integer.parseInt(memory[position + 1], 16) * 16;
		address2 = Integer.parseInt(memory[position + 2], 16);
		location = address1 + address2;
		memory[location] = accumulatorA;
		counter += 2;
	}

	private void swap() {
		String temp = accumulatorA;
		accumulatorA = accumulatorB;
		accumulatorB = temp;
	}

	private void add() {
		// convert to decimal
		int decimalA = Integer.parseInt(accumulatorA, 16);
		int decimalB = Integer.parseInt(accumulatorB, 16);
		int total = decimalA + decimalB;
		// convert to hex
		String sTotal = Integer.toHexString(total).toUpperCase();
		if (sTotal.length() == 2) {
			accumulatorB = String.valueOf(sTotal.charAt(0));
			accumulatorA = String.valueOf(sTotal.charAt(1));
		} else {
			accumulatorB = "0";
			accumulatorA = String.valueOf(sTotal.charAt(0));
		}
	}

	private void increment() {
		if (accumulatorA.equals("F")) {
			accumulatorA = "0";
		} else {
			int dec = Integer.parseInt(accumulatorA, 16);
			dec++;
			accumulatorA = Integer.toHexString(dec).toUpperCase();

		}

	}

	private void decrement() {
		if (accumulatorA.equals("0")) {
			accumulatorA = "F";
		} else {
			int dec = Integer.parseInt(String.valueOf(accumulatorA), 16);
			dec--;
			accumulatorA = Integer.toHexString(dec).toUpperCase();

		}

	}

	private void bz() {

		if (accumulatorA.equals("0")) {
			position = counter;
			address1 = Integer.parseInt(memory[position + 1], 16) * 16;
			address2 = Integer.parseInt(memory[position + 2], 16);
			location = address1 + address2;
			counter = location;
		} else {
			counter += 2;
		}
	}

	private void br() {
		position = counter;
		address1 = Integer.parseInt(memory[position + 1], 16) * 16;
		address2 = Integer.parseInt(memory[position + 2], 16);
		location = address1 + address2;
		counter = location;
	}

}
