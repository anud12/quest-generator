package ro.anud.anud.npc;

import ro.anud.anud.questgenerator.dilemma.Betrayal;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.external.QuestNpc;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class NpcFilters {
    public static Predicate<QuestNpc> validQuestGiver() {
        return npc -> isAlive().test(npc)
                & !npc.getActivityHistory().values().contains(Betrayal.betrayerActivity);
    }

    public static Predicate<QuestNpc> isAlive() {
        return npc -> !npc.getActivityHistory().values().containsAll(List.of(
                Activity.slain,
                Activity.wanted
        ));
    }

    public static Predicate<QuestNpc> notIn(Collection<QuestNpc> npcCollection) {
        return npc1 -> !npcCollection.contains(npc1);
    }

    public static Predicate<QuestNpc> not(QuestNpc npc) {
        return npc1 -> !npc1.equals(npc);
    }
}
 