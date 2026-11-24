package ro.uvt.info.designpatternslab2026;

public record Dimension(int width, int height)
{
 public Dimension {
    if( width<0 || height<0 )
        throw new IllegalArgumentException();
 }
}
