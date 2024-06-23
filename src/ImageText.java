import javax.swing.*;
import java.awt.*;
public class ImageText extends JPanel {
     String catname; // 猫名字
     String catID; // 猫ID，唯一且不可修改
     String catText; // 猫的简短描述
     Image catimage;

     ImageText(String name, String ID, String text, Image catimage) {
        this.catname = name;
        this.catID = ID;
        this.catText = text;
        this.catimage = catimage;
    }

    public static ImageText createInstance(String name, String ID, String text, Image catimage) {
        return new ImageText(name, ID, text, catimage);
    }
}

