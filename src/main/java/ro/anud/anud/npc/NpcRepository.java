package ro.anud.anud.npc;

import java.util.HashMap;
import java.util.Map;

public class NpcRepository {

    static private Map<Integer, Npc> npcMap = new HashMap<>();

    static public Map<Integer, Npc> getMap() {
        return npcMap;
    }

    static public Npc create() {
        var npc = new Npc();
        npcMap.put(npc.getId(), npc);
        return npc;
    }
}
