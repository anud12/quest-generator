package ro.anud.anud.quest;

import ro.anud.anud.action.GetQuestDilema;
import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;

import java.util.function.Supplier;

public class ClaimRewardQuest implements Quest {

    public static Activity turnInQuest = () -> "Rewarded player";

    private Npc npc;

    public ClaimRewardQuest(final Npc npc) {
        this.npc = npc;
    }

    @Override
    public String getDescription() {
        return "Claim reward from " + npc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("c")) {
            npc.addActivity(turnInQuest);
            return new GetQuestDilema(npc).get();
        }
        return this;
    }
}
