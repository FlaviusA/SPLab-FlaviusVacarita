package ro.uvt.info.designpatternslab2026;

public class Author {
    final String name;
    final String surname;
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
    public void print(){

        System.out.println("Author: " + name + " " + surname);

}

}
