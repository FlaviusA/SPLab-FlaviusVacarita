package ro.uvt.info.designpatternslab2026;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class ImageProxy extends BaseElement implements Picture {
    private String url;

    @Embedded
    private Dimension dim;

    @Transient
    private Image realImg = null;

    protected ImageProxy() {
    }

    public ImageProxy(String url) {
        this.url = url;
    }
    public ImageProxy(String url, Dimension dim) {
        this.url = url;
        this.dim = dim;
    }
    private Image loadImage() {
        if(realImg==null){
            realImg = new Image(url, dim);
        }
        return realImg;
    }

    public String getUrl() {
        return url;
    }

    public void render() {
        loadImage().render();
    }
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
    @Override
    public String url()
    {
        return url;
    }
    @Override
    public Dimension dim()
    {
        return dim;
    }
    @Override
    public PictureContent content()
    {
        return loadImage().content();
    }
}
