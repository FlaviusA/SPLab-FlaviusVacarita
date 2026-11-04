package ro.uvt.info.designpatternslab2026;
import java.util.ArrayList;
import java.util.List;
public class Section implements Element{
    private final String title;
    private final List<Element> children= new ArrayList<>();
    Section(String title) {
        this.title = title;
    }
    public void print(){
        System.out.println(title);
        for(Element child: children){
            child.print();
        }



    }
    public void add(Element element){
        children.add(element);


    }
    public void remove(Element element){
        children.remove(element);
    }
    public Element get(int index){
        return children.get(index);
    }





}
