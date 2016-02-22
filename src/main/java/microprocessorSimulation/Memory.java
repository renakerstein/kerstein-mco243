package microprocessorSimulation;

public class Memory {

	private String[] memory;

	public Memory(String memoryStr) {
		memory = memoryStr.split("");
	}

	public String[] getMemory() {
		return memory;
	}

	public void setMemory(String[] memory) {
		this.memory = memory;
	}

}
