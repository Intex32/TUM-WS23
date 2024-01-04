package pgdp.universe;

public class Parrot extends Bird {


    public Parrot(String name, Sex sex, byte[] melody) {
        super(name, sex, melody);
    }

    @Override
    public boolean breedWith(Animal animal) {
        boolean valid = animal instanceof Parrot && this.sex != animal.sex;
        if(valid) {
            if(animal.sex == Sex.FEMALE)
                ((Parrot) animal).layEgg();
            else this.layEgg();
        }
        return valid;
    }

    @Override
    public void layEgg() {
        super.layEgg();
    }

}
