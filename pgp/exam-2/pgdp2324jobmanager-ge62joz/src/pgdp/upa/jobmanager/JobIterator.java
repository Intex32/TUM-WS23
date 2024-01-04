package pgdp.upa.jobmanager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JobIterator implements Iterator<Job> {

    protected int[] sortedPriorityLevels;
    protected boolean hasItems;
    private int currentPriorityLevelIndex;
    private Map<Integer, List<Job>> priorityGroups;
    protected Iterator<Job> currentGroupIterator;

    public JobIterator(Map<Integer, List<Job>> priorityGroups) {
        // The constructor implies a certain solution strategy.
        // You can change it if you want to, but you are recommended to keep it as it is.
        // However, you MUST keep the first line and the signature of the constructor.
        this.priorityGroups = priorityGroups;
        if (priorityGroups.isEmpty()) {
            hasItems = false;
            return;
        }
        hasItems = true;
        sortedPriorityLevels = getSortedPriorities();

        currentPriorityLevelIndex = sortedPriorityLevels.length-1;
        int highestPriority = sortedPriorityLevels[currentPriorityLevelIndex];
        currentGroupIterator = priorityGroups.get(highestPriority).iterator();
    }

    // TODO The following methods are not implemented correctly.
    // You can use them for testing the other methods.
    // Without any modification, the iterator will
    // iterate over all jobs with the priority 3.


    protected int[] getSortedPriorities() {
        // TODO Exercise 3: Replace this with the correct solution
        // If you are stuck, you can test the other methods with a fixed return value.
        //return new int[] {1, 2, 3};
        int[] indices = priorityGroups.keySet().stream().mapToInt(value -> value).toArray();
        Arrays.sort(indices);
        return indices;
    }


    @Override
    public boolean hasNext() {
        if(!hasItems)
            return false;
        // TODO Exercise 4: Replace this with the correct solution
        if(!currentGroupIterator.hasNext()) {
            if(currentPriorityLevelIndex == 0) {
                hasItems = false;
                return false;
            } else {
                currentPriorityLevelIndex--;
                int nextHighestPriority = sortedPriorityLevels[currentPriorityLevelIndex];
                currentGroupIterator = priorityGroups.get(nextHighestPriority).iterator();
            }
        }
        return currentGroupIterator.hasNext();
    }

    @Override
    public Job next() {
        // TODO Exercise 4: Replace this with the correct solution
        return currentGroupIterator.next();
    }
}
