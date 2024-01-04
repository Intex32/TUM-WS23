package pgdp.universe;

public abstract class Animal {

	protected final String name;
	protected final Sex sex;

	public Animal(String name, Sex sex) {
		this.name = name;
		this.sex = sex;
	}

	public abstract boolean breedWith(Animal animal);

	@Override
	public String toString() {
		return name;
	}
}
