package ro.anud.anud.questgenerator.action;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.quest.Quest;
import ro.anud.markovchain.Choice;

import java.util.function.Supplier;

public class KillDilemma implements Dilemma {

    private QuestScope questScope;
    private Supplier<Quest> parent;
    private Npc npc;

    public KillDilemma(QuestScope questScope, Npc npc, final Supplier<Quest> parent) {
        this.questScope = questScope;
        this.parent = parent;
        this.npc = npc;
    }

    @Override
    public Quest get() {

        npc.addActivity(Activity.slain);

        return new Choice<Supplier<Quest>>()
                .addChoice(0.9, parent)
                .addChoice(0.1, () -> new FoundItemDilemma(questScope).get())
                .chose()
                .get();
    }
}
