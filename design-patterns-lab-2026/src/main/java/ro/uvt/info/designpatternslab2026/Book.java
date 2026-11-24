package ro.uvt.info.designpatternslab2026;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book extends BaseElement {
    private String bookName;
    private String isbn;

    @OneToMany(targetEntity = BaseElement.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Element> elements = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Author> authors = new ArrayList<>();

    protected Book() {
    }

    public Book(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addContent(Element element) {
        elements.add(element);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void remove(Element element) {
        elements.remove(element);
    }

    public Element get(int index) {
        return elements.get(index);
    }

    /** Replaces name, authors and content with those of {@code other} (keeps id and isbn). */
    public void replaceWith(Book other) {
        this.bookName = other.bookName;
        this.authors.clear();
        this.authors.addAll(other.authors);
        this.elements.clear();
        this.elements.addAll(other.elements);
    }

    public void render() {
        System.out.println("Book: " + bookName);
        System.out.println();

        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
        System.out.println();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}
