package pgdp.tree;

import java.util.Arrays;

public class MinMaxBSTree<T extends Comparable<T>> extends BSTree<T> {

	@Override
	public void insert(T value) {
		// TODO Exercise 6: Replace this with the correct solution
		if (isEmpty()) {
			root = new MinMaxBSTNode<>(value);
		} else {
			((BSTNode<T>) root).insert(value);
		}
	}

	protected class MinMaxBSTNode<B extends T> extends BSTNode<B> {

		protected B min;
		protected B max;

		protected MinMaxBSTNode(B value) {
			super(value);
			min = value;
			max = value;
		}

		@Override
		public <G extends T> Node<G> createNode(G value) {
			return new MinMaxBSTNode<>(value);
		}

		public void insert(B value) {
			super.insert(value);
			updateMinMax(value);
			// TODO Exercise 5: Replace this with the correct solution
		}

		private void updateMinMax(B value) {
			if (value.compareTo(min) < 0) {
				min = value;
			}
			if (max.compareTo(value) < 0) {
				max = value;
			}
		}

		protected boolean canContain(B value) {
			// TODO Exercise 7: Replace this with the correct solution
			return this.min.compareTo(value) < 1 && value.compareTo(this.max) < 1;
		}

		@Override
		public boolean contains(B value) {
			// TODO Exercise 8: Replace this with the correct solution
			if(!canContain(value)) {
				return false;
			}
			return super.contains(value);
		}

		@Override
		public String toString() {
			return "{value = " + getValue() + ", min = " + min + ", max = " + max + " nodes = "
					+ Arrays.deepToString(getNodes()) + "}";
		}

		@Override
		public String toGraphviz() {
			StringBuilder sb = new StringBuilder();
			sb.append(getValue()).append(" [label=\"").append(getValue()).append(" : [").append(min).append(", ").append(max).append("]\"];\n");
			sb.append("subgraph {\n");
			childToGraphviz(sb, "left", getLeftChild());
			childToGraphviz(sb, "right", getRightChild());
			sb.append("}\n");
			return sb.toString();
		}
	}
}
