package ro.anud.anud.npc;

import ro.anud.anud.questgenerator.action.Betrayal;
import ro.anud.anud.questgenerator.activity.Activity;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class NpcFilters {
    public static Predicate<Npc> validQuestGiver() {
        return npc -> isAlive().test(npc)
                & !npc.getActivityHistory().values().contains(Betrayal.betrayerActivity);
    }

    public static Predicate<Npc> isAlive() {
        return npc -> !npc.getActivityHistory().values().containsAll(List.of(
                Activity.slain,
                Activity.wanted
        ));
    }

    public static Predicate<Npc> notIn(Collection<Npc> npcCollection) {
        return npc1 -> !npcCollection.contains(npc1);
    }

    public static Predicate<Npc> not(Npc npc) {
        return npc1 -> !npc1.equals(npc);
    }
}
 