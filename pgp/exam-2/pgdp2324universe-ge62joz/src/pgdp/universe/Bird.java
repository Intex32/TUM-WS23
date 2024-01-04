package pgdp.universe;

public abstract class Bird extends Animal implements Oviparous {

	protected final byte[] melody;

	public Bird(String name, Sex sex, byte[] melody) {
		super(name, sex);
		this.melody = melody;
	}

	public void fly() {
		System.out.println(this.toString() + ConsoleOutputs.IS_FLYING);
	}
}
