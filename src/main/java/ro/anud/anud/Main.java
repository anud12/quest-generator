package ro.anud.anud;

import ro.anud.anud.action.GetQuestDilema;
import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        Activity spawned = () -> "Spawned";

        Stream.generate(() -> spawned)
                .limit(4)
                .forEach(activity -> {
                    NpcRepository.create();
                });

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        var startingQuest = new GetQuestDilema(NpcRepository.create()).get();
        var player = new Player(new Position(0, 0), startingQuest);
        var keepGoing = new AtomicBoolean(true);

        while (keepGoing.get()) {
            var quest = player.getQuest();
            System.out.print(quest.getDescription());
            var line = reader.readLine();

            if (line.equals("desc")) {
                player.getQuest().getDescription();
                continue;
            }
            if (line.equals("exit")) {
                keepGoing.set(false);
                continue;
            }
            if (line.equals("listNpc")) {
                NpcRepository.getMap().values().stream().map(Npc::prettyString).forEach(System.out::println);
                continue;
            }

            var newQuest = player.getQuest().read(() -> line);
            player.setQuest(newQuest);
        }

        NpcRepository.getMap().values()
                .stream()
                .flatMap(npc -> npc.getActivityHistory().entrySet()
                        .stream()
                        .map(entry -> new AbstractMap.SimpleEntry<>(
                                entry.getKey(),
                                npc.getId() + " - " + npc.getName() + " : " + entry.getValue().getDescription())
                        )
                )
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(entry -> {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                });
    }
}
