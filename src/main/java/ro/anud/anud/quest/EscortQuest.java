package ro.anud.anud.quest;

import ro.anud.anud.action.BetrayalAction;
import ro.anud.anud.npc.Npc;
import ro.anud.markovchain.Choice;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public class EscortQuest implements Quest {

    private AtomicReference<Integer> distance;
    private Npc npc;

    public EscortQuest(final Npc npc, final Integer distance) {
        this.distance = new AtomicReference<>(distance);
        this.npc = npc;
        npc.addHistory("Required escort");
    }

    @Override
    public Quest read(final Supplier<String> s) {
        System.out.print("Escort quest - " + distance.get() + " : ");
        if (!s.get().equals("e")) {
            return this;
        }

        if (distance.updateAndGet(integer -> integer - 1) <= 0) {
            npc.addHistory("Escorted successfully");
            return new TurnInQuest(npc);
        }

        return new Choice<Supplier<Quest>>()
                .addChoice(0.9, () -> this)
                .addChoice(0.1, () -> new BetrayalAction(npc).get())
                .chose()
                .get();
    }
}
