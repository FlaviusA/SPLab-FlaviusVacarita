package ro.uvt.info.designpatternslab2026.books;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/** Simulates an external ISBN provider with an uncommitted SLA (slow, unpredictable). */
@Service
public class IsbnService {
    public String obtainIsbn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for ISBN", e);
        }
        return "978-" + ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
    }
}
