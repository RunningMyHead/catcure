import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Userbar {
    public static void Useruse(JMenuItem userInformation,JMenuItem userEXIT,JMenuItem usermodify, JFrame frame,String userID) {
        userInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //查看个人信息的panel
                //名字与账号
                showuserxingxi(frame , userID);
            }
        });//实现个人信息功能

        usermodify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xiugaiuserxingxi(frame,userID);
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

    public static void showuserxingxi(JFrame frame,String userID){
        String JDBC_URL = "jdbc:mysql://localhost:3306/text00?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "20040612";
        String sql = "SELECT * FROM users WHERE account = ?";
        try (
                // 连接数据库
                Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                // 准备查询语句
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // 设置参数
            stmt.setString(1, userID);

            // 执行查询
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String account = rs.getString("account");
                String username = rs.getString("username");

                // 创建面板
                JPanel panel = new JPanel(new GridBagLayout());
                GridBagConstraints constraints = new GridBagConstraints();
                constraints.insets = new Insets(5, 5, 5, 5);

                // 添加账号提示
                JLabel accountLabel = new JLabel("账号：");
                constraints.gridx = 0;
                constraints.gridy = 1;
                panel.add(accountLabel, constraints);

                // 添加账号
                JTextField accountField = new JTextField(account);
                accountField.setEditable(false);
                constraints.gridx = 1;
                panel.add(accountField, constraints);

                // 添加名称提示
                JLabel usernameLabel = new JLabel("名称：");
                constraints.gridx = 0;
                constraints.gridy = 0;
                panel.add(usernameLabel, constraints);

                // 添加名称
                JTextField usernameField = new JTextField(username);
                usernameField.setEditable(false);
                constraints.gridx = 1;
                panel.add(usernameField, constraints);

                frame.getContentPane().removeAll();
                frame.add(panel);
                frame.setVisible(true);
            } else {
                System.out.println("User not found!");
            }
            // 在try-with-resources中使用，不需要手动关闭conn、stmt和rs，它们会自动关闭
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//实现个人信息功能
    public static void xiugaiuserxingxi(JFrame frame,String userID) {
        String JDBC_URL = "jdbc:mysql://localhost:3306/text00?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "20040612";
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("新用户名：");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(usernameLabel, constraints);
        JTextField usernameField = new JTextField(20);
        constraints.gridx = 1;
        panel.add(usernameField, constraints);
        JLabel passwordLabel = new JLabel("新密码：");
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(passwordLabel, constraints);

        JPasswordField passwordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(passwordField, constraints);

        JLabel confirmPasswordLabel = new JLabel("确认密码：");
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(confirmPasswordLabel, constraints);

        JPasswordField confirmPasswordField = new JPasswordField(20);
        constraints.gridx = 1;
        panel.add(confirmPasswordField, constraints);

        JButton updateButton = new JButton("修改用户名和密码");
        updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    String newUsername = usernameField.getText();
                    String newPassword = passwordField.getText();
                    String confirmPassword = new String(confirmPasswordField.getPassword());

                    if (newUsername.isEmpty() && newPassword.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "用户名和密码均为空，无需修改！");
                        return;
                    }

                    if (!newPassword.equals(confirmPassword)) {
                        JOptionPane.showMessageDialog(frame, "密码不匹配，请重新输入！");
                        return;
                    }

                    try {
                        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                        int rowsAffected = -1;
                        if(!(newUsername.isEmpty()) && !(newPassword.isEmpty())){
                            System.out.println("1");
                            PreparedStatement stmtUpdateUser = conn.prepareStatement("UPDATE users SET username = ?, password = ? WHERE account = ?");
                            stmtUpdateUser.setString(1, newUsername);
                            stmtUpdateUser.setString(2, newPassword);
                            stmtUpdateUser.setString(3, userID);
                            rowsAffected = stmtUpdateUser.executeUpdate();
                        }
                        else if(newUsername .isEmpty()) {
                            System.out.println("2");
                            PreparedStatement stmtUpdateUser = conn.prepareStatement("UPDATE users SET password = ? WHERE account = ?");
                            stmtUpdateUser.setString(1, newPassword);
                            stmtUpdateUser.setString(2, userID);
                            rowsAffected = stmtUpdateUser.executeUpdate();
                        }
                        else if(newPassword .isEmpty()){
                            System.out.println("3");
                            PreparedStatement stmtUpdateUser = conn.prepareStatement("UPDATE users SET username = ?WHERE account = ?");
                            stmtUpdateUser.setString(1, newUsername);
                            stmtUpdateUser.setString(2, userID);
                            rowsAffected = stmtUpdateUser.executeUpdate();
                        }
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(frame, "信息修改成功！");
                        } else {
                            JOptionPane.showMessageDialog(frame, "信息修改失败！");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame, "数据库操作失败！");
                    }
                }
            });
                        constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 2;
            panel.add(updateButton, constraints);

            frame.getContentPane().removeAll();
            frame.add(panel);
            frame.setVisible(true);
    }

}
