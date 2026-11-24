package ro.uvt.info.designpatternslab2026.command;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/** Keeps track of accepted asynchronous requests so clients can poll their status. */
@Component
public class TaskRegistry {
    private final Map<String, CompletableFuture<?>> tasks = new ConcurrentHashMap<>();

    public String register(CompletableFuture<?> future) {
        String id = UUID.randomUUID().toString();
        tasks.put(id, future);
        return id;
    }

    public TaskStatus status(String id) {
        CompletableFuture<?> future = tasks.get(id);
        if (future == null) throw new NoSuchElementException("Task " + id + " not found");
        if (!future.isDone()) return new TaskStatus(id, "PENDING", null, null);
        if (future.isCompletedExceptionally()) {
            try {
                future.join();
            } catch (RuntimeException e) {
                Throwable cause = e.getCause() != null ? e.getCause() : e;
                return new TaskStatus(id, "FAILED", null, cause.getMessage());
            }
        }
        return new TaskStatus(id, "DONE", future.join(), null);
    }
}
