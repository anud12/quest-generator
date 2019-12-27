package ro.anud.anud.npc;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class Npc {


    private static AtomicReference<Integer> generator = new AtomicReference<>(0);
    private Integer id;
    private String name;
    private Map<LocalDateTime, String> history = new HashMap<>();

    public Npc() {
        id = generator.getAndAccumulate(1, Integer::sum);
    }

    public Npc addHistory(String description) {
        history.put(LocalDateTime.now(), description);
        return this;
    }

    public static AtomicReference<Integer> getGenerator() {
        return generator;
    }

    public static void setGenerator(final AtomicReference<Integer> generator) {
        Npc.generator = generator;
    }

    public Integer getId() {
        return id;
    }

    public Npc setId(final Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Npc setName(final String name) {
        this.name = name;
        return this;
    }

    public Map<LocalDateTime, String> getHistory() {
        return history;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Npc)) return false;
        final Npc npc = (Npc) o;
        return Objects.equals(getId(), npc.getId()) &&
                Objects.equals(getName(), npc.getName()) &&
                Objects.equals(getHistory(), npc.getHistory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getHistory());
    }

    @Override
    public String toString() {
        return "Npc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", history=" + history +
                '}';
    }
}
