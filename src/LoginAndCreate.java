import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


//数据库相关 61--78   登录，校验用户账号密码
//          136-158  注册，保存用户账号密码至数据库
//
public class LoginAndCreate {
     static javax.swing.JFrame frame;//创建窗口
     static JPanel loginPanel;
     static JPanel registerPanel;

    public void LoginAndRegisterGUI(javax.swing.JFrame frame) {
        this.frame = frame;
        loginPanel = createLoginPanel();// 创建登录界面
        registerPanel = createRegisterPanel();// 创建创建账号界面
        showLoginPanel();// 默认显示登录界面
    }//创建登录账号页面以及创建账号页面，默认显现登录账号页面

     JPanel createLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //设置输入框与登录按钮
        JLabel usernameLabel = new JLabel("账号:");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("登录");

        //指定输入框位置
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
        registerLabel.setForeground(Color.BLUE.darker());//设置颜色
         registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//鼠标靠近更改鼠标样式、
         constraints.gridy = 3;
         panel.add(registerLabel, constraints);
         //监听鼠标点击事件
         registerLabel.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showRegisterPanel();
            }//跳转到注册界面
         });
        //监听登录按钮的按下
         loginButton.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 // 处理登录逻辑
                 String username = usernameField.getText();//校验账号
                 String password = passwordField.getText();//校验密码

                 //需要将数据传入至数据库中访问
                 if(suerstureorfalse(username , password)){
                     //进入首页
                     gotofraMian(username);

                 }
                 else {
                     JOptionPane.showMessageDialog(panel, "账号或密码错误");//按下就出现
                 }
             }
         });

        return panel;
    }

    JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel usernameLabel = new JLabel("账号");
        JTextField usernameField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密码");
        JPasswordField passwordField = new JPasswordField(20);
        JLabel rightpasswordLabel = new JLabel("确认密码");
        JPasswordField rightpasswordField = new JPasswordField(20);
        JButton registerButton = new JButton("创建");

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
        constraints.gridy = 4;
        panel.add(loginLabel, constraints);

        //监听鼠标点击事件
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showLoginPanel();
            }
        });
        //监听按钮事件
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理注册逻辑
                String username = usernameField.getText();
                String password = passwordField.getText();
                String rightpassword = rightpasswordField.getText();
                // 这里可以添加注册逻辑
                if(password.equals(rightpassword)) {
                    JOptionPane.showMessageDialog(panel, "注册成功");

                    //将 username ， password 载入至数据库中保存
                    chuangjiansuer(username,password);
                    showLoginPanel();//自动跳转至登录页面
                }
                else {
                    JOptionPane.showMessageDialog(panel, "注册失败，密码不匹配");
                }

            }
        });

        return panel;
    }

    static void showLoginPanel() {
        frame.getContentPane().removeAll();
        frame.setJMenuBar(null);
        frame.add(loginPanel);
        frame.revalidate();
        frame.repaint();
    }
    static void showRegisterPanel() {
        frame.getContentPane().removeAll();
        frame.setJMenuBar(null);
        frame.add(registerPanel);
        frame.revalidate();
        frame.repaint();
    }

    //进入fraMain，即主板块
    void gotofraMian(String userID){
        frame.getContentPane().removeAll();
        frame.setJMenuBar(null);
        fraMain.startfraMain(frame , userID);
        frame.revalidate();
        frame.repaint();
    }

    public boolean suerstureorfalse(String userID, String usermima) {
        String JDBC_URL = "jdbc:mysql://localhost:3306/text00?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "20040612";
        boolean isValidUser = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to check if the user credentials are valid
            String sql = "SELECT COUNT(*) FROM users WHERE account = ? AND password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, usermima);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Check the result
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                isValidUser = (count > 0);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Close resources in reverse order of their opening
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isValidUser;
    }
    public void chuangjiansuer(String suersID, String suersmima) {
        String JDBC_URL = "jdbc:mysql://localhost:3306/text00?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "20040612";
        String sql = "INSERT INTO users (account, password, username) VALUES (?, ?, ?)";
        try (
                // 连接数据库
                Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                // 准备插入语句
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            // 设置参数
            stmt.setString(1, suersID);
            stmt.setString(2, suersmima);
            stmt.setString(3, suersID);

            // 执行插入
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            // 在try-with-resources中使用，不需要手动关闭conn和stmt，它们会自动关闭
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
