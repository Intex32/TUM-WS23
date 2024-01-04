package pgdp.universe;

import java.util.HashSet;
import java.util.Set;

public class Universe {

	private Set<Animal> inhibitors;

	private Universe() {
		this.inhibitors = new HashSet<>();
	}

	public void addInhibitor(Animal animal) {
		inhibitors.add(animal);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		inhibitors.forEach(animal -> sb.append(animal).append(","));
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}

	private static final Universe universe = new Universe();

	public static Universe getUniverse() {
		return universe;
	}
}
