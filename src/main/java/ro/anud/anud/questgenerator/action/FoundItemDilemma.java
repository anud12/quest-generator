package ro.anud.anud.questgenerator.action;

import ro.anud.anud.questgenerator.NpcGenerator;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcFilters;
import ro.anud.anud.questgenerator.quest.Quest;
import ro.anud.anud.questgenerator.quest.SeekNpcQuest;

public class FoundItemDilemma implements Dilemma {

    public static Activity lostItem = () -> "Lost item";

    private final Npc targetNpc;
    private NpcGenerator npcGenerator;

    public FoundItemDilemma(final NpcGenerator npcGenerator) {
        this.npcGenerator = npcGenerator;
        this.targetNpc = npcGenerator.get(NpcFilters.isAlive())
                .addActivity(lostItem)
                .addActivity(Activity.waitsForPlayer);
        System.out.println("Found important item");
    }

    @Override
    public Quest get() {
        return new SeekNpcQuest(npcGenerator, targetNpc);
    }
}
