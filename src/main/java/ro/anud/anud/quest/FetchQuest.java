package ro.anud.anud.quest;

import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;

import java.util.function.Supplier;

public class FetchQuest implements Quest {

    public static Activity fetchQuestActivity = () -> "Given fetch quest";
    public static Activity receivedItemActivity = () -> "Received item";

    private Npc npc;

    public FetchQuest(final Npc npc) {
        this.npc = npc;
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
            return new ClaimRewardQuest(npc);
        }
        return this;
    }
}
