package ro.anud.anud.quest;

import ro.anud.anud.action.KillDilema;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class KillGroup implements Quest {
    private int number;

    private Map<Integer, Npc> npcMap = new HashMap<>();
    private Npc npc;

    public KillGroup(Npc npc, int number) {
        this.npc = npc;
        npc.addHistory("Given Kill quest");
        this.number = number;
        Stream.iterate(0, integer -> integer + 1)
                .limit(number)
                .forEach(integer -> {
                    npcMap.put(integer, NpcRepository.create().addHistory("Marked for death"));
                });
    }

    @Override
    public Quest read(final Supplier<String> s) {
        System.out.print("Kill quest - " + number + ": ");
        if (!s.get().equals("k")) {
            return this;
        }

        this.number = this.number - 1;
        npcMap.get(this.number).addHistory("Killed");
        if (this.number <= 0) {
            return new TurnInQuest(npc);
        }

        return new KillDilema(() -> this).get();
    }
}
