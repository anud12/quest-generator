package ro.anud.anud.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.quest.Quest;
import ro.anud.markovchain.Choice;

import java.util.function.Supplier;

public class KillImportantDilema implements Dilema {
    private Supplier<Quest> parent;
    private Npc npc;

    public KillImportantDilema(final Npc npc, final Supplier<Quest> parent) {
        this.parent = parent;
        this.npc = npc;
    }

    @Override
    public Quest get() {
        npc.addHistory("Killed");
        return new Choice<Supplier<Quest>>()
                .addChoice(0.3, parent)
                .addChoice(0.7, () -> new FoundImportantItemDilema().get())
                .chose()
                .get();
    }
}
