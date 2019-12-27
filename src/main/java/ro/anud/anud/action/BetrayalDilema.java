package ro.anud.anud.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.quest.KillImportantQuest;
import ro.anud.anud.quest.Quest;

public class BetrayalDilema implements Dilema {

    private Npc npc;

    public BetrayalDilema(final Npc npc) {
        this.npc = npc;
        npc.addHistory("Betrayed player");
    }

    @Override
    public Quest get() {
        System.out.println("Betrayed player");
        return new KillImportantQuest(npc);
    }
}
