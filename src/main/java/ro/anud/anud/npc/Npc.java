package ro.anud.anud.npc;

import ro.anud.anud.Position;
import ro.anud.anud.questgenerator.activity.Activity;
import ro.anud.anud.questgenerator.external.QuestNpc;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Npc implements QuestNpc {

    private static AtomicReference<Integer> generator = new AtomicReference<>(0);

    private Integer id;
    private String name;
    private Position position = new Position();
    private Map<LocalDateTime, Activity> history = new HashMap<>();
    private List<Consumer<Npc>> consumerList = new ArrayList<>();

    public Npc() {
        id = generator.getAndAccumulate(1, Integer::sum);
    }

    @Override
    public Runnable subscribeOnChanges(final Consumer<Npc> npcConsumer) {
        consumerList.add(npcConsumer);
        return () -> consumerList.remove(npcConsumer);
    }

    public Npc addActivity(Activity activity) {
        history.put(LocalDateTime.now(), activity);
        System.out.println(LocalDateTime.now() + " - " + name + " : " + activity.getDescription());
        consumerList.forEach(npcConsumer -> npcConsumer.accept(this));
        return this;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Npc setName(final String name) {
        this.name = name;
        consumerList.forEach(npcConsumer -> npcConsumer.accept(this));
        return this;
    }

    public Position getPosition() {
        return position;
    }

    public Npc setPosition(final Position position) {
        this.position = position;
        consumerList.forEach(npcConsumer -> npcConsumer.accept(this));
        return this;
    }

    public Map<LocalDateTime, Activity> getActivityHistory() {
        return history;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Npc)) return false;
        final Npc npc = (Npc) o;
        return Objects.equals(getId(), npc.getId()) &&
                Objects.equals(getName(), npc.getName()) &&
                Objects.equals(getActivityHistory(), npc.getActivityHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getActivityHistory());
    }

    @Override
    public String toString() {
        return "Npc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", history=" + history +
                '}';
    }

    public String prettyString() {
        return "Npc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", history=" + history.entrySet()
                .stream()
                .map(e -> "\n\t" + e.getKey() + " : " + e.getValue().getDescription())
                .collect(Collectors.joining()) +
                "}";
    }
}
