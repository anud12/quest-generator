package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.action.Betrayal;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.markovchain.Choice;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class EscortQuest implements Quest {

    public static Activity escortQuestActivity = () -> "Given escort quest";
    public static Activity escortedActivity = () -> "Escorted";

    private QuestScope questScope;
    private AtomicReference<Integer> distance;
    private Npc npc;

    public EscortQuest(final QuestScope questScope, final Npc npc, final Integer distance) {
        this.questScope = questScope;
        this.distance = new AtomicReference<>(distance);
        this.npc = npc;
        npc.addActivity(escortedActivity);
    }

    @Override
    public String getDescription() {
        return "Escorting " + npc.getName() + " distance of " + distance.get() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (!s.get().equals("e")) {
            return this;
        }

        npc.addActivity(escortedActivity);

        if (distance.updateAndGet(integer -> integer - 1) <= 0) {
            return new ClaimRewardQuest(questScope, npc);
        }

        return new Choice<Supplier<Quest>>()
                .addChoice(0.9, () -> this)
                .addChoice(0.1, () -> new Betrayal(questScope, npc).get())
                .chose()
                .get();
    }
}
