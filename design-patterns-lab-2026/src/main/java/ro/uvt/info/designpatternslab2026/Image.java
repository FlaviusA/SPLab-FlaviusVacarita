package ro.uvt.info.designpatternslab2026;
import java.util.concurrent.TimeUnit;

public class Image implements Element,Picture{
    String imageName;
    private final Dimension dim;
    private final ImageContent content = new ImageContent();

    public Image(String imageName) {
        this(imageName, null);
    }
    public Image(String imageName, Dimension dim) {
        this.imageName = imageName;
        this.dim = dim;
        try {
            TimeUnit.SECONDS.sleep(5);

        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void print() {
        System.out.println("Image with name:" + imageName);
    }
    @Override
    public String url() {
        return imageName;
    }
    @Override
    public Dimension dim() {
        return dim;
    }
    @Override
    public ImageContent content() {
        return content;
    }

}
