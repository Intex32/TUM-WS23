package pgdp.universe;

public interface Viviparous {

    default void giveBirth() {
        System.out.println(toString() + ConsoleOutputs.GIVE_BIRTH);
    }

}