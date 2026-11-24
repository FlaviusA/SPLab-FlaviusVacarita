package ro.uvt.info.designpatternslab2026.command;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Thread pool pattern: queued commands are picked up by a fixed pool of workers. */
@Component
public class AsynchronousCommandExecutor implements CommandExecutor {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);

    @Override
    public <T> CompletableFuture<T> submit(Command<T> command) {
        return CompletableFuture.supplyAsync(command::execute, pool);
    }

    @PreDestroy
    void shutdown() {
        pool.shutdown();
    }
}
