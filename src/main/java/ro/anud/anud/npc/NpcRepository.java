package ro.anud.anud.npc;

import ro.anud.anud.Position;
import ro.anud.anud.RandomString;

import java.time.temporal.ValueRange;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class NpcRepository {

    private Map<Integer, Npc> npcMap = new HashMap<>();
    private Random random = new Random();
    private RandomString randomString = new RandomString(Azeroth.humanMale,
                                                         2,
                                                         ValueRange.of(4, 8),
                                                         new Random());

    public Map<Integer, Npc> getMap() {
        return npcMap;
    }

    public Npc get(Predicate<Npc> predicate) {
        return get(predicate, this::create);
    }

    public Npc get(Predicate<Npc> predicate, Supplier<Npc> supplier) {
        return npcMap.values().stream()
                .sorted((npc, t1) -> (int) (Math.random() - 0.5))
                .filter(predicate)
                .findAny()
                .orElseGet(supplier);

    }

    public Npc create() {
        var firstName = randomString.generate();
        var lastName = randomString.generate();
        var npc = new Npc()
                .setName(firstName.substring(0, 1).toUpperCase() + firstName.substring(1)
                                 + " "
                                 + lastName.substring(0, 1).toUpperCase() + lastName.substring(1))
                .setPosition(new Position(random.nextInt(10), random.nextInt(10)));
        npcMap.put(npc.getId(), npc);
        System.out.println("NpcRepository - create : " + npc);
        return npc;
    }
}
