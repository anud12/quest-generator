package ro.anud.anud.npc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Npc {


    private static AtomicReference<Integer> generator = new AtomicReference<>(0);
    private Integer id;

    private Map<LocalDateTime, String> history = new HashMap<>();

    public Npc() {
        id = generator.getAndAccumulate(1, Integer::sum);
    }

    public Npc addHistory(String description) {
        history.put(LocalDateTime.now(), description);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Map<LocalDateTime, String> getHistory() {
        return history;
    }

    @Override
    public String toString() {
        return "Npc{" +
                "id=" + id +
                ", history=" + history +
                '}';
    }
}
