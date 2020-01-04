package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.npc.NpcFilters;
import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.external.QuestNpc;

import java.util.function.Supplier;

public class DiscoverNpc implements Quest {


    public static Activity discoverQuestActivity = () -> "Given discover quest";
    public static Activity foundActivity = () -> "Found";

    private QuestNpc targetNpc;
    private QuestNpc npc;
    private QuestScope questScope;

    public DiscoverNpc(final QuestScope questScope, final QuestNpc npc) {
        this.npc = npc;
        this.questScope = questScope;
        targetNpc = questScope.getNpc(NpcFilters.isAlive().and(NpcFilters.not(npc)));
        npc.addActivity(discoverQuestActivity);
    }

    @Override
    public String getDescription() {
        return "Discover " + targetNpc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("d")) {
            targetNpc.addActivity(foundActivity);
            return new ClaimRewardQuest(questScope, npc);
        }
        return this;
    }
}
