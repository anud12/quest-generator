package ro.anud.anud.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.quest.KillImportantQuest;
import ro.anud.anud.quest.Quest;

import java.util.function.Supplier;

public class BetrayalAction implements Action {

    private Npc npc;

    public BetrayalAction(final Npc npc) {
        this.npc = npc;
        npc.addHistory("Betrayed player");
    }

    @Override
    public Quest get() {
        System.out.println("Betrayed player");
        return new KillImportantQuest(npc);
    }
}
