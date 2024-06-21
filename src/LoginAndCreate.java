import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginAndCreate {
     javax.swing.JFrame frame;//创建窗口
     JPanel loginPanel;
     JPanel registerPanel;

    public void LoginAndRegisterGUI(javax.swing.JFrame frame) {
        //frame = new JFrame("登录");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame = frame;
        loginPanel = createLoginPanel();// 创建登录界面
        registerPanel = createRegisterPanel();// 创建创建账号界面


        showLoginPanel();// 默认显示登录界面

        //frame.pack();
        //frame.setVisible(true);
    }//创建登录账号页面以及创建账号页面，默认显现登录账号页面

     JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //constraints.insets = new Insets(50, 50, 50, 50);
        //设置输入框与登录按钮
        JLabel usernameLabel = new JLabel("账号:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("登录");

        //监听登录按钮的按下
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理登录逻辑
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                // 这里可以添加登录验证逻辑
                //校验函数
                //对比数据库内容，如果匹敌，登录跳转页面，如果不匹敌，显示账号或密码错误

                JOptionPane.showMessageDialog(panel, "点击登录按钮");//按下就出现
            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, constraints);

        JLabel registerLabel = new JLabel("没有账号？去注册");
        registerLabel.setForeground(Color.BLUE.darker());
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showRegisterPanel();
            }
        });

        constraints.gridy = 3;
        panel.add(registerLabel, constraints);

        return panel;
    }

    JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //constraints.insets = new Insets(50, 50, 50, 50);

        JLabel usernameLabel = new JLabel("账号");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密码");
        JPasswordField passwordField = new JPasswordField(20);
        JLabel rightpasswordLabel = new JLabel("确认密码");
        JPasswordField rightpasswordField = new JPasswordField(20);
        JButton registerButton = new JButton("创建");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理注册逻辑
                String username = usernameField.getText();
                String password = passwordField.getText();
                String rightpassword = rightpasswordField.getText();
                // 这里可以添加注册逻辑
                if(password.equals(rightpassword))
                {
                    JOptionPane.showMessageDialog(panel, "点击注册按钮");
                }
                else
                {
                    JOptionPane.showMessageDialog(panel, "注册失败，密码不匹配");
                }

            }
        });

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);

        constraints.gridx = 1;
        panel.add(usernameField, constraints);

        constraints.gridy = 1;
        constraints.gridx = 0;
        panel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        constraints.gridy = 2;
        constraints.gridx = 0;
        panel.add(rightpasswordLabel,constraints);

        constraints.gridx = 1;
        panel.add(rightpasswordField,constraints);

        constraints.gridy = 3;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, constraints);

        JLabel loginLabel = new JLabel("已有账号，去登录");
        loginLabel.setForeground(Color.BLUE.darker());//设置颜色
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标靠近改变鼠标样式
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showLoginPanel();
            }
        });
        //设置位置
        constraints.gridy = 4;
        panel.add(loginLabel, constraints);

        return panel;
    }

     void showLoginPanel() {
        frame.getContentPane().removeAll();
        frame.add(loginPanel);
        frame.revalidate();
        frame.repaint();
        //frame.pack();
    }

     void showRegisterPanel() {
        frame.getContentPane().removeAll();
        frame.add(registerPanel);
        frame.revalidate();
        frame.repaint();
        //frame.pack();
    }
}