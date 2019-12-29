package ro.anud.anud.action;

import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.quest.Quest;
import ro.anud.markovchain.Choice;

import java.util.function.Supplier;

public class KillDilema implements Dilema {

    private Supplier<Quest> parent;
    private Npc npc;

    public KillDilema(Npc npc, final Supplier<Quest> parent) {
        this.parent = parent;
        this.npc = npc;
    }

    @Override
    public Quest get() {

        npc.addActivity(Activity.slain);

        return new Choice<Supplier<Quest>>()
                .addChoice(0.9, parent)
                .addChoice(0.1, () -> new FoundItemDilema().get())
                .chose()
                .get();
    }
}
