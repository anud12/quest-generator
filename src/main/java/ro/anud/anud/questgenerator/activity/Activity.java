package ro.anud.anud.questgenerator.activity;

public interface Activity {

    static Activity waitsForPlayer = () -> "Waits for player";
    static Activity wanted = () -> "Wanted";
    static Activity slain = () -> "Slain";

    String getDescription();


}
