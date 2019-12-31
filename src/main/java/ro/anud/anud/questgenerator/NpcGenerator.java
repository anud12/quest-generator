package ro.anud.anud.questgenerator;

import ro.anud.anud.npc.Npc;

import java.util.function.Predicate;

public interface NpcGenerator {
    Npc get(Predicate<Npc> predicate);
}
