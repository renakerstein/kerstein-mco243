package kerstein.scheduler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class JobScheduler implements Runnable {

	// max amount of time each job runs for
	private static final int TIME_SLICE = 10;
	// amount of time it takes to switch jobs
	private static final int OVERHEAD = 3;

	private int numJobsCompleted ;
	private List<Job> jobs;
	private Comparator<Job> comparator;
	private int totalTime;

	public JobScheduler(List<Job> jobs, Comparator<Job> comparator) {
		this.jobs = jobs;
		this.comparator = comparator;
		this.numJobsCompleted = 0;;
		this.totalTime = 0;
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
				lastJob=job;
			}
		}

	}

	private int executeJob(Job job) {
		job.setState(JobState.Running);

		job.setLastRanAtTime(System.currentTimeMillis());

		int actualTimeSlice = computeActualTimeSLice(job);
		job.decrementTimeToLeftRun(actualTimeSlice);
		

		if (job.isFinished()) {
			jobs.remove(0);
			numJobsCompleted++;
		}

		else {
			job.setState(JobState.Ready);
		}
		return actualTimeSlice;
	}

	private int computeActualTimeSLice(Job job) {
		Random rand = new Random();

		int timeLeftToRun = job.getTimeLeftToRun();
		int actualTimeSlice;
		if (job.getType() == JobType.IO) {
			actualTimeSlice = Math.min(timeLeftToRun, rand.nextInt(TIME_SLICE));
		} else {
			actualTimeSlice = Math.min(timeLeftToRun, TIME_SLICE);
		}
		return actualTimeSlice;
	}

	
	public int getNumJobsCompleted() {
		return numJobsCompleted;
	}

	public void setNumJobsCompleted(int numJobsCompleted) {
		this.numJobsCompleted = numJobsCompleted;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public static void main(String[] args) {
		List<Job> jobs = Arrays.asList(new Job("1", Priority.High, JobType.Computation, 100), new Job("2",
				Priority.Low, JobType.Computation, 200), new Job("3", Priority.Medium, JobType.IO, 100), new Job("4",
				Priority.Medium, JobType.Computation, 1000), new Job("5", Priority.High, JobType.Computation, 350),
				new Job("6", Priority.Low, JobType.IO, 20), new Job("7", Priority.High, JobType.IO, 30), new Job("8",
						Priority.Medium, JobType.Computation, 600), new Job("9", Priority.High, JobType.Computation,
						700), new Job("10", Priority.Low, JobType.IO, 200));

		JobScheduler scheduler = new JobScheduler(new ArrayList<Job>(jobs), new PriorityComparator());

		scheduler.run();

		System.out.println(String.format("numJobsCompleted=%d totalTime=%d", scheduler.getNumJobsCompleted(),
				scheduler.getTotalTime()));

	}
}
