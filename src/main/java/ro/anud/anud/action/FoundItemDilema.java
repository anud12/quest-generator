package ro.anud.anud.action;

import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;
import ro.anud.anud.quest.Quest;
import ro.anud.anud.quest.SeekNpcQuest;

public class FoundItemDilema implements Dilema {

    public static Activity lostItem = () -> "Lost item";

    private final Npc targetNpc;

    public FoundItemDilema() {
        System.out.println("Found important item");
        this.targetNpc = NpcRepository.create();
        targetNpc.addActivity(lostItem);
        targetNpc.addActivity(Activity.waitsForPlayer);
    }

    @Override
    public Quest get() {
        return new SeekNpcQuest(targetNpc);
    }
}
