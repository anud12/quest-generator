package ro.anud.anud.questgenerator.external;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.activity.Activity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.BiConsumer;

public interface QuestNpc {
    String getName();

    QuestNpc addActivity(Activity activity);

    QuestNpc subscribeOnChanges(BiConsumer<Npc, Runnable> npcConsumer);

    Map<LocalDateTime, Activity> getActivityHistory();
}
