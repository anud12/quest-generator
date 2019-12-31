package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.NpcGenerator;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcFilters;

import java.util.function.Supplier;

public class DiscoverNpc implements Quest {


    public static Activity discoverQuestActivity = () -> "Given discover quest";
    public static Activity foundActivity = () -> "Found";

    private Npc targetNpc;
    private Npc npc;
    private NpcGenerator npcGenerator;

    public DiscoverNpc(final NpcGenerator npcGenerator, final Npc npc) {
        this.npc = npc;
        this.npcGenerator = npcGenerator;
        targetNpc = npcGenerator.get(NpcFilters.isAlive().and(NpcFilters.not(npc)));
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
            return new ClaimRewardQuest(npcGenerator, npc);
        }
        return this;
    }
}
