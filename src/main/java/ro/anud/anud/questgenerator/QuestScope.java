package ro.anud.anud.questgenerator;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.questgenerator.external.NpcSupplier;

import java.util.function.Predicate;

public class QuestScope implements NpcSupplier {
    private NpcSupplier npcSupplier;


    public QuestScope setNpcSupplier(final NpcSupplier npcSupplier) {
        this.npcSupplier = npcSupplier;
        return this;
    }

    @Override
    public Npc getNpc(final Predicate<Npc> predicate) {
        return npcSupplier.getNpc(predicate);
    }
}
