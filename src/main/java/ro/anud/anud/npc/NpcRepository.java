package ro.anud.anud.npc;

import ro.anud.anud.RandomString;

import java.time.temporal.ValueRange;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NpcRepository {

    static private Map<Integer, Npc> npcMap = new HashMap<>();
    static private RandomString randomString = new RandomString(WowHumanMale.names,
                                                                2,
                                                                ValueRange.of(4, 8),
                                                                new Random());

    static public Map<Integer, Npc> getMap() {
        return npcMap;
    }

    static public Npc create() {
        var firstName = randomString.generate();
        var lastName = randomString.generate();
        var npc = new Npc()
                .setName(firstName.substring(0, 1).toUpperCase() + firstName.substring(1)
                                 + " "
                                 + lastName.substring(0, 1).toUpperCase() + lastName.substring(1));
        npcMap.put(npc.getId(), npc);
        return npc;
    }
}
