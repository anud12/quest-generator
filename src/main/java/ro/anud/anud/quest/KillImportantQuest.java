package ro.anud.anud.quest;

import ro.anud.anud.action.KillImportantDilema;
import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;

import java.util.function.Supplier;

public class KillImportantQuest implements Quest {

    public static Activity killImportantQuestActivity = () -> "Given kill important quest";

    private Npc npc;

    public KillImportantQuest(final Npc npc) {
        this.npc = npc.addActivity(Activity.wanted);
        npc.addActivity(killImportantQuestActivity);
    }

    @Override
    public String getDescription() {
        return "Kill (important) " + npc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("k")) {
            return new KillImportantDilema(npc, () -> new SeekNpcQuest(NpcRepository.create())).get();
        }
        return this;
    }
}
