package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.NpcGenerator;
import ro.anud.anud.questgenerator.action.GetQuestDilemma;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;

import java.util.function.Supplier;

public class ClaimRewardQuest implements Quest {

    public static Activity turnInQuest = () -> "Rewarded player";

    private Npc npc;
    private NpcGenerator npcGenerator;

    public ClaimRewardQuest(final NpcGenerator npcGenerator, final Npc npc) {
        this.npc = npc;
        this.npcGenerator = npcGenerator;
    }

    @Override
    public String getDescription() {
        return "Claim reward from " + npc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("c")) {
            npc.addActivity(turnInQuest);
            return new GetQuestDilemma(npcGenerator, npc).get();
        }
        return this;
    }
}
