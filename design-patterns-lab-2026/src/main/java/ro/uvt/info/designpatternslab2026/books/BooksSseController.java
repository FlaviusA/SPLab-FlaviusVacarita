package ro.uvt.info.designpatternslab2026.books;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ro.uvt.info.designpatternslab2026.observer.AllBooksSubject;
import ro.uvt.info.designpatternslab2026.observer.SseObserver;

@RestController
public class BooksSseController {
    private final AllBooksSubject allBooksSubject;

    public BooksSseController(AllBooksSubject allBooksSubject) {
        this.allBooksSubject = allBooksSubject;
    }

    @GetMapping("/books-sse")
    public ResponseBodyEmitter getBooksSse() {
        final SseEmitter emitter = new SseEmitter(0L); // never times out
        SseObserver observer = new SseObserver(emitter);
        allBooksSubject.registerObserver(observer);
        Runnable detach = () -> allBooksSubject.unregisterObserver(observer);
        emitter.onCompletion(detach);
        emitter.onTimeout(detach);
        emitter.onError(e -> detach.run());
        return emitter;
    }
}
