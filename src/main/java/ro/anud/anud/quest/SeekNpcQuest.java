package ro.anud.anud.quest;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;

import java.util.function.Supplier;

public class SeekNpcQuest implements Quest {

    private Npc npc;

    public SeekNpcQuest() {
        this(NpcRepository.create());
    }

    public SeekNpcQuest(final Npc npc) {
        npc.addHistory("Waits for player");
        this.npc = npc;
    }

    @Override
    public Quest read(final Supplier<String> s) {
        System.out.print("Seeking npc : ");
        if (s.get().equals("s")) {
            return new TurnInQuest(npc);
        }
        return this;
    }
}
