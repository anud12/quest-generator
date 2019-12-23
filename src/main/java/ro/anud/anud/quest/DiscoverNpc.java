package ro.anud.anud.quest;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;

import java.util.function.Supplier;

public class DiscoverNpc implements Quest {

    private Npc targetNpc = NpcRepository.create();

    private Npc npc;

    public DiscoverNpc(final Npc npc) {
        this.npc = npc;
        npc.addHistory("Given Discover Npc quest");
        targetNpc.addHistory("Waiting to be discovered");
    }

    @Override
    public Quest read(final Supplier<String> s) {
        System.out.print("Discover npc : ");
        if (s.get().equals("d")) {
            targetNpc.addHistory("Discovered");
            return new TurnInQuest(npc);
        }
        return this;
    }
}
