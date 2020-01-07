package ro.anud.anud;

import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;
import ro.anud.anud.questgenerator.Action;
import ro.anud.anud.questgenerator.QuestScope;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.dilemma.GetQuestDilemma;
import ro.anud.anud.questgenerator.quest.Quest;
import ro.anud.anud.questgenerator.quest.SeekNpcQuest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {
        NpcRepository npcRepository = new NpcRepository();
        ArrayList<Quest> quests = new ArrayList<>();


        QuestScope questScope = new QuestScope(predicate -> npcRepository.get(predicate::test),
                                               quests);
        Activity spawned = () -> "Spawned";

        Stream.generate(() -> spawned)
                .limit(1)
                .forEach(activity -> npcRepository.create());

        var startingQuest = new GetQuestDilemma(questScope, npcRepository.create()).get();
        new SeekNpcQuest(questScope, npcRepository.create());
        ;

        Action action = s -> {};
        System.out.println(action.getName());


        var server = new ServerSocket(3000);
        Supplier<Optional<Socket>> supplier = () -> {
            try {
                return Optional.of(server.accept());
            } catch (IOException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        };
        Stream.generate(supplier)
                .flatMap(Optional::stream)
                .forEach(socket -> {
                    try {
                        var consumer = parseToEvent(npcRepository, quests);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                        while (socket.isConnected()) {
                            var line = reader.readLine();
                            consumer.accept(line, printWriter);
                        }
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });


        npcRepository.getMap()
                .values()
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

    private static BiConsumer<String, PrintWriter> parseToEvent(NpcRepository npcRepository,
                                                                Collection<Quest> quests) {
        return (line, printWriter) -> {
            var array = Arrays.asList(line.split(" "));
            if (array.size() < 2) {
                quests.forEach(quest -> {
                    printWriter.println(quest + " " + quest.getDescription());
                    printWriter.flush();

                });
                return;
            }

            if (array.get(1).toLowerCase().equals("s")) {
                npcRepository.get(npc -> npc.getId().toString().equals(array.get(0)))
                        .addActivity(SeekNpcQuest.found);
            }
            quests.forEach(quest -> {
                printWriter.println(quest + " " + quest.getDescription());
                printWriter.flush();

            });
        };
    }

    private static void parseConsole(NpcRepository npcRepository, Quest startingQuest) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
                npcRepository.getMap().values().stream().map(Npc::prettyString).forEach(System.out::println);
                continue;
            }

            var newQuest = player.getQuest().read(() -> line);
            player.setQuest(newQuest);
        }
    }
}
