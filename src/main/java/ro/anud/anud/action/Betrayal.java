package ro.anud.anud.action;

import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.quest.KillImportantQuest;
import ro.anud.anud.quest.Quest;

public class Betrayal implements Dilema {

    public static Activity betrayerActivity = () -> "Betrayed";

    private Npc npc;

    public Betrayal(final Npc npc) {
        this.npc = npc;
        npc.addActivity(betrayerActivity);
    }

    @Override
    public Quest get() {
        System.out.println(betrayerActivity.getDescription());
        return new KillImportantQuest(npc);
    }
}
