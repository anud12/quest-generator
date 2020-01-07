package ro.anud.anud.questgenerator;

public abstract class Action {

    private String name;

    public Action(final String name) {
        this.name = name;
    }

    abstract String parse(String s);
}
