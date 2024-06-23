import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hallbar {
    public static void Hallmenu(JMenuItem catlook,JFrame frame, String userID) {
        catlook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showHallPanel(frame,userID);
                //显示大厅
            }
        });
    }//实现大厅功能

    public static JPanel HallPanel(){
        JPanel Panel = new JPanel();

        return Panel;
    }
    public static void showHallPanel(JFrame frame, String userID) {

    }

}
