package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.dilemma.GetQuestDilemma;
import ro.anud.anud.questgenerator.external.QuestNpc;

import java.util.function.Supplier;

public class ClaimRewardQuest implements Quest {

    public static Activity turnInQuest = () -> "Rewarded player";

    private QuestNpc npc;
    private QuestScope questScope;

    public ClaimRewardQuest(final QuestScope questScope, final QuestNpc npc) {
        this.npc = npc;
        this.questScope = questScope;
        questScope.addQuest(this);

    }

    @Override
    public String getDescription() {
        return "Claim reward from " + npc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("c")) {
            npc.addActivity(turnInQuest);
            return new GetQuestDilemma(questScope, npc).get();
        }
        return this;
    }
}
