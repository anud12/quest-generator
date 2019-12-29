package ro.anud.anud.npc;

import ro.anud.anud.action.Betrayal;
import ro.anud.anud.action.GetQuestDilema;
import ro.anud.anud.activity.Activity;

import java.util.List;
import java.util.function.Predicate;

public class NpcFilters {
    public static Predicate<Npc> validQuestGiven() {
        return npc -> !npc.getActivityHistory().values().containsAll(List.of(
                GetQuestDilema.givenQuestActivity,
                Activity.slain,
                Activity.wanted,
                Betrayal.betrayerActivity
        ));
    }
}
 