package ro.anud.anud.quest;

import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcFilters;
import ro.anud.anud.npc.NpcRepository;

import java.util.function.Supplier;

public class DiscoverNpc implements Quest {


    public static Activity discoverQuestActivity = () -> "Given discover quest";
    public static Activity foundActivity = () -> "Found";

    private Npc targetNpc;
    private Npc npc;

    public DiscoverNpc(final Npc npc) {
        this.npc = npc;
        targetNpc = NpcRepository.get(NpcFilters.isAlive().and(NpcFilters.not(npc)));
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
            return new ClaimRewardQuest(npc);
        }
        return this;
    }
}
