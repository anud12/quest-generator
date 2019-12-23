package ro.anud.anud;

import ro.anud.anud.npc.NpcRepository;
import ro.anud.anud.action.GetQuestAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        var currentChain = new AtomicReference<>(new GetQuestAction(NpcRepository.create()).get());
        var keepGoing = new AtomicBoolean(true);
        while (keepGoing.get()) {
            currentChain.updateAndGet(newQuest -> newQuest.read(() -> {
                try {
                    var line = reader.readLine();
                    if (line.equals("exit")) {
                        keepGoing.set(false);
                    }
                    return line;

                } catch (IOException e) {
                    e.printStackTrace();
                    return "";
                }
            }));
        }

        NpcRepository.getMap().values()
                .stream()
                .flatMap(npc -> npc.getHistory().entrySet()
                        .stream()
                        .peek(entry -> entry.setValue(npc.getId() + " : " + entry.getValue()))
                )
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(entry -> {
                    System.out.println(entry.getKey() + " : " + entry.getValue());
                });
    }
}
