import javax.swing.*;
//import java.awt.*;
//import java.swing.*;
//import java.awt.*;
//import java.awt.*;


public class Main {
    public static javax.swing.JFrame start() {
        JFrame frame = new JFrame("猫猫爱心平台");//创建窗口，同时设置窗口名字
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置点击窗口左上角 叉号 时，退出程序
        frame.setSize(960,480);//设置窗口大小
        frame.setVisible(true);//设置可见性
        return frame;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginAndCreate().LoginAndRegisterGUI(start());
            }
        });
    }
}