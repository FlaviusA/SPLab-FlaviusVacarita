package ro.uvt.info.designpatternslab2026;

public class ImageProxy implements Element, Picture {
    String url;
    Dimension dim;
    Image realImg=null;
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
    @Override
    public void print() {
        loadImage().print();
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
