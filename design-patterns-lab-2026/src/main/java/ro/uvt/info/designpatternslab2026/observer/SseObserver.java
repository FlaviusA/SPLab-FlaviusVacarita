package ro.uvt.info.designpatternslab2026.observer;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ro.uvt.info.designpatternslab2026.Book;

import java.io.IOException;
import java.io.UncheckedIOException;

/** ConcreteObserver: pushes each new book to one connected browser as a JSON SSE event. */
public class SseObserver implements Observer<Book> {
    private final SseEmitter emitter;

    public SseObserver(SseEmitter emitter) {
        this.emitter = emitter;
    }

    @Override
    public void update(Book book) {
        try {
            emitter.send(book, MediaType.APPLICATION_JSON);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
