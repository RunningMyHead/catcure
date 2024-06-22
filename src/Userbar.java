import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Userbar {
    public static void Useruse(JMenuItem userInformation,JMenuItem userEXIT,JMenuItem usermodify) {

        userInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //查看个人信息的panel
                //名字与账号
            }
        });//实现个人信息功能

        usermodify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //查看修改信息的panel
                //名字与密码
            }
        });//实现修改信息功能

        userEXIT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginAndCreate.showLoginPanel();
            }
        });//实现退出功能
    }
}
