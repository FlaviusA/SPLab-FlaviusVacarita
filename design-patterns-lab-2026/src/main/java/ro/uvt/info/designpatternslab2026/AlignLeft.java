package ro.uvt.info.designpatternslab2026;

public class AlignLeft implements AlignStrategy {
    @Override
    public void render(Paragraph paragraph, RenderContext context) {
        System.out.println(paragraph.getText());
    }
}
