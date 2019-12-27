package ro.anud.anud.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.quest.*;
import ro.anud.markovchain.Choice;

import java.util.Random;
import java.util.function.Supplier;

public class GetQuestDilema implements Dilema {

    private Random random = new Random();
    private Npc npc;

    public GetQuestDilema(final Npc npc) {
        this.npc = npc;
    }

    @Override
    public Quest get() {
        System.out.println("New Quest");

        return new Choice<Supplier<Quest>>()
                .addChoice(1, () -> new FetchQuest(npc))
                .addChoice(1, () -> new KillGroup(npc, random.nextInt(4) + 2))
                .addChoice(1, () -> new DiscoverNpc(npc))
                .addChoice(1, () -> new SeekNpcQuest())
                .addChoice(1, () -> new EscortQuest(npc, random.nextInt(4) + 2))
                .chose()
                .get();
    }
}
