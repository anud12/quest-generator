package ro.anud.anud.action;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;
import ro.anud.anud.quest.Quest;
import ro.anud.anud.quest.SeekNpcQuest;

public class FoundImportantItemDilema implements Dilema {

    private final Npc targetNpc;

    public FoundImportantItemDilema() {
        this.targetNpc = NpcRepository.create();
        targetNpc.addHistory("Remembers lost item");
    }

    @Override
    public Quest get() {
        System.out.println("Found important item");
        return new SeekNpcQuest(targetNpc);
    }
}
