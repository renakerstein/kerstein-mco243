package kerstein.scheduler;

import java.util.Comparator;

public class JobStateComparator implements Comparator<Job>{

	@Override
	public int compare(Job a, Job b) {
		return a.getState().compareTo(b.getState());
	}

}
