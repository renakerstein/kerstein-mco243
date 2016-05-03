package kerstein.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SelfishRoundRobinScheduler extends Scheduler {

	private ArrayList<Job> active = new ArrayList<Job>();
	private ArrayList<Job> holding = new ArrayList<Job>();

	public SelfishRoundRobinScheduler(List<Job> jobs) {
		super(jobs);
		divideIntoQueues();
	}

	private void divideIntoQueues() {
		for (Job j : jobs) {
			if (j.getPriority() == Priority.High) {
				active.add(j);
			} else {
				holding.add(j);
			}
		}
	}

	@Override
	public void run() {
		while (active.size() != 0) {
			execute();
		}

		while (holding.size() != 0) {
			increasePriority();
			execute();
		}

	}

	private void execute() {
		Job job = active.remove(0);
		int actualTimeSlice = executeJob(job);
		totalTime += actualTimeSlice;
		if (!job.isFinished() && job.getPriority() == Priority.High) {
			active.add(job);
		}
	}

	private void increasePriority() {
		Iterator<Job> iter = holding.iterator();

		while (iter.hasNext()) {
			Job j = iter.next();
			int nextOrdinal = j.getPriority().ordinal() + 1;
			if (nextOrdinal < Priority.values().length) {
				j.setPriority(Priority.values()[nextOrdinal]);

			}
			if (j.getPriority() == Priority.High) {
				active.add(j);
				iter.remove();
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

		SelfishRoundRobinScheduler scheduler = new SelfishRoundRobinScheduler(
				new ArrayList<Job>(jobs));

		scheduler.run();

		System.out.println(String.format(
				"Number of jobs completed=%d \nTotal time=%d",
				scheduler.numJobsCompleted, scheduler.totalTime));

	}
}
