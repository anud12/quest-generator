package ro.anud.anud.quest;

import ro.anud.anud.action.KillAction;
import ro.anud.anud.action.KillImportantAction;
import ro.anud.anud.npc.Npc;

import java.util.function.Supplier;

public class KillImportantQuest implements Quest {

    private Npc npc;

    public KillImportantQuest(final Npc npc) {
        this.npc = npc;
        npc.addHistory("Marked as important");
    }

    @Override
    public Quest read(final Supplier<String> s) {
        System.out.print("Kill important : ");
        if (s.get().equals("k")) {
            return new KillImportantAction(npc, SeekNpcQuest::new).get();
        }
        return this;
    }
}
