package pgdp.upa.jobmanager;

import java.util.Arrays;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        // As long as your code compiles, this method will be ignored.
        // Example:
        // JobManager jobManager = JobManager.exampleJobManager();
        JobManager jobManager = new JobManager();
        jobManager.addJob(new Job(1, 10), 2);
        jobManager.addJob(new Job(2, 20), 2);
        jobManager.addJob(new Job(3, 30), 1);
        jobManager.addJob(new Job(4, 30), 450);
        jobManager.addJob(new Job(5, 30), 254);
        JobIterator jobIterator = new JobIterator(jobManager.getPriorityGroups());
        while (jobIterator.hasNext()) {
            System.out.println(jobIterator.next().getId());
        }

        System.out.println();

        jobManager = new JobManager();
        jobManager.addJob(new Job(1, 10), 2);
        jobManager.addJob(new Job(2, 20), 2);
        jobManager.addJob(new Job(3, 30), 1);
        jobManager.addJob(new Job(4, 30), 450);
        jobManager.addJob(new Job(5, 30), 254);
        System.out.println(durationOfFirstJobs((Iterable<Job>) jobManager, 3));
    }

    public static int durationOfFirstJobs(Iterable<Job> jobManager, int numberOfJobs) {
        Iterator<Job> it = jobManager.iterator();
        int sum = 0;
        for (int i = 0; i < numberOfJobs && it.hasNext(); i++) {
            sum += it.next().getDurationInMinutes();
        }
        return sum;
    }
}
