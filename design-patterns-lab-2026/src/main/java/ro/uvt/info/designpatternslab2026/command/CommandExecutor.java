package ro.uvt.info.designpatternslab2026.command;

import java.util.concurrent.CompletableFuture;

/** The invoker: decides HOW a command runs (same thread vs. thread pool). */
public interface CommandExecutor {
    <T> CompletableFuture<T> submit(Command<T> command);
}
