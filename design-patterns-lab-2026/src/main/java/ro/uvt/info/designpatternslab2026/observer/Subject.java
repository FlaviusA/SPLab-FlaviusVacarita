package ro.uvt.info.designpatternslab2026.observer;

public interface Subject<T> {
    void registerObserver(Observer<T> observer);
    void unregisterObserver(Observer<T> observer);
    void notifyObservers(T value);
}
