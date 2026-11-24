package ro.uvt.info.designpatternslab2026;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

import java.util.concurrent.TimeUnit;

@Entity
public class Image extends BaseElement implements Picture {
    private String imageName;

    @Embedded
    private Dimension dim;

    @Transient
    private final ImageContent content = new ImageContent();

    protected Image() {
    }

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

    public String getImageName() {
        return imageName;
    }

    public void render() {
        System.out.println("Image with name:" + imageName);
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
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
