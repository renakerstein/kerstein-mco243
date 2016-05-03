package kerstein.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/*
 processes are dispatched FIFO but are given a limited amount of processor time (time slice/quantum)
 */

public class RoundRobinScheduler extends Scheduler {

	public RoundRobinScheduler(List<Job> jobs) {
		super(jobs);
	}

	@Override
	public void run() {

		while (!jobs.isEmpty()) {
			Job job = jobs.remove(0);
			int actualTimeSlice = executeJob(job);
			totalTime += actualTimeSlice;

			if (!job.isFinished()) {
				// if the process is not finished before its quantum time
				// expires
				// it is put back on to the queue
				jobs.add(job);
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

		RoundRobinScheduler scheduler = new RoundRobinScheduler(
				new ArrayList<Job>(jobs));

		scheduler.run();

		System.out.println(String.format(
				"Number of jobs completed=%d \nTotal time=%d",
				scheduler.numJobsCompleted, scheduler.totalTime));

	}
}
