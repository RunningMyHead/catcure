import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Publishbar {
    public static void Publishmenu(JMenuItem UserPosts,JMenuItem newUserPosts)
    {
        UserPosts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //管理
            }
        });//实现颁布帖子功能

        newUserPosts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //管理
            }
        });//实现删除已颁布帖子功能
    }
}
