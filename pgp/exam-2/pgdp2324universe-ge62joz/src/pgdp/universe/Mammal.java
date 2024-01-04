package pgdp.universe;

public class Mammal extends Animal implements Viviparous {

    protected byte numberOfTeats;

    public Mammal(String name, Sex sex, byte numberOfTeats) {
        super(name, sex);
        this.numberOfTeats = numberOfTeats;
    }

    public void suckle() {
        System.out.println(toString() + ConsoleOutputs.IS_SUCKLING);
    }

    @Override
    public boolean breedWith(Animal animal) {
        return false;
    }
}
