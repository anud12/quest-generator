package ro.anud.anud.questgenerator.dilemma;

import ro.anud.anud.npc.NpcFilters;
import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.external.QuestNpc;
import ro.anud.anud.questgenerator.quest.Quest;
import ro.anud.anud.questgenerator.quest.SeekNpcQuest;

public class FoundItemDilemma implements Dilemma {

    public static Activity lostItem = () -> "Lost item";

    private final QuestNpc targetNpc;
    private QuestScope questScope;

    public FoundItemDilemma(final QuestScope questScope) {
        this.questScope = questScope;
        this.targetNpc = questScope.getNpc(NpcFilters.isAlive())
                .addActivity(lostItem)
                .addActivity(Activity.waitsForPlayer);
        System.out.println("Found important item");
    }

    @Override
    public Quest get() {
        return new SeekNpcQuest(questScope, targetNpc);
    }
}
