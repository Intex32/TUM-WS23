package pgdp.threadswarmup;

import java.util.Optional;

public class SumThread extends Thread {
    private Optional<Integer> sum;

    private Node node;

    private int remainingThreads;

    SumThread leftThread;
    SumThread rightThread;


    public SumThread(Node node, int remainingThreads) {
        this.node = node;
        this.remainingThreads = remainingThreads;
        this.sum = Optional.empty();
    }

    public Optional<Integer> getSum() {
        return sum;
    }

    public void run() {
        // compute sum on this non-parallel on this thread, and return
        if(remainingThreads <= 2) {
            sum = Optional.of(node.sum());
            return;
        }

        // start threads, from here on the children's sums are calculated in the background
        startChildThreads();

        // accumulate sum
        int sum = node.getValue();
        if(rightThread != null) {
            try {
                rightThread.join(); // wait for thread to finish
                sum += rightThread.sum.get(); // capture sum, cannot be null as thread completed execution
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(leftThread != null) {
            try {
                leftThread.join(); // wait for thread to finish
                sum += leftThread.sum.get(); // capture sum, cannot be null as thread completed execution
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.sum = Optional.of(sum); // apply final sum to current node
    }

    protected void startChildThreads() {
        int rightThreadCount = remainingThreads - 1 - leftThreadCount();
        if(node.getRight().isPresent() && rightThreadCount >= 1) {
            rightThread = new SumThread(node.getRight().get(), rightThreadCount);
            rightThread.start();
        }

        int leftThreadCount = leftThreadCount();
        if(node.getLeft().isPresent() && leftThreadCount >= 1) {
            leftThread = new SumThread(node.getLeft().get(), leftThreadCount);
            leftThread.start();
        }
    }

    protected int leftThreadCount() {
        if(node.getLeft().isEmpty()) {
            return 0;
        }
        if(node.getRight().isEmpty()) {
            return remainingThreads - 1;
        }
        return (remainingThreads - 1) / 2;
    }

}
