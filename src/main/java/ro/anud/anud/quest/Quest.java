package ro.anud.anud.quest;

import java.util.function.Supplier;

public interface Quest {
    Quest read(Supplier<String> s);
}
