package pgdp.tree;

import java.util.HashSet;
import java.util.Set;

public class Forest<T> {

    protected final Set<Tree<T>> trees = new HashSet<>();

    public void addTree(Tree<T> tree) {
        this.trees.add(tree);
    }

    public boolean contains(T value) {
        // TODO Exercise 10: Replace this with the correct solution
        return trees.stream().anyMatch(tTree -> tTree.contains(value));
    }
}
