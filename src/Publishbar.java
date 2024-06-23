import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class Publishbar {

    public static void Publishmenu(JMenuItem UserPosts,JMenuItem newUserPosts, JFrame frame,String userID) {
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
                showUserPostsPanel(frame,userID);
            }
        });//实现删除已颁布帖子功能
    }
    public static class CatInfoPanel extends JPanel {
        JTextField catNameField;
        //JTextField catIDField;
        int catID;

        File selectedFile;
        JTextArea catTextField;
        JLabel catImageLabel;

        public CatInfoPanel(String users) {
            setLayout(new BorderLayout());

            // 创建输入域
            catNameField = new JTextField(15);
            //catIDField = new JTextField(15);
            catTextField = new JTextArea(5, 15);
            catImageLabel = new JLabel();

            JButton chooseImageButton = new JButton("选择图片");
            chooseImageButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        selectedFile = fileChooser.getSelectedFile();
                        ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                        catImageLabel.setIcon(new ImageIcon(imageIcon.getImage().getScaledInstance(200, 100, Image.SCALE_SMOOTH)));
                    }
                }
            });

            // 创建提交按钮
            JButton submitButton = new JButton("提交");
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(catNameField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "猫的名字不能为空！");
                        return ;
                        //ImageText.createInstance(catNameField.getText(), catIDField.getText(), catTextField.getText(), catImage);
                    }
                    if(catTextField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null, "猫的介绍不能为空");
                        return;
                    }
                    if(catImageLabel.getIcon() == null){
                        JOptionPane.showMessageDialog(null, "猫的图片不能为空");
                        return;
                    }
                    String JDBC_URL = "jdbc:mysql://localhost:3306/text00?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
                    String JDBC_USER = "root";
                    String JDBC_PASSWORD = "20040612";
                    Connection conn = null;
                    PreparedStatement stmt = null;
                    ResultSet rs = null;

                    try {
                        // 连接数据库
                        conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

                        // 初始化 PreparedStatement
                        String sq = "SELECT COUNT(*) AS rowcount FROM cats";
                        stmt = conn.prepareStatement(sq);

                        // 执行查询
                        rs = stmt.executeQuery();

                        if (rs.next()) {
                            catID = rs.getInt("rowcount");
                        }
                        // SQL插入语句
                        String sql = "INSERT INTO cats (cat_name, cat_discoverer,cat_id, cat_adopter) VALUES (?, ?, ?,?)";
                        stmt = conn.prepareStatement(sql);// 创建PreparedStatement对象

                        // 设置参数
                        stmt.setString(1, catNameField.getText()); // 替换为实际的猫的名字
                        stmt.setString(2, users);
                        stmt.setInt(3, catID);
                        stmt.setString(4,"");// 初始时领养人为空
                        stmt.executeUpdate();
                        sql ="INSERT INTO posts (post_id,cat_id,description,image_url) VALUES (?, ?, ?,?)";
                        stmt = conn.prepareStatement(sql);
                        stmt.setInt(1, Integer.parseInt(catNameField.getText()));
                        stmt.setInt(2, catID);
                        stmt.setString(3, catTextField.getText());
                        stmt.setString(4,selectedFile.toString());
                        // 执行插入操作
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(null, "猫的信息已提交！");
                            System.out.println("数据插入成功！");
                        } else {
                            System.out.println("未插入任何数据！");
                        }
                    } catch (SQLException xe) {
                        xe.printStackTrace();
                    } finally {
                        // 关闭Statement和Connection
                        try {
                            if (stmt != null) stmt.close();
                            if (conn != null) conn.close();
                        } catch (SQLException closeException) {
                            closeException.printStackTrace();
                        }
                    }
                }
            });

            // 布局
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(4, 2));
            inputPanel.add(new JLabel("猫的名字:"));
            inputPanel.add(catNameField);
            inputPanel.add(new JLabel("描述:"));
            inputPanel.add(new JScrollPane(catTextField));

            inputPanel.add(chooseImageButton);
            inputPanel.add(catImageLabel);

            add(inputPanel, BorderLayout.CENTER);
            add(submitButton, BorderLayout.SOUTH);
        }
        public JPanel getUserPostsJPanel() {
            return this;
        }
    }//实现发布帖子功能
    public static void showUserPostsPanel(JFrame frame,String userID) {
        CatInfoPanel catinfopanel = new CatInfoPanel(userID);
        frame.getContentPane().removeAll();
        frame.add(catinfopanel);
        frame.revalidate();
        frame.repaint();
    }//跳转发帖页面

    public static void shownewUserPostsPanel(JFrame frame){
    }

}
