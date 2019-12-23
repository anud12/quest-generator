package ro.anud.anud.quest;

import ro.anud.anud.npc.Npc;

import java.util.function.Supplier;

public class FetchQuest implements Quest {

    private Npc npc;

    public FetchQuest(final Npc npc) {
        this.npc = npc;
        npc.addHistory("Given Fetch quest");
    }

    @Override
    public Quest read(final Supplier<String> s) {
        System.out.print("Fetch quest : ");
        if (s.get().equals("f")) {
            return new TurnInQuest(npc);
        }
        return this;
    }
}
