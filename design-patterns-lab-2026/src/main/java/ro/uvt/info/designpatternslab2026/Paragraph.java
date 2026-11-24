package ro.uvt.info.designpatternslab2026;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Paragraph extends BaseElement {
    @Transient
    private static final RenderContext DEFAULT_CONTEXT = new RenderContext(40);

    private String text;

    // Strategies are behavior, not data: not persisted.
    @Transient
    private AlignStrategy textAlignment;

    protected Paragraph() {
    }

    public Paragraph(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @JsonIgnore
    public AlignStrategy getAlignStrategy() {
        return textAlignment;
    }

    public void setAlignStrategy(AlignStrategy strategy) {
        this.textAlignment = strategy;
    }

    public void render() {
        if (textAlignment == null) {
            System.out.println("Paragraph:" + text);
        } else {
            textAlignment.render(this, DEFAULT_CONTEXT);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
