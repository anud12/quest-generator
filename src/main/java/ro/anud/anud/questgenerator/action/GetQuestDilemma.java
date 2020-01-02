package ro.anud.anud.questgenerator.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.quest.*;
import ro.anud.markovchain.Choice;

import java.util.function.Supplier;

import static ro.anud.anud.npc.NpcFilters.*;

public class GetQuestDilemma implements Dilemma {

    public static Activity givenQuestActivity = () -> "Given quest";

    private Npc npc;
    private QuestScope questScope;

    public GetQuestDilemma(final QuestScope questScope, final Npc npc) {
        System.out.println("New Quest");
        this.npc = npc;
        this.questScope = questScope;
    }

    @Override
    public Quest get() {

        npc.addActivity(givenQuestActivity);

        return new Choice<Supplier<Quest>>()
                .addChoice(1, () -> new FetchQuest(questScope, npc))
                .addChoice(1, () -> new KillGroup(questScope, npc, 2))
                .addChoice(1, () -> new DiscoverNpc(questScope, npc))
                .addChoice(1, () -> new SeekNpcQuest(questScope, questScope.getNpc(validQuestGiver().and(not(npc)))))
                .addChoice(1, () -> new SeekNpcQuest(questScope, questScope.getNpc(isAlive().and(not(npc)))))
                .addChoice(1, () -> new EscortQuest(questScope, npc, 2))
                .chose()
                .get();
    }
}
