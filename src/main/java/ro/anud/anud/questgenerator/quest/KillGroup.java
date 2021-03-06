package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.dilemma.KillDilemma;
import ro.anud.anud.questgenerator.external.QuestNpc;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static ro.anud.anud.npc.NpcFilters.*;

public class KillGroup implements Quest {

    public static Activity killGroupQuestActivity = () -> "Given kill group quest";

    private int targetNumber;
    private int number;

    private Map<Integer, QuestNpc> npcMap = new HashMap<>();
    private QuestNpc npc;
    private QuestScope questScope;

    public KillGroup(QuestScope questScope, QuestNpc npc, int targetNumber) {
        this.npc = npc;
        npc.addActivity(killGroupQuestActivity);
        this.targetNumber = targetNumber;
        this.questScope = questScope;
        Stream.iterate(0, integer -> integer + 1)
                .limit(targetNumber)
                .forEach(integer -> {
                    npcMap.put(integer, questScope
                            .getNpc(isAlive()
                                            .and(not(npc))
                                            .and(notIn(npcMap.values()))
                            )
                            .addActivity(Activity.wanted));
                });
    }

    @Override
    public String getDescription() {
        return "Kill for " + npc.getName() + " " + number + " of " + targetNumber + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (!s.get().equals("k")) {
            return this;
        }

        if (this.number >= targetNumber) {
            return new ClaimRewardQuest(questScope, npc);
        }

        return new KillDilemma(questScope, npcMap.get(this.number), () -> {
            this.number = this.number + 1;
            return this;
        }).get();
    }
}
