package ro.anud.anud.questgenerator.quest;

import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.external.QuestNpc;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.function.Supplier;

public class SeekNpcQuest implements Quest {

    public static Activity seekNpcQuestActivity = () -> "Seek target";
    public static Activity found = () -> "Found npc";

    private QuestScope questScope;
    private QuestNpc npc;

    public SeekNpcQuest(final QuestScope questScope, final QuestNpc npc) {
        this.questScope = questScope;
        this.npc = npc;
        LocalDateTime lastEntry = npc.getActivityHistory().keySet()
                .stream()
                .max(Comparator.naturalOrder())
                .orElse(LocalDateTime.now());

        questScope.addQuest(this);
        System.out.println(npc.getActivityHistory());
        System.out.println(lastEntry);
        npc.addActivity(seekNpcQuestActivity)
                .subscribeOnChanges((npc1, unsubscribe) -> {
                    if (npc1.getActivityHistory().entrySet()
                            .stream()
                            .filter(e -> e.getKey().isAfter(lastEntry))
                            .anyMatch(e -> e.getValue().equals(found))) {
                        questScope.removeQuest(this);

                        new ClaimRewardQuest(questScope, npc);
                        unsubscribe.run();
                    }
                });
    }

    @Override
    public String getDescription() {
        return "Seek " + npc.getName() + " : ";
    }

    @Override
    public Quest read(final Supplier<String> s) {
        if (s.get().equals("s")) {
            npc.addActivity(found);
            return new ClaimRewardQuest(questScope, npc);
        }
        return this;
    }
}
