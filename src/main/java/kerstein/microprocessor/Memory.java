package kerstein.microprocessor;

public class Memory {

	private char[] memory;

	public Memory(String memoryStr) {
		memory = memoryStr.toCharArray();
	}

	public char[] getMemory() {
		return memory;
	}

	public void setMemory(char[] memory) {
		this.memory = memory;
	}

}
