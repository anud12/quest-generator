package ro.anud.anud.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;
import ro.anud.anud.quest.Quest;
import ro.anud.anud.quest.SeekNpcQuest;

import java.util.function.Supplier;

public class FoundImportantItemAction implements Action {

    private final Npc targetNpc;

    public FoundImportantItemAction() {
        this.targetNpc = NpcRepository.create();
        targetNpc.addHistory("Remembers lost item");
    }

    @Override
    public Quest get() {
        System.out.println("Found important item");
        return new SeekNpcQuest(targetNpc);
    }
}
