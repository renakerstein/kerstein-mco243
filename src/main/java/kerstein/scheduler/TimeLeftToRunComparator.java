package kerstein.scheduler;

import java.util.Comparator;

public class TimeLeftToRunComparator implements Comparator<Job>{

	@Override
	public int compare(Job a, Job b) {
		Integer timeLeftA=a.getTimeLeftToRun();
		Integer timeLeftB=b.getTimeLeftToRun();
		return timeLeftA.compareTo(timeLeftB);
	}

}
