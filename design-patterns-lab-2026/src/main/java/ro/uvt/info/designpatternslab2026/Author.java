package ro.uvt.info.designpatternslab2026;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;

    protected Author() {
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(String fullName) {
        int lastSpace = fullName.lastIndexOf(' ');
        if (lastSpace == -1) { // only one word
            this.name = fullName;
            this.surname = "";
        } else {
            this.name = fullName.substring(0, lastSpace);
            this.surname = fullName.substring(lastSpace + 1);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void print() {
        System.out.println("Author: " + name + " " + surname);
    }
}
