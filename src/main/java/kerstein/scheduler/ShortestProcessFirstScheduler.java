package kerstein.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShortestProcessFirstScheduler extends Scheduler {

	/*
	 * The scheduler selects the waiting process with the smallest estimated
	 * run-time-to-completion
	 */
	private Comparator<Job> comparator;

	public ShortestProcessFirstScheduler(List<Job> jobs,
			Comparator<Job> comparator) {
		super(jobs);
		this.comparator = comparator;
	}

	@Override
	public void run() {
		Job lastJob = null;
		while (!jobs.isEmpty()) {
			Collections.sort(jobs, comparator);
			Job job = jobs.get(0);
			int actualTimeSlice = executeJob(job);

			totalTime += actualTimeSlice;

			if (job != lastJob) {
				totalTime += OVERHEAD;
				lastJob = job;
			}
		}
	}

	public static void main(String[] args) {
		List<Job> jobs = Arrays.asList(new Job("1", Priority.High,
				JobType.Computation, 100, 5L), new Job("2", Priority.Low,
				JobType.Computation, 200, 3L), new Job("3", Priority.Medium,
				JobType.IO, 100, 2L), new Job("4", Priority.Medium,
				JobType.Computation, 1000, 4L), new Job("5", Priority.High,
				JobType.Computation, 350, 6L), new Job("6", Priority.Low,
				JobType.IO, 20, 1L), new Job("7", Priority.High, JobType.IO,
				30, 4L), new Job("8", Priority.Medium, JobType.Computation,
				600, 5L), new Job("9", Priority.High, JobType.Computation, 700,
				6L), new Job("10", Priority.Low, JobType.IO, 200, 3L));

		ShortestProcessFirstScheduler scheduler = new ShortestProcessFirstScheduler(
				new ArrayList<Job>(jobs), new TimeLeftToRunComparator());

		scheduler.run();

		System.out.println(String.format(
				"Number of jobs completed=%d \nTotal time=%d",
				scheduler.numJobsCompleted, scheduler.totalTime));

	}
}
