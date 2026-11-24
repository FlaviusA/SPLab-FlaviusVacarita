package ro.uvt.info.designpatternslab2026.command;

import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class SynchronousCommandExecutor implements CommandExecutor {
    @Override
    public <T> CompletableFuture<T> submit(Command<T> command) {
        try {
            return CompletableFuture.completedFuture(command.execute());
        } catch (RuntimeException e) {
            return CompletableFuture.failedFuture(e);
        }
    }
}
