package ro.anud.anud.questgenerator.action;

import ro.anud.anud.questgenerator.NpcGenerator;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.quest.*;
import ro.anud.markovchain.Choice;

import java.util.function.Supplier;

import static ro.anud.anud.npc.NpcFilters.*;

public class GetQuestDilemma implements Dilemma {

    public static Activity givenQuestActivity = () -> "Given quest";

    private Npc npc;
    private NpcGenerator npcGenerator;

    public GetQuestDilemma(final NpcGenerator npcGenerator, final Npc npc) {
        System.out.println("New Quest");
        this.npc = npc;
        this.npcGenerator = npcGenerator;
    }

    @Override
    public Quest get() {

        npc.addActivity(givenQuestActivity);

        return new Choice<Supplier<Quest>>()
                .addChoice(1, () -> new FetchQuest(npcGenerator, npc))
                .addChoice(1, () -> new KillGroup(npcGenerator, npc, 2))
                .addChoice(1, () -> new DiscoverNpc(npcGenerator, npc))
                .addChoice(1, () -> new SeekNpcQuest(npcGenerator, npcGenerator.get(validQuestGiver().and(not(npc)))))
                .addChoice(1, () -> new SeekNpcQuest(npcGenerator, npcGenerator.get(isAlive().and(not(npc)))))
                .addChoice(1, () -> new EscortQuest(npcGenerator, npc, 2))
                .chose()
                .get();
    }
}
