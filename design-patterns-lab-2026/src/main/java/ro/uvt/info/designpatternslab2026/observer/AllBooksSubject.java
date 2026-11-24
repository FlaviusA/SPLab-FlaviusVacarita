package ro.uvt.info.designpatternslab2026.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ro.uvt.info.designpatternslab2026.Book;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/** ConcreteSubject: its state change is "a new book was added". */
@Component
public class AllBooksSubject implements Subject<Book> {
    private static final Logger log = LoggerFactory.getLogger(AllBooksSubject.class);

    private final List<Observer<Book>> observers = new CopyOnWriteArrayList<>();

    @Override
    public void registerObserver(Observer<Book> observer) {
        observers.add(observer);
    }

    @Override
    public void unregisterObserver(Observer<Book> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Book book) {
        for (Observer<Book> observer : observers) {
            try {
                observer.update(book);
            } catch (RuntimeException e) {
                // a dead client must not break the others
                log.info("Dropping observer: {}", e.getMessage());
                observers.remove(observer);
            }
        }
    }

    /** Changes the subject's state and notifies all observers. */
    public void add(Book book) {
        notifyObservers(book);
    }
}
