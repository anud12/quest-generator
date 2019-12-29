package ro.anud.anud.action;

import ro.anud.anud.activity.Activity;
import ro.anud.anud.npc.Npc;
import ro.anud.anud.npc.NpcRepository;
import ro.anud.anud.quest.*;
import ro.anud.markovchain.Choice;

import java.util.Random;
import java.util.function.Supplier;

import static ro.anud.anud.npc.NpcFilters.validQuestGiven;

public class GetQuestDilema implements Dilema {

    public static Activity givenQuestActivity = () -> "Given quest";

    private Random random = new Random();
    private Npc npc;

    public GetQuestDilema(final Npc npc) {
        System.out.println("New Quest");
        this.npc = npc;
    }

    @Override
    public Quest get() {

        npc.addActivity(givenQuestActivity);

        return new Choice<Supplier<Quest>>()
                .addChoice(1, () -> new FetchQuest(npc))
                .addChoice(1, () -> new KillGroup(npc, random.nextInt(4) + 2))
                .addChoice(1, () -> new DiscoverNpc(npc))
                .addChoice(1, () -> new SeekNpcQuest(NpcRepository.get(validQuestGiven().and(npc1 -> !npc1.getId().equals(npc.getId())), NpcRepository::create)))
                .addChoice(1, () -> new SeekNpcQuest(NpcRepository.create()))
                .addChoice(1, () -> new EscortQuest(npc, random.nextInt(4) + 2))
                .chose()
                .get();
    }
}
