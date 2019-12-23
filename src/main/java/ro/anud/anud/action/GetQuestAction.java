package ro.anud.anud.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.quest.*;
import ro.anud.markovchain.Choice;

import java.util.Random;
import java.util.function.Supplier;

public class GetQuestAction implements Action {

    private Random random = new Random();
    private Npc npc;

    public GetQuestAction(final Npc npc) {
        this.npc = npc;
    }

    @Override
    public Quest get() {
        System.out.println("New Quest");

        return new Choice<Supplier<Quest>>()
                .addChoice(1, () -> new FetchQuest(npc))
                .addChoice(0.8, () -> new KillGroup(npc, random.nextInt(4) + 2))
                .addChoice(0.8, () -> new DiscoverNpc(npc))
                .addChoice(0.5, () -> new SeekNpcQuest())
                .addChoice(0.3, () -> new EscortQuest(npc, random.nextInt(4) + 2))
                .chose()
                .get();
    }
}
