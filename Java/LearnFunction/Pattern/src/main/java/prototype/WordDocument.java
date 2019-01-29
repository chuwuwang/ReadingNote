package prototype;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 文档类型
 * 是ConcretePrototype角色, 而Cloneable是代表prototype角色
 */
public class WordDocument implements Cloneable {

    private String text;
    private ArrayList<String> imageList = new ArrayList<>();

    public WordDocument() {
        System.out.println("--------document constructor--------");
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object clone() {
        try {
            WordDocument doc = (WordDocument) super.clone();
            doc.text = this.text;
            doc.imageList = (ArrayList<String>) this.imageList.clone();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<String> imageList) {
        this.imageList = imageList;
    }

    public void addImage(String image) {
        imageList.add(image);
    }

    /**
     * 打印文档内容
     */
    public void showDocument() {
        System.out.println("--------document start--------");
        System.out.println("Text : " + text);
        System.out.println("Images List : ");
        for (String name : imageList) {
            System.out.println("image name : " + name);
        }
        System.out.println("--------document end--------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        boolean b = o == null || getClass() != o.getClass();
        if (b) return false;
        WordDocument that = (WordDocument) o;
        return Objects.equals(text, that.text) &&
                Objects.equals(imageList, that.imageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, imageList);
    }


}
