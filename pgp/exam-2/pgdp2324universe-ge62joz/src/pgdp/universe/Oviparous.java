package pgdp.universe;

public interface Oviparous {

	default void layEgg() {
		System.out.println(toString() + ConsoleOutputs.LAYED_AN_EGG);
	}

}
