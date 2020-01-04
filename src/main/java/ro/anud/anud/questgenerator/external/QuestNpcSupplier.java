package ro.anud.anud.questgenerator.external;

import java.util.function.Predicate;

public interface QuestNpcSupplier {
    QuestNpc getNpc(Predicate<QuestNpc> predicate);
}
