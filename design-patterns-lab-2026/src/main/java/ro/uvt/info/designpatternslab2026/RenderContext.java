package ro.uvt.info.designpatternslab2026;

/**
 * Rendering context passed to an AlignStrategy.
 * Holds the state a strategy needs but a Paragraph does not own: the line width.
 */
public record RenderContext(int width) {
    public RenderContext {
        if (width <= 0) throw new IllegalArgumentException("width must be positive");
    }
}
