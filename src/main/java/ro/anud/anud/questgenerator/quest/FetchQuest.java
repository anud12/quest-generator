package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.external.QuestNpc;

import java.util.function.Supplier;

public class FetchQuest implements Quest {

    public static Activity fetchQuestActivity = () -> "Given fetch quest";
    public static Activity receivedItemActivity = () -> "Received item";

    private QuestNpc npc;
    private QuestScope questScope;

    public FetchQuest(final QuestScope questScope, final QuestNpc npc) {
        this.npc = npc;
        this.questScope = questScope;
        npc.addActivity(fetchQuestActivity);
    }

    @Override
    public String getDescription() {
        return "Fetch quest for " + npc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("f")) {
            npc.addActivity(receivedItemActivity);
            return new ClaimRewardQuest(questScope, npc);
        }
        return this;
    }
}
