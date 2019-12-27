package ro.anud.anud.quest;

import ro.anud.anud.action.GetQuestDilema;
import ro.anud.anud.npc.Npc;

import java.util.function.Supplier;

public class TurnInQuest implements Quest {


    private Npc npc;

    public TurnInQuest(final Npc npc) {
        this.npc = npc;
        npc.addHistory("Acknowledged quest");
    }

    @Override
    public Quest read(final Supplier<String> s) {
        System.out.print("Turn in quest: ");
        if (s.get().equals("t")) {
            return new GetQuestDilema(npc).get();
        }
        return this;
    }
}
