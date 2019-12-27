package ro.anud.anud.action;

import ro.anud.anud.quest.Quest;
import ro.anud.markovchain.Choice;

import java.util.function.Supplier;

public class KillDilema implements Dilema {

    private Supplier<Quest> parent;

    public KillDilema(final Supplier<Quest> parent) {
        this.parent = parent;
    }

    @Override
    public Quest get() {
        return new Choice<Supplier<Quest>>()
                .addChoice(0.9, parent)
                .addChoice(0.1, () -> new FoundImportantItemDilema().get())
                .chose()
                .get();
    }
}
