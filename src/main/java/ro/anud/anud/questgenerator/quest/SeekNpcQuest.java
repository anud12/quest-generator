package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;

import java.util.function.Supplier;

public class SeekNpcQuest implements Quest {

    public static Activity seekNpcQuestActivity = () -> "Seek target";
    public static Activity found = () -> "Found npc";

    private QuestScope questScope;
    private Npc npc;

    public SeekNpcQuest(final QuestScope questScope, final Npc npc) {
        this.questScope = questScope;
        this.npc = npc;
        npc.addActivity(seekNpcQuestActivity);
    }

    @Override
    public String getDescription() {
        return "Seek " + npc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("s")) {
            npc.addActivity(found);
            return new ClaimRewardQuest(questScope, npc);
        }
        return this;
    }
}
