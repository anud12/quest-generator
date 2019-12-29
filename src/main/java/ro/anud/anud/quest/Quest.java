package ro.anud.anud.quest;

import java.util.function.Supplier;

public interface Quest {
    String getDescription();

    Quest read(Supplier<String> s);
}
