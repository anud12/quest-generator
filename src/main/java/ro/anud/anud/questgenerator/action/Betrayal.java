package ro.anud.anud.questgenerator.action;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.quest.KillImportantQuest;
import ro.anud.anud.questgenerator.quest.Quest;

public class Betrayal implements Dilemma {

    public static Activity betrayerActivity = () -> "Betrayed";

    private QuestScope questScope;
    private Npc npc;

    public Betrayal(final QuestScope questScope, final Npc npc) {
        this.questScope = questScope;
        this.npc = npc;
        npc.addActivity(betrayerActivity);
    }

    @Override
    public Quest get() {
        System.out.println(betrayerActivity.getDescription());
        return new KillImportantQuest(questScope, npc);
    }
}
