package ro.anud.anud.questgenerator.external;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.activity.Activity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;

public interface QuestNpc {
    String getName();

    QuestNpc addActivity(Activity activity);

    Runnable subscribeOnChanges(Consumer<Npc> npcConsumer);

    Map<LocalDateTime, Activity> getActivityHistory();
}
