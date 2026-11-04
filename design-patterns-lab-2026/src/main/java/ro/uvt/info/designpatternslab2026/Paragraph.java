package ro.uvt.info.designpatternslab2026;

public class Paragraph implements Element {
    String text;
    Paragraph(String text) {
        this.text = text;
    }
    public void print(){
        System.out.println("Paragraph:"+text);

    }

}
