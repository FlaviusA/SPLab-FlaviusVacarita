package ro.uvt.info.designpatternslab2026;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

@Entity
public class TableOfContents extends BaseElement {
    @ElementCollection
    private List<String> entries = new ArrayList<>();

    public void addEntry(String title) {
        entries.add(title);
    }

    public List<String> getEntries() {
        return List.copyOf(entries);
    }

    public void render() {
        System.out.println("Table of Contents:");
        int i = 1;
        for (String entry : entries) {
            System.out.println(i++ + ". " + entry);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
