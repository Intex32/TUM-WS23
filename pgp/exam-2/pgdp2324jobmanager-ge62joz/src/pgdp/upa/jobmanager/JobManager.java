package pgdp.upa.jobmanager;

import java.util.*;

public class JobManager implements Iterable<Job> {

    private Map<Integer, List<Job>> priorityGroups;

    // TODO Exercise 1: Implement the constructor.
    public JobManager() {
        this.priorityGroups = new HashMap<>();
    }

    public void addJob(Job job, int priority) {
        priorityGroups.putIfAbsent(priority, new ArrayList<>());
        priorityGroups.get(priority).add(job);
    }

    public Map<Integer, List<Job>> getPriorityGroups() {
        return priorityGroups;
    }

    public String toString() {
        return priorityGroups.toString();
    }

    /**
     * This method can be used for testing as in Main.main even if
     * you could not implement `addJob` and the constructor.
     */
    public static JobManager exampleJobManager() {
        JobManager jobManager = new JobManager();
        Map<Integer, List<Job>> examplePriorityGroups = new HashMap<>();
        examplePriorityGroups.put(2, Arrays.asList(new Job(1, 10), new Job(2, 20)));
        examplePriorityGroups.put(1, Arrays.asList(new Job(3, 30)));
        examplePriorityGroups.put(3, Arrays.asList(new Job(4, 40), new Job(5, 50)));
        jobManager.priorityGroups = examplePriorityGroups;
        return jobManager;
    }

    @Override
    public Iterator<Job> iterator() {
        return new JobIterator(new HashMap<>(priorityGroups));
    }
}
