package pgdp.universe;

public class Platypus extends Mammal implements Oviparous {

    public Platypus(String name, Sex sex) {
        super(name, sex, (byte) 0);
    }

    @Override
    public void giveBirth() {

    }

    @Override
    public boolean breedWith(Animal animal) {
        boolean valid = animal instanceof Platypus && this.sex != animal.sex;
        if(valid) {
            if(animal.sex == Sex.FEMALE)
                ((Platypus) animal).layEgg();
            else this.layEgg();
        }
        return valid;
    }
}
