package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.NpcGenerator;
import ro.anud.anud.questgenerator.action.KillImportantDilemma;
import ro.anud.anud.questgenerator.activity.Activity;

import java.util.function.Supplier;

import static ro.anud.anud.npc.NpcFilters.not;
import static ro.anud.anud.npc.NpcFilters.validQuestGiver;

public class KillImportantQuest implements Quest {

    public static Activity killImportantQuestActivity = () -> "Given kill important quest";

    private NpcGenerator npcGenerator;
    private Npc npc;

    public KillImportantQuest(final NpcGenerator npcGenerator, final Npc npc) {
        this.npcGenerator = npcGenerator;
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
            Supplier<Quest> questSupplier = () -> new SeekNpcQuest(npcGenerator, npcGenerator.get(validQuestGiver().and(not(npc))));
            return new KillImportantDilemma(npcGenerator, npc, questSupplier)
                    .get();
        }
        return this;
    }
}
