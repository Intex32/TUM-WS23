package pgdp.universe;

public enum ConsoleOutputs {

    GIVE_BIRTH(" gave birth!"),
    IS_FLYING(" is flying!"),
    IS_SUCKLING(" is suckling!"),
    LAYED_AN_EGG(" layed an egg!");

    private final String str;

    ConsoleOutputs(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
