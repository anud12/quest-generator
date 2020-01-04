package ro.anud.anud.questgenerator;

import ro.anud.anud.questgenerator.external.QuestNpc;
import ro.anud.anud.questgenerator.external.QuestNpcSupplier;
import ro.anud.anud.questgenerator.quest.Quest;

import java.util.Collection;
import java.util.function.Predicate;

public class QuestScope implements QuestNpcSupplier {

    private QuestNpcSupplier questNpcSupplier;
    private Collection<Quest> questList;

    public QuestScope(final QuestNpcSupplier questNpcSupplier,
                      final Collection<Quest> questList) {
        this.questNpcSupplier = questNpcSupplier;
        this.questList = questList;
    }

    @Override
    public QuestNpc getNpc(final Predicate<QuestNpc> predicate) {
        return questNpcSupplier.getNpc(predicate);
    }

    public QuestScope removeQuest(Quest quest) {
        questList.remove(quest);
        return this;
    }

    public QuestScope addQuest(Quest quest) {
        questList.add(quest);
        return this;
    }
}
