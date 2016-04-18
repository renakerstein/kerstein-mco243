package kerstein.scheduler;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Job>{

	@Override
	public int compare(Job a, Job b) {
		return a.getPriority().compareTo(b.getPriority());
	}

}
