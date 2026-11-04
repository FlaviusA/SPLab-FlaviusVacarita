package ro.uvt.info.designpatternslab2026;

public interface Element {
    void print();
    default void add(Element element){ throw new UnsupportedOperationException(); } //For the Section part(composite only)
    default void remove(Element element) { throw new UnsupportedOperationException(); } // for the section part(composite)
    default Element get(int index) { throw new UnsupportedOperationException(); } //composite part


}
