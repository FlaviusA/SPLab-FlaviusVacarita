package ro.uvt.info.designpatternslab2026;

public class AlignCenter implements AlignStrategy {
    @Override
    public void render(Paragraph paragraph, RenderContext context) {
        String text = paragraph.getText();
        int pad = Math.max(0, (context.width() - text.length()) / 2);
        System.out.println(" ".repeat(pad) + text);
    }
}
