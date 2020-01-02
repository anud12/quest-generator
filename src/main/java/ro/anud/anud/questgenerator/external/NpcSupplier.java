package ro.anud.anud.questgenerator.external;

import ro.anud.anud.npc.Npc;

import java.util.function.Predicate;

public interface NpcSupplier {
    Npc getNpc(Predicate<Npc> predicate);
}
