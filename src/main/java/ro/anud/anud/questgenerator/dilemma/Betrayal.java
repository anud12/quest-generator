package ro.anud.anud.questgenerator.dilemma;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.external.QuestNpc;
import ro.anud.anud.questgenerator.quest.KillImportantQuest;
import ro.anud.anud.questgenerator.quest.Quest;

public class Betrayal implements Dilemma {

    public static Activity betrayerActivity = () -> "Betrayed";

    private QuestScope questScope;
    private QuestNpc npc;

    public Betrayal(final QuestScope questScope, final QuestNpc npc) {
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
