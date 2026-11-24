package ro.uvt.info.designpatternslab2026.books;

import ro.uvt.info.designpatternslab2026.Author;
import ro.uvt.info.designpatternslab2026.Book;
import ro.uvt.info.designpatternslab2026.Paragraph;

import java.util.List;

/** JSON body for POST/PUT /books. */
public record BookRequest(String title, List<String> authors, List<String> paragraphs) {
    public Book toBook() {
        Book book = new Book(title);
        if (authors != null) authors.forEach(a -> book.addAuthor(new Author(a)));
        if (paragraphs != null) paragraphs.forEach(p -> book.addContent(new Paragraph(p)));
        return book;
    }
}
