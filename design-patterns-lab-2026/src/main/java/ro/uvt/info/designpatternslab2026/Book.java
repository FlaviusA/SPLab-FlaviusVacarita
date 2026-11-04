package ro.uvt.info.designpatternslab2026;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private final String bookName;
    private final List<Element> elements=new ArrayList<>();
    private final List<Author> authors= new ArrayList<>();

    Book(String bookName) {
        this.bookName = bookName;
    }
    public void addContent(Element element)
    {
        elements.add(element);

    }
    public void addAuthor(Author author)
    {
        authors.add(author);
    }
    public void remove(Element element)
        {
        elements.remove(element);
        }
    public Element get(int index){
        return elements.get(index);
    }
    public void print() {
        System.out.println("Book: " + bookName);
        System.out.println();

        System.out.println("Authors:");
        for (Author author : authors) {
            author.print();
        }
        System.out.println();

        for (Element element : elements) {
            element.print();
        }
    }

}
