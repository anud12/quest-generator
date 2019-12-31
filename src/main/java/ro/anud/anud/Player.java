package ro.anud.anud;

import ro.anud.anud.questgenerator.quest.Quest;

public class Player {
    private Position position;
    private Quest quest;

    public Player(final Position position, final Quest quest) {
        this.position = position;
        this.quest = quest;
    }

    public Position getPosition() {
        return position;
    }

    public Quest getQuest() {
        return quest;
    }

    public Player setQuest(Quest quest) {
        this.quest = quest;
        return this;
    }
}
