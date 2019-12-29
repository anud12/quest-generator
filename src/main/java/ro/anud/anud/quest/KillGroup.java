package ro.anud.anud.quest;

import ro.anud.anud.action.KillDilema;
import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class KillGroup implements Quest {

    public static Activity killGroupQuestActivity = () -> "Given kill group quest";

    private int targetNumber;
    private int number;

    private Map<Integer, Npc> npcMap = new HashMap<>();
    private Npc npc;

    public KillGroup(Npc npc, int number) {
        this.npc = npc;
        npc.addActivity(killGroupQuestActivity);
        this.targetNumber = number;
        Stream.iterate(0, integer -> integer + 1)
                .limit(number)
                .forEach(integer -> {
                    npcMap.put(integer, NpcRepository.create().addActivity(Activity.wanted));
                });
    }

    @Override
    public String getDescription() {
        return "Kill for " + npc.getName() + " " + number + " of " + targetNumber + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (!s.get().equals("k")) {
            return this;
        }

        if (this.number >= targetNumber) {
            return new ClaimRewardQuest(npc);
        }

        return new KillDilema(npcMap.get(this.number), () -> {
            this.number = this.number + 1;
            return this;
        }).get();
    }
}
