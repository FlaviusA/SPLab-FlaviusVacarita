package ro.uvt.info.designpatternslab2026;

import jakarta.persistence.Entity;

@Entity
@jakarta.persistence.Table(name = "Tables") // "Table" is a reserved SQL word
public class Table extends BaseElement {
    private String title;

    protected Table() {
    }

    public Table(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void render() {
        System.out.println("Table:" + title);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
