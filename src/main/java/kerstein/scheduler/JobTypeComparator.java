package kerstein.scheduler;

import java.util.Comparator;

public class JobTypeComparator implements Comparator<Job> {

	@Override
	public int compare(Job a, Job b) {
		return a.getType().compareTo(b.getType());
	}

}
